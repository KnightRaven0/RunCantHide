package runcanthide;

public class Player{
	private Vector2 Pos;
	private double dy, dx;
	private double Speed = .1;
	
	public Player(){
		Pos = new Vector2(0,0);
		dy = 0;
		dx = 0;
	}
	public Vector2 GetPos(){
		return Pos;
	}
	public void Update(){
		Pos.SetX(Pos.GetX() + dx);
		Pos.SetY(Pos.GetY() + dy);
		dy *= .97;
		dx *= .97;
	}
	
	public void MoveDirection(double X, double Y){
		dx += X * Speed;
		dy += Y * Speed;
	}
}