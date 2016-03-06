import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

enum TextState {
	PRINTING,
	DONE,
	WAITING_FOR_INPUT,
}

public class TextBox {
	Vector2 Position;
	Vector2 Size;
	String Text;
	boolean NeedsInput;
	
	TextState state;
	Thread animateThread;
	ArrayList<String> animateQueue = new ArrayList<String>();	
	int animateCounter = 0;

	int dashCounter = 0;
	
	public TextBox(Vector2 pos, Vector2  size){
		Position = pos;
		Size = size;
		Text = "";
		
		animateThread = new Thread(){	// Creates new thread
			public void run(){	// Run thread
				while (this != null){	// While loop to animate the String
					// when thread initializes, animate 
					if (animateQueue.size() > 0){	
						if (animateCounter < animateQueue.get(0).length()){
							state = TextState.PRINTING;
							animateCounter++;
							Text = animateQueue.get(0).substring(0, animateCounter);
							dashCounter = 20;
						} else {
							// Adds to counter, counter managed by equation in Draw method ***
							dashCounter++;	
							state = TextState.DONE;
						}
						GameWindow.gameWindow.gamePanel.repaint();
					}
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) { }
				}
			};
		};
		animateThread.start();
	}
	
	public void AnimateText(final String text, boolean needsInput){
		// Adds text to next slot in ArrayList
		animateQueue.add(text);
		NeedsInput = needsInput;
	}
	
	public void EnterToContinue(KeyEvent k) {
		if (animateQueue.size() > 1){
			animateQueue.remove(0);
			animateCounter = 0;
		}
	}
	
	public void ClickToContinue(MouseEvent k) {
		if (animateQueue.size() > 1){
			animateQueue.remove(0);
			animateCounter = 0;
		}
	}
	
	public void EnterCommand(KeyEvent k) {
		int i = 0;
		int keyCode = k.getKeyCode();
		while (i == 0) {
			// FIXME this thing - have to somehow wait for input
			//Thread.interrupt();
			// Check for input
			if (keyCode == 10 && Game.commandLine.length() > 0) {
				
			}
				
		}
		
	}
	
	public void Draw(Graphics2D g2d){
		g2d.setColor(Color.black);
		g2d.fillRect((int)Position.x, (int)Position.y, (int)Size.x, (int)Size.y);
		g2d.setColor(Color.green); // TODO replace with bg
		g2d.drawRect((int)Position.x, (int)Position.y, (int)Size.x, (int)Size.y);

		g2d.setFont(new Font("consolas", Font.PLAIN, 18));
		g2d.setColor(Color.green);
		// ***
		g2d.drawString(Text + ((dashCounter / 20) % 2 == 0 ? "__" : ""), (int)Position.x + 15, (int)Position.y + 30);	
	}
}
