import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Sprite {
	
	private int x;
	private int y;
	
	public static BufferedImage img;
	
	public Sprite() {
		init();
		// Nothing for now
	}
	
	public Sprite(int x, int y) {
		init();
		this.x = x;
		this.y = y;
	}
	
	public void init() {
    	Texture t = new Texture("/img/");
    	img = t.load("trump.png");
	}
	
	public void move(int deltaX, int deltaY) {
		x += deltaX;
		y += deltaY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Image getImage() {
		return img;
	}
	
	
}
