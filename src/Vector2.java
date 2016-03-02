
public class Vector2 
{
	public double x;
	public double y;
	
	public Vector2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2 add(Vector2 other)
	{
		return new Vector2(x + other.x, y + other.y);
	}
	public Vector2 sub(Vector2 other)
	{
		return new Vector2(x - other.x, y - other.y);
	}
	public Vector2 mul(Vector2 other)
	{
		return new Vector2(x * other.x, y * other.y);
	}
	public Vector2 div(Vector2 other)
	{
		return new Vector2(x / other.x, y / other.y);
	}
	public Vector2 mul(double other)
	{
		return new Vector2(x * other, y * other);
	}
	public Vector2 div(double other)
	{
		return new Vector2(x / other, y / other);
	}
	
	public double length()
	{
		return Math.sqrt(x*x + y*y);
	}
	
	public Vector2 normalized()
	{
		return div(length());
	}
	
	public static double DistanceSquared(Vector2 a, Vector2 b)
	{
		double x1 = a.x - b.x;
		double y1 = a.y - b.y;
		return x1*x1 + y1*y1;
	}
	public static double Distance(Vector2 a, Vector2 b)
	{
		return Math.sqrt(DistanceSquared(a,b));
	}
	public static Vector2 Zero(){
		return new Vector2(0,0);
	}
}
