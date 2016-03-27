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
			img = t.load("trump.png");
		} else if (compkemon.name.equals("Alex")) {
			img = t.load("alex.png");
		} else if (compkemon.name.equals("Hieu")) {
			img = t.load("trump.png");
		} else if (compkemon.name.equals("Jackon")) {
			img = t.load("trump.png");
		} else if (compkemon.name.equals("Jeremiah")) {
			img = t.load("trump.png");
		} else if (compkemon.name.equals("Kenny")) {
			img = t.load("trump.png");
		} else if (compkemon.name.equals("Noah")) {
			img = t.load("trump.png");
		} else if (compkemon.name.equals("Ryan")) {
			img = t.load("trump.png");
		} else if (compkemon.name.equals("Trevor")) {
			img = t.load("trump.png");
		} else if (compkemon.name.equals("Wrightson")) {
			img = t.load("trump.png");
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
