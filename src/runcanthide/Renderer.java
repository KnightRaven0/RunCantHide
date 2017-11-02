package runcanthide;

import javax.swing.JFrame;

public class Renderer{
	
	private JFrame Frame;
	private final int WindowWidth = 800;
	private final int WindowHight = 800;	
	
	public Renderer(StateManager Controller){
		Frame = new JFrame("Run, Can't Hide");
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setResizable(false);
		Frame.setVisible(true);
	}
	
	public void SetGamePanel(GamePanel p){
		Frame.setContentPane(p);
	}
	
	public void RepackFrame(){
		Frame.setBounds(0, 0, WindowWidth, WindowHight);
		Frame.setVisible(true);
	}
}