import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.*;

public class Animations {
	
	static boolean done;
	
	public static void intro(Graphics2D g2d) {

		g2d.setColor(Color.GREEN);
		Ellipse2D ground = new Ellipse2D.Double(0, 0, 250, 50);
		g2d.draw(ground);
		ground.setFrame(100, 0, 250, 50);
		GameWindow.gameWindow.gamePanel.repaint();
		done = true;
		System.out.println("done");
		
	}
}
