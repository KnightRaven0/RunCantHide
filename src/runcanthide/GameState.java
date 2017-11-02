package runcanthide;

import java.awt.Graphics;

public abstract class GameState{
	protected Renderer Render;
	protected StateManager Controller;
	public GameState(Renderer Render, StateManager Controller){this.Render = Render; this.Controller = Controller;}
	public abstract void Pause();
	public abstract void Resume();
	public abstract void Draw(Graphics g);
	public abstract void Update();
}