import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ground {

	Ellipse2D ground;
	
	public Ground(int startX, int startY) {
		ground = new Ellipse2D.Double(startX, startY, 250, 50);
	}
	
	public void draw (Graphics2D g) {
		g.setColor(Color.GREEN);
		g.draw(ground);
	}
	
	public void move(double deltaX, double deltaY) {
		ground.setFrame(ground.getX() + deltaX, ground.getY() + deltaY, ground.getWidth(), ground.getHeight());
	}
	
	public double getX() {
		return ground.getX();
	}
	
	public double getY() {
		return ground.getY();
	}
}
