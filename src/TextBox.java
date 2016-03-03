import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TextBox {
	Vector2 Position;
	Vector2 Size;
	String Text;
	
	Thread animateThread;
	
	public TextBox(Vector2 pos, Vector2  size){
		Position = pos;
		Size = size;
		Text = "";
	}
	
	public void AnimateText(final String text){
		if (animateThread != null)
			animateThread.interrupt();
		
		animateThread = new Thread(){
			public void run(){
				for (int i = 0; i <= text.length(); i++){
					Text = text.substring(0, i);
					GameWindow.gameWindow.gamePanel.repaint();
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						break;
					}
				}
			}
		};
		animateThread.start();
	}
	
	public void Draw(Graphics2D g2d){
		g2d.setColor(Color.black);
		g2d.fillRect((int)Position.x, (int)Position.y, (int)Size.x, (int)Size.y);
		g2d.setColor(Color.green); // TODO replace with bg
		g2d.drawRect((int)Position.x, (int)Position.y, (int)Size.x, (int)Size.y);

		g2d.setFont(new Font("consolas", Font.PLAIN, 18));
		g2d.setColor(Color.green);
		g2d.drawString(Text, (int)Position.x + 10, (int)Position.y + 30);
	}
}
