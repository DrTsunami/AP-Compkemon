import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
/**
 * The main game panel, resides in a JFrame (Main)
 * This is just for painting things
 * @author Trevor
 *
 */
class GamePanel extends JPanel{
	public GameWindow mainWindow;
	public Game game;
	
	public GamePanel(GameWindow window){
		mainWindow = window;
    	setOpaque(false);
    	setSize(mainWindow.getSize());
	}

	public void init(){
		game = new Game();
		game.panel = this;
		game.init();
	}
	
    public void paintComponent(Graphics g){
    	if (game != null){
	    	GameWindow.ScreenWidth = getWidth();
	    	GameWindow.ScreenHeight = getHeight();
	    	
	        Graphics2D g2d = (Graphics2D)g;
	        
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	        
	        game.draw(g2d);
	        
	    	g2d.dispose();
    	}
    }
    
	public static void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += (g.getFontMetrics().getHeight()));
    }
	
}

@SuppressWarnings("serial")
/**
 * The main JFrame. Creates a thread to update and draw
 * a GamePanel inside it.
 * @author Trevor
 *
 */
public class GameWindow extends JFrame implements KeyListener, MouseListener, MouseMotionListener  {
    public static int ScreenWidth, ScreenHeight;

    public boolean isRunning = false;
    public static GameWindow gameWindow;
    public GamePanel gamePanel;

    /**
     * Creates a JFrame, maximized, then calls
     * Content.LoadContent(), init(), and starts updating
     */
    public GameWindow(){
    	setSize(new Dimension(900, 690));
    	
    	//setUndecorated(true); // borderless (fullscreen) window
    	//setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH); // maximize window
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game");
        setLocationRelativeTo(null); // center on screen
    }

    public void keyPressed( KeyEvent e ) {
        char c = e.getKeyChar();
        if ( c != KeyEvent.CHAR_UNDEFINED ){
            Input.keys[e.getKeyCode()] = true;
            if (gamePanel != null && gamePanel.game != null)
            	gamePanel.game.KeyPress(e);
    	}
        
    	if (Input.Typing) {
	    	int i = (int)c;
	    	if (i >= 32 && i < 127)
	    		Input.Typed = Input.Typed + c;
	    	else if (i == 8)
	    		if (Input.Typed.length() > 0)
	    			Input.Typed = Input.Typed.substring(0, Input.Typed.length() - 1);
	    }
    }

    public void keyReleased( KeyEvent e ) {
        char c = e.getKeyChar();
        if ( c != KeyEvent.CHAR_UNDEFINED )
            Input.keys[e.getKeyCode()] = false;
    }

    public void keyTyped( KeyEvent e ) { }

    public void mouseEntered( MouseEvent e ) { }

    public void mouseExited( MouseEvent e ) { }

    public void mousePressed( MouseEvent e ) {
        if (e.getButton() == MouseEvent.BUTTON1)
            Input.MouseButtons[0] = true;
        if (e.getButton() == MouseEvent.BUTTON2)
            Input.MouseButtons[1] = true;
        if (e.getButton() == MouseEvent.BUTTON3)
            Input.MouseButtons[2] = true;

        if (gamePanel != null && gamePanel.game != null)
        	gamePanel.game.MousePress(e);
        	//gamePanel.game.textBox.ClickToContinue(e);
    }

    public void mouseReleased( MouseEvent e ) {
        if (e.getButton() == MouseEvent.BUTTON1)
            Input.MouseButtons[0] = false;
        if (e.getButton() == MouseEvent.BUTTON2)
            Input.MouseButtons[1] = false;
        if (e.getButton() == MouseEvent.BUTTON3)
            Input.MouseButtons[2] = false;
    }

    public void mouseMoved( MouseEvent e ) {
        Input.MousePosition.x = e.getX();
        Input.MousePosition.y = e.getY();
    }

    public void mouseDragged( MouseEvent e ) {
        Input.MousePosition.x = e.getX();
        Input.MousePosition.y = e.getY();
    }

    public void mouseClicked( MouseEvent e ) { }

    /**
     * Creates and initializes the GamePanel
     */
    public void init(){
    	gamePanel = new GamePanel(this);
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        
        gamePanel.addMouseListener(this);
        gamePanel.addMouseMotionListener(this);
       
        
        gamePanel.init();
    }
    
    public static void main(String[] args) {
        gameWindow = new GameWindow();
        gameWindow.addKeyListener(gameWindow);
        gameWindow.setVisible(true);
        gameWindow.init();
    }
}
