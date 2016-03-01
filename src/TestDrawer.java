import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestDrawer extends JPanel{
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		
		g.setColor(Color.BLUE);
		g.fillRect(25, 25, 100, 30);
		
		g.setColor(Color.RED);
		g.drawString("this is some text", 25, 120);
		
		g.drawLine(0, 0, 400, 400);
	}
}


