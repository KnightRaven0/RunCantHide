package runcanthide;

import java.util.ArrayList;
import java.util.Random;

public class Map {

	public ArrayList<ArrayList<Tile>> Grid;

	public Map(int DefaultSize) {
		Random Rand = new Random();
		Grid = new ArrayList<>(DefaultSize);
		for (int i = 0; i < DefaultSize; i++) {
			Grid.add(new ArrayList<>());
			for (int j = 0; j < DefaultSize; j++) {
				double P1 = Rand.nextDouble() * 6000 - 3000, P2 = Rand.nextDouble() * 6000 - 3000;
				double Angle = Rand.nextDouble() * jMath.PIt2;
				double Length = Rand.nextDouble() * 100 + 100;
				Grid.get(i).add(new Tile(new Vector2[]{new Vector2(Math.min(P1,P1 + Math.cos(Angle) * Length), Math.min(P2,P2 + Math.sin(Angle) * Length)), new Vector2(Math.max(P1,P1 + Math.cos(Angle) * Length), Math.max(P2,P2 + Math.sin(Angle) * Length))}));
				//Grid.get(i).add(new Tile(new Vector2[]{new Vector2(P1,P2), new Vector2(P1 + Math.cos(Angle) * Length, P2 + Math.sin(Angle) * Length)}));
			}
		}
	}

	public ArrayList<ArrayList<Vector2>> GetShadows(Vector2 Pos) {
		ArrayList<ArrayList<Vector2>> Shapes = new ArrayList<>();
		for (int i = 0; i < Grid.size(); i++) {
			for (int j = 0; j < Grid.get(i).size(); j++) {
				Shapes.addAll(Grid.get(i).get(j).GetShapes(Pos));
			}
		}
		return Shapes;
	}
}
