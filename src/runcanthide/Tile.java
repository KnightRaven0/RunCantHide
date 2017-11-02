package runcanthide;

import java.util.ArrayList;
import java.util.Arrays;

public class Tile {

	private ArrayList<ArrayList<Vector2>> Positions;

	public Tile(Vector2[] v) {
		ArrayList<Vector2> Points = new ArrayList<>(Arrays.asList(v));
		Positions = new ArrayList<>();
		Positions.add(Points);
	}

	public ArrayList<Angle> GetAngles(Vector2 Pos) {
		ArrayList<Angle> Angles = new ArrayList<>();
		for (ArrayList<Vector2> Position : Positions) {
			double MinY = -Position.get(0).GetY(), MaxY = -Position.get(0).GetY(), MaxX = Position.get(0).GetX(), MinX = Position.get(0).GetX();
			double MinAngle = jMath.getRadiansPointsTo(Pos, Position.get(0));
			double MaxAngle = MinAngle;
			for (int i = 1; i < Position.size(); i++) {
				Vector2 p = Position.get(i);
				MinY = Math.min(MinY, -p.GetY());
				MaxY = Math.max(MaxY, -p.GetY());
				MinX = Math.min(MinX, p.GetX());
				MaxX = Math.max(MaxX, p.GetX());
				double Ang = jMath.getRadiansPointsTo(Pos, p);
				MinAngle = Math.min(MinAngle, Ang);
				MaxAngle = Math.max(MaxAngle, Ang);
			}
			double s = (-Position.get(0).GetY() + Position.get(1).GetY()) / (Position.get(0).GetX() - Position.get(1).GetX());
			double i = -s * Position.get(0).GetX() - Position.get(0).GetY();
			double y = s * Pos.GetX() + i;
			boolean l = -Pos.GetY() < y;
			if (l && (-Pos.GetY() > MaxY || -Pos.GetY() < MinY)){
				Angles.add(new Angle(MinAngle, MaxAngle));
			} else {
				Angles.add(new Angle(MaxAngle, MinAngle));
			}
		}
		return Angles;
	}

	public ArrayList<ArrayList<Vector2>> GetRealShapes(Vector2 Pos, ArrayList<Vector2> points) {
		ArrayList<ArrayList<Vector2>> Shapes = new ArrayList<>();
		return Shapes;
	}

	public ArrayList<ArrayList<Vector2>> GetShapes(Vector2 Pos) {
		ArrayList<ArrayList<Vector2>> Shapes = new ArrayList<>();
		ArrayList<Angle> a = GetAngles(Pos);
		for (int i = 0; i < Positions.size(); i++) {
			ArrayList<Vector2> temp = new ArrayList<>();
			for (int j = 0; j < Positions.get(i).size(); j++) {
				temp.add(Positions.get(i).get(j));
			}
			temp.add(new Vector2(Math.cos(a.get(i).GetAngle()) * 100000, -Math.sin(a.get(i).GetAngle()) * 100000));
			temp.add(new Vector2(Math.cos(a.get(i).GetAddition()) * 100000, -Math.sin(a.get(i).GetAddition()) * 100000));
			Shapes.add(temp);
		}
		return Shapes;
	}
}
