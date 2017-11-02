package runcanthide;

import java.awt.Graphics;
import java.util.Stack;

public class StateManager {

	private Stack<GameState> States;
	private boolean Running = true;
	private boolean Pop = false;

	private Renderer Rend;
	private GamePanel Panel;

	public StateManager() {
		States = new Stack<>();
		States.push(new MainMenuState(Rend, this));
		Rend = new Renderer(this);
		Panel = new GamePanel(this);
		Rend.SetGamePanel(Panel);
		Rend.RepackFrame();
	}

	public boolean IsRunning() {
		return Running;
	}

	public void Exit() {
		Running = false;
	}

	public void Pop() {
		Pop = true;
	}

	public void Push(GameState NewState) {
		States.lastElement().Pause();
		States.push(NewState);
	}

	public void Update() {
		if (!States.isEmpty() && Running) {
			if (Pop) {
				States.pop();
				Pop = false;
				if (!States.isEmpty()) {
					States.lastElement().Resume();
					States.lastElement().Update();
				} else {
					Running = false;
				}
			} else {
				States.lastElement().Update();
			}
		} else {
			Running = false;
		}
	}

	public void Draw(Graphics g) {
		if (!States.isEmpty() && Running) {
			States.lastElement().Draw(g);
			Panel.repaint();
		}
	}

}
