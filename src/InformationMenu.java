package runcanthide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class InformationMenu extends GameState {

	public InformationMenu(Renderer Render, StateManager Control) {
		super(Render, Control);
	}

	@Override
	public void Resume() {
	}

	@Override
	public void Draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 800);
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Comic Sans MS", 1, 20));
		g.drawString("The Goal of this game is to escape.", 50, 100);
		g.drawString("The Squares are hunting you. Run.", 50, 130);
		g.drawString("There are walls of black fog.", 50, 160);
		g.drawString("You can't see past them, but they aren't solid.", 50, 190);
		g.drawString("You can pass through them, but beware. What lies on the other side?", 50, 220);
		g.drawString("If I run far enough away, will I get away?", 50, 250);
		g.drawString("I have to try.", 50, 280);
		g.drawString("WSAD or arrow keys to move.", 50, 400);
		g.drawString("Escape to exit this menu.", 50, 430);
		g.drawString("Good luck.", 50, 460);
	}

	public void HandleKeys() {
		if (Input.IsKeyPressed(KeyEvent.VK_ESCAPE)) {
			Controller.Pop();
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
