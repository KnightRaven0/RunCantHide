package runcanthide;

public class Vector2{
	private double x;
	private double y;
	
	public Vector2(){
		x = 0;
		y = 0;
	}
	public Vector2(double x, double y){
		this.x = x;
		this.y = y;
	}
	public double GetX(){
		return x;
	}
	public double GetY(){
		return y;
	}
	public void SetX(double x){
		this.x = x;
	}
	public void SetY(double y){
		this.y = y;
	}
}