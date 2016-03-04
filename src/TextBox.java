import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TextBox {
	Vector2 Position;
	Vector2 Size;
	String Text;
	
	Thread animateThread;
	boolean animationDone;
	
	
	public TextBox(Vector2 pos, Vector2  size){
		Position = pos;
		Size = size;
		Text = "";
	}
	
	public void AnimateText(final String text){
		if (animateThread != null) {
			animateThread.interrupt();
		}
		//FIXME make arraylist of threads. then use for each loop to iterate in order of threads to print text
			
		animateThread = new Thread(){
			public void run(){
				for (int i = 0; i <= text.length() + 1; i++){
					if (i <= text.length()) {
						animationDone = false;
						Text = text.substring(0, i);
						GameWindow.gameWindow.gamePanel.repaint();
						
						try {
							Thread.sleep(15);
						} catch (InterruptedException e) {
							break;
						}
					} else if (i == text.length() + 1) {
						animationDone = true;
					}
					
					while (animationDone) {
						Text += "_";
						GameWindow.gameWindow.gamePanel.repaint();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							break;
						}
						Text = Text.substring(0, text.length());
						GameWindow.gameWindow.gamePanel.repaint();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							break;
						}
					}									
				}
			}
		};
		animateThread.start();
	}
	
	public void ClickToContinue(MouseEvent k) {
		if (animateThread != null && animationDone) {
			animationDone = false; // stops current animation
		}
	}
	
	public void Draw(Graphics2D g2d){
		g2d.setColor(Color.black);
		g2d.fillRect((int)Position.x, (int)Position.y, (int)Size.x, (int)Size.y);
		g2d.setColor(Color.green); // TODO replace with bg
		g2d.drawRect((int)Position.x, (int)Position.y, (int)Size.x, (int)Size.y);

		g2d.setFont(new Font("consolas", Font.PLAIN, 18));
		g2d.setColor(Color.green);
		g2d.drawString(Text, (int)Position.x + 15, (int)Position.y + 30);
	}
}
