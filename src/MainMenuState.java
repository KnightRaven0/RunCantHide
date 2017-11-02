package runcanthide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class MainMenuState extends GameState {

	private String[] Options = {"Play Game", "Information", "Exit", "Fluffy Menu Option"};
	private int Selected = 0;

	public MainMenuState(Renderer Render, StateManager Control) {
		super(Render, Control);
	}

	@Override
	public void Resume() {
		Options[3] = "Fluffy Menu Option";
	}

	@Override
	public void Draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 800);
		int height = 800 / Options.length;
		g.setColor(Color.GREEN);
		g.fillRect(0, height * Selected, 800, height);
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Comic Sans MS", 1, 45));
		for (int i = 0; i < Options.length; i++) {
			g.drawString(Options[i], 50, height * i + 100);
		}
		g.setFont(new Font("Comic Sans MS", 1, 16));
		g.drawString("Nagivate: WSAD/Arrowkeys", 500, 30);
		g.drawString("Select: Enter", 500, 60);
		g.drawString("Exit: Escape", 500, 90);
	}

	public void HandleKeys() {
		if (Input.IsKeyPressed(KeyEvent.VK_ESCAPE)) {
			Controller.Pop();
		}
		if (Input.IsKeyPressed(KeyEvent.VK_W) || Input.IsKeyPressed(KeyEvent.VK_UP)) {
			Selected--;
			if (Selected < 0) {
				Selected = Options.length - 1;
			}
		}
		if (Input.IsKeyPressed(KeyEvent.VK_S) || Input.IsKeyPressed(KeyEvent.VK_DOWN)) {
			Selected++;
			Selected %= Options.length;
		}
		if (Input.IsKeyPressed(KeyEvent.VK_ENTER)) {
			switch (Selected) {
				case 0:
					Controller.Push(new PlayState(Render, Controller));
					break;
				case 1:
					Controller.Push(new InformationMenu(Render, Controller));
					break;
				case 2:
					Controller.Pop();
					break;
				case 3:
					Options[3] = "This doesn't do anything.";
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void Update() {
		HandleKeys();
	}

	@Override
	public void Pause() {

	}
}
