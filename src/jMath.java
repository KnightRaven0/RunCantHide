package runcanthide;

public class jMath {

	public static final double PI = 3.141592653589793238462643383279502884197;
	public static final double PIo2 = 1.570796326794896619231321691639751442098;
	public static final double PIt2 = 6.283185307179586476925286766559005768394;

	public static double getRadiansPointsTo(Vector2 From, Vector2 To) {
		double DifX = To.GetX() - From.GetX();
		double DifY = To.GetY() - From.GetY();
		double Angle;
		if (DifY != 0) {
			Angle = Math.atan2(-DifY , DifX);
		} else {
			Angle = PI;
		}
		if (Angle < 0) {
			Angle += PIt2;
		}
		return Angle;
	}
}
