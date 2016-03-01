import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;

@SuppressWarnings("unused")
public class Game {
    public SolarSystemClass solarSystem;
    
    public Game()
    {
    	solarSystem = new SolarSystemClass();
    }

    public void init()
    {
    	solarSystem = new SolarSystemClass();
    }
    
    public void mouseMoved() {
    	
    }

    public void update( float delta )
    {
    	solarSystem.updateAll(delta);
    }

    public void draw( Graphics g )
    {
    	for (int index = 0; index < solarSystem.system.size(); index++)
    	{
    		
    		g.fillOval((int)(solarSystem.system.get(index).location.x),(int)(solarSystem.system.get(index).location.y), (int)(solarSystem.system.get(index).radius/5000), (int)(solarSystem.system.get(index).radius/5000));
    		g.fillOval(600, 500, 200, 200);
    	}
    	
    }
}


