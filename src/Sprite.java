import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Sprite {
	
	private int x;
	private int y;
	public Compkemon compkemon;
	
	public static BufferedImage img;
	
	public Sprite() {
		// Nothing for now
	}
	
	public Sprite(Compkemon c, int x, int y) {
		this.x = x;
		this.y = y;
		compkemon = c;
		
    	Texture t = new Texture("/img/");
    	
    	switch (c.name) {
			case "Prototype" :
				img = t.load("trump.png");
				break;
			case "Aidan" :
				img = t.load("trump.png");
				break;
			case "Alex" :
				img = t.load("trump.png");
				break;
			case "Hieu" :
				img = t.load("trump.png");
				break;
			case "Jackson" :
				img = t.load("trump.png");
				break;
			case "Jeremiah" :
				img = t.load("trump.png");
				break;
			case "Kenny" :
				img = t.load("trump.png");
				break;
			case "Noah" :
				img = t.load("trump.png");
				break;
			case "Ryan" :
				img = t.load("trump.png");
				break;
			case "Trevor" :
				img = t.load("trump.png");
				break;
			case "Wrightson" :
				img = t.load("trump.png");
				break;
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
