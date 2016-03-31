import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Sprite {
	
	private int x;
	private int y;
	private Compkemon compkemon;
	private BufferedImage img;
	Texture t = new Texture("/img/");
	
	public Sprite() {
		// Nothing for now
	}
	
	public Sprite(Compkemon c, int x, int y) {
		this.x = x;
		this.y = y;
		compkemon = c;
		
		init();
		
	}
	
	public void init() {
		
		if (compkemon.name.equals("Prototype")) {
			img = t.load("trump.png");
		} else if (compkemon.name.equals("Aidan")) {
			img = t.load("aidan.png");
		} else if (compkemon.name.equals("Alex")) {
			img = t.load("alex.png");
		} else if (compkemon.name.equals("Hieu")) {
			img = t.load("hieu.png");
		} else if (compkemon.name.equals("Jackson")) {
			img = t.load("jackson.png");
		} else if (compkemon.name.equals("Jeremiah")) {
			img = t.load("jeremiah.png");
		} else if (compkemon.name.equals("Kenny")) {
			img = t.load("kenny.png");
		} else if (compkemon.name.equals("Noah")) {
			img = t.load("noah.png");
		} else if (compkemon.name.equals("Ryan")) {
			img = t.load("ryan.png");
		} else if (compkemon.name.equals("Trevor")) {
			img = t.load("trevor.png");
		} else if (compkemon.name.equals("God")) {
			img = t.load("god.png");
		}
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
