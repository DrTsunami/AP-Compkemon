import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class HealthBox {
	
	Rectangle2D healthBox;
	Rectangle2D healthBar;
	Rectangle2D currentHealth;
	Compkemon compkemon;
	boolean animationDone;
	
	public HealthBox(Compkemon c, int startX, int startY) {
		compkemon = c;
		animationDone = false;
		
		healthBox = new Rectangle2D.Double(startX, startY, 300, 85);
		healthBar = new Rectangle2D.Double(startX + 40, startY + 40, 250, 15);
		currentHealth = new Rectangle2D.Double(startX + 40, startY + 40, 250, 15);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.draw(healthBox);
		g2d.draw(healthBar);
		g2d.fill(currentHealth);
		g2d.drawString("HP:", (int)this.getX() + 10, (int)this.getY() + 71 - 17);
		g2d.drawString(compkemon.name, (int)this.getX() + 5, (int)this.getY() + 20);
		//g2d.drawString(compkemon.currentHealth + "/" + compkemon.health, (int)this.getX() + 220, (int)this.getY() + 71);
	}
	
	// TODO allow change health to handle health gaining moves
	public void changeHealth(double health) {
		
		double percentDeltaHealth = health/100;
		double newHealth = percentDeltaHealth * healthBar.getWidth();
		
		animationDone = false;
				
		if (currentHealth.getWidth() > newHealth) {
			System.out.println("getWidth: " + currentHealth.getWidth() + "\t" + "newHealth = " + newHealth);
			currentHealth.setFrame(currentHealth.getX(), currentHealth.getY(), currentHealth.getWidth() - 1, currentHealth.getHeight());
			if (currentHealth.getWidth() < newHealth) {
				animationDone = true;
			}
		} else if (currentHealth.getWidth() < newHealth) {
			System.out.println("Absorption detected");
			System.out.println("getWidth: " + currentHealth.getWidth() + "\t" + "newHealth = " + newHealth);
			currentHealth.setFrame(currentHealth.getX(), currentHealth.getY(), currentHealth.getWidth() + 1, currentHealth.getHeight());
			if (currentHealth.getWidth() > newHealth) {
				animationDone = true;
			}
		} else {
			animationDone = true; 
		}
	}
	
	
	
	public double getX() {
		return healthBox.getX();
	}
	
	public double getY() {
		return healthBox.getY();
	}
}
