package runcanthide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class PlayState extends GameState {

	private Player Victim;
	private ArrayList<Monster> Monsters;
	private Map Level;

	private boolean Running = true;
	private String EndMessage = "";
	private Color EndColor;
	private double DeadAlpha = 0;

	public PlayState(Renderer Render, StateManager Control) {
		super(Render, Control);
		Random Rand = new Random();
		Victim = new Player();
		Level = new Map(25);
		Monsters = new ArrayList<>();
		for (int i = 0; i < 400; i++) {
			Vector2 Pos = new Vector2(Rand.nextInt(6000) - 3000, Rand.nextInt(6000) - 3000);
			if (!(Math.sqrt(Math.pow(Victim.GetPos().GetX() - Pos.GetX(), 2) + Math.pow(Victim.GetPos().GetY() - Pos.GetY(), 2)) < 300)) {
				Monsters.add(new Monster(Pos));
			} else {
				i--;
			}
		}
	}

	@Override
	public void Resume() {

	}

	@Override
	public void Pause() {

	}

	@Override
	public void Draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 800, 800);
		Vector2 CamMod = new Vector2(Victim.GetPos().GetX() - 400, Victim.GetPos().GetY() - 400);
		g.setColor(Color.BLUE);
		g.fillOval((int) (Victim.GetPos().GetX() - CamMod.GetX()) - 10, (int) (Victim.GetPos().GetY() - CamMod.GetY()) - 10, 20, 20);
		g.setColor(Color.RED);
		for (int i = 0; i < Monsters.size(); i++) {
			g.fillRect((int) (Monsters.get(i).GetPos().GetX() - CamMod.GetX()) - 10, (int) (Monsters.get(i).GetPos().GetY() - CamMod.GetY()) - 10, 20, 20);
		}
		ArrayList<ArrayList<Vector2>> Shapes = Level.GetShadows(Victim.GetPos());
		for (int i = 0; i < Shapes.size(); i++) {
			int[] xs = new int[Shapes.get(i).size()];
			int[] ys = new int[Shapes.get(i).size()];
			for (int j = 0; j < Shapes.get(i).size(); j++) {
				xs[j] = (int) (Shapes.get(i).get(j).GetX() - CamMod.GetX());
				ys[j] = (int) (Shapes.get(i).get(j).GetY() - CamMod.GetY());
				g.setColor(Color.BLACK);
			}
			g.fillPolygon(xs, ys, Shapes.get(i).size());
		}
		if (!Running) {
			if (DeadAlpha < 255) {
				DeadAlpha += .4;
			}
			g.setColor(new Color(EndColor.getRed(), EndColor.getGreen(), EndColor.getBlue(), (int) DeadAlpha));
			
			g.setFont(new Font("Comic Sans MS", 1, 45));
			g.drawString(EndMessage, 220, 300);
			g.setFont(new Font("Comic Sans MS", 1, 20));
			g.drawString("Press Escape to go back to menu", 220, 350);
		}
	}

	public void HandleKeys() {
		if (Running) {
			if (Input.IsKeyDown(KeyEvent.VK_W) || Input.IsKeyDown(KeyEvent.VK_UP)) {
				Victim.MoveDirection(0, -1);
			}
			if (Input.IsKeyDown(KeyEvent.VK_S) || Input.IsKeyDown(KeyEvent.VK_DOWN)) {
				Victim.MoveDirection(0, 1);
			}
			if (Input.IsKeyDown(KeyEvent.VK_A) || Input.IsKeyDown(KeyEvent.VK_LEFT)) {
				Victim.MoveDirection(-1, 0);
			}
			if (Input.IsKeyDown(KeyEvent.VK_D) || Input.IsKeyDown(KeyEvent.VK_RIGHT)) {
				Victim.MoveDirection(1, 0);
			}
		} else if (Input.IsKeyPressed(KeyEvent.VK_ESCAPE)) {
			Controller.Pop();
		}
	}

	@Override
	public void Update() {
		if (Running) {
			Victim.Update();
			if (Victim.GetPos().GetX() > 3100 || Victim.GetPos().GetX() < -3100 || Victim.GetPos().GetY() > 3100 || Victim.GetPos().GetY() < -3100) {
				EndMessage = "You WON!";
				EndColor = Color.BLUE;
				Running = false;
			}
			int[] player = {(int) Victim.GetPos().GetX() - 7, (int) Victim.GetPos().GetY() - 7,
				(int) Victim.GetPos().GetX() + 7, (int) Victim.GetPos().GetY() + 7};
			for (int i = 0; i < Monsters.size(); i++) {
				Monsters.get(i).Update(Victim);
				boolean Collided = true;
				int[] rec = {(int) Monsters.get(i).GetPos().GetX() - 10, (int) Monsters.get(i).GetPos().GetY() - 10,
					(int) Monsters.get(i).GetPos().GetX() + 10, (int) Monsters.get(i).GetPos().GetY() + 10};
				if (rec[0] > player[2]) {
					Collided = false;
				} else if (player[0] > rec[2]) {
					Collided = false;
				} else if (player[1] > rec[3]) {
					Collided = false;
				} else if (rec[1] > player[3]) {
					Collided = false;
				}
				if (Collided) {
					Running = false;
					EndMessage = "GAME OVER...";
					EndColor = Color.RED;
				}
			}
		} else {
			for (int i = 0; i < Monsters.size(); i++) {
				Monsters.get(i).Update(Victim);
			}
		}
		HandleKeys();
	}
}
