import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.*;

public class Animations {
	
	
	public static void intro(Graphics2D g2d, Ground userGround, Ground enemyGround) {

		Game.animating = true;
		
		if (userGround.getX() < 100) { 
			userGround.move(1, 0);
		}
		
		if (enemyGround.getX() > (GameWindow.ScreenWidth - 350)) {
			enemyGround.move(-1, 0);
		} else {
			userGround.draw(g2d);
			enemyGround.draw(g2d);
			Game.animating = false;
		}
		userGround.draw(g2d);
		enemyGround.draw(g2d);
		
	}
	
	public static void damaged(Graphics2D g2d, HealthBox box) {
		box.changeHealth(box.compkemon.currentHealth);
		box.draw(g2d);
	}
	

}
