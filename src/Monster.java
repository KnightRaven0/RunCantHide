package runcanthide;

public class Monster{
	private Vector2 Pos;
	private int State = 0;	//0 = No motion 1 = Move Forward 2 = Chase Player
	
	public Monster(Vector2 Position){
		Pos = Position;
	}
	
	public void Update(Player Victim){
		if (Math.sqrt(Math.pow(Victim.GetPos().GetX() - Pos.GetX(), 2) + Math.pow(-Victim.GetPos().GetY() + Pos.GetY(), 2)) < 300){
			State = 2;
		}
		if (State == 2){
			double AngleToPlayer = jMath.getRadiansPointsTo(Pos, Victim.GetPos());
			Pos.SetX(Pos.GetX() + Math.cos(AngleToPlayer) * 1.5);
			Pos.SetY(Pos.GetY() - Math.sin(AngleToPlayer) * 1.5);
		}
	}
	
	public Vector2 GetPos(){
		return Pos;
	}
}