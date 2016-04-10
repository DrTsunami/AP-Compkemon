import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Compkemon {

    String name;
    String type;
    Move[] moveset;
    Move currentMove;
    int health;
    int currentHealth;
    float attack;
    float defense;
    float speed;
    float evasion;
    ArrayList<Effect> effect;
    
    CompkemonList compkemonList;
    
    // Zero arg Constructor
    public Compkemon() {
		this.name = "";
        this.type = "";
        this.health = 0;
        this.currentHealth = 0;
        this.attack = 0.0f;
        this.defense = 0.0f;
        this.speed = 0.0f; 
        this.evasion = 1.0f;
        this.moveset = new Move[4];
        this.currentMove = new Move();
        this.effect = new ArrayList<Effect>();
    }
    
    // List of available Compkemon. Arg constructor for Compkemon
    public Compkemon(CompkemonList compkemon) {
    	
    	compkemonList = compkemon;
    	
    	switch(compkemon) {
			case Prototype: 
				this.name = "Prototype";
		        this.type = "God";
		        this.health = 100;
		        this.currentHealth = 100;
		        this.attack = 80.0f;
		        this.defense = 70.0f;
		        this.speed = 80.0f; 
		        this.evasion = 1.0f;
		        this.effect = new ArrayList<Effect>();
		        this.moveset = new Move[4];
		        this.currentMove = new Move();
		
		        moveset[0] = new Move(MoveName.Splash);
		        moveset[1] = new Move(MoveName.Splash);
		        moveset[2] = new Move(MoveName.Splash);
		        moveset[3] = new Move(MoveName.Splash);
		        break;
///////////////////////////////////////////////////////////////////////////////////////////////////
			case Aidan:
				this.name = "Aidan";
				this.type = "Enlightened";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 70.0f;
				this.defense = 70.0f;
				this.speed = 85.0f;
				this.evasion = 1.0f;
				this.effect = new ArrayList<Effect>();
				this.moveset = new Move[4];
		        this.currentMove = new Move();
				
				moveset[0] = new Move(MoveName.Squint);
		        moveset[1] = new Move(MoveName.ChopstickBarrage);	 
		        moveset[2] = new Move(MoveName.ChopstickSlam);			
		        moveset[3] = new Move(MoveName.StateOfAscendancy);
		        break;
///////////////////////////////////////////////////////////////////////////////////////////////////
			case Alex:
				this.name = "Alex";
				this.type = "Cynic";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 50.0f;
				this.defense = 90.0f;
				this.speed = 70.0f;
				this.evasion = 1.0f;
				this.effect = new ArrayList<Effect>();
				this.moveset = new Move[4];
		        this.currentMove = new Move();
				
				moveset[0] = new Move(MoveName.Apathy);
		        moveset[1] = new Move(MoveName.DogmaticBurst);
		        moveset[2] = new Move(MoveName.LSD);
		        moveset[3] = new Move(MoveName.StateOfAscendancy);
		        break;
///////////////////////////////////////////////////////////////////////////////////////////////////
			case Hieu:
				this.name = "Hieu";
				this.type = "Moron";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 10.0f;
				this.defense = 10.0f;
				this.speed = 50.0f;
				this.evasion = 1.0f;
				this.effect = new ArrayList<Effect>();
				this.moveset = new Move[4];
				this.currentMove = new Move();
				
				moveset[0] = new Move(MoveName.Splash);
				moveset[1] = new Move(MoveName.Splash);
				moveset[2] = new Move(MoveName.Splash);
				moveset[3] = new Move(MoveName.Splash);
				break;
///////////////////////////////////////////////////////////////////////////////////////////////////
			case Jackson:
				this.name = "Jackson";
				this.type = "Meat";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 60.0f;
				this.defense = 95.0f;
				this.speed = 50.0f;
				this.evasion = 1.0f;
				this.effect = new ArrayList<Effect>();
				this.moveset = new Move[4];
				this.currentMove = new Move();
				
				moveset[0] = new Move(MoveName.Meatquake);
				moveset[1] = new Move(MoveName.Meatpin);
		        moveset[2] = new Move(MoveName.MeatDance);
		        moveset[3] = new Move(MoveName.Eat);
		        break;
///////////////////////////////////////////////////////////////////////////////////////////////////	     
			case Jeremiah:
				this.name = "Jeremiah";
				this.type = "Moron";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 30.0f;
				this.defense = 30.0f;
				this.speed = 30.0f;
				this.evasion = 1.0f;
				this.effect = new ArrayList<Effect>();
				this.moveset = new Move[4];
		        this.currentMove = new Move();
				
				moveset[0] = new Move(MoveName.AlmightyKush);
				moveset[1] = new Move(MoveName.SatanicMissionary);
		        moveset[2] = new Move(MoveName.Squint);
		        moveset[3] = new Move(MoveName.Dab);
		        break;
///////////////////////////////////////////////////////////////////////////////////////////////////	     
			case Kenny:
				this.name = "Kenny";
				this.type = "Musician";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 80.0f;
				this.defense = 65.0f;
				this.speed = 90.0f;
				this.evasion = 1.0f;
				this.effect = new ArrayList<Effect>();
				this.moveset = new Move[4];
				this.currentMove = new Move();
				
				moveset[0] = new Move(MoveName.DankSaxSolo);
				moveset[1] = new Move(MoveName.Dissonance);
				moveset[2] = new Move(MoveName.DogmaticBurst);
				moveset[3] = new Move(MoveName.SexySaxMan);
				break;
				
///////////////////////////////////////////////////////////////////////////////////////////////////	     
			case Noah:
				this.name = "Noah";
				this.type = "Meat";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 85.0f;
				this.defense = 65.0f;
				this.speed = 95.0f;
				this.evasion = 1.0f;
				this.effect = new ArrayList<Effect>();
				this.moveset = new Move[4];
				this.currentMove = new Move();
				
				moveset[0] = new Move(MoveName.ColorCorrection);
				moveset[1] = new Move(MoveName.MeatThrow);
				moveset[2] = new Move(MoveName.RKO);
				moveset[3] = new Move(MoveName.PoleDance);
				break;
///////////////////////////////////////////////////////////////////////////////////////////////////	     
			case Ryan:
				this.name = "Ryan";
				this.type = "Enlightened";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 80.0f;
				this.defense = 65.0f;
				this.speed = 99.0f;
				this.evasion = 1.0f;
				this.effect = new ArrayList<Effect>();
				this.moveset = new Move[4];
				this.currentMove = new Move();
				
				moveset[0] = new Move(MoveName.Squint);
				moveset[1] = new Move(MoveName.ChopstickBarrage);
				moveset[2] = new Move(MoveName.DivineChopstick);
				moveset[3] = new Move(MoveName.PickRice);
				break;
///////////////////////////////////////////////////////////////////////////////////////////////////	     
			case Trevor:
				this.name = "Trevor";
				this.type = "Enlightened";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 95.0f;
				this.defense = 55.0f;
				this.speed = 75.0f;
				this.evasion = 1.0f;
				this.effect = new ArrayList<Effect>();
				this.moveset = new Move[4];
				this.currentMove = new Move();
				
				moveset[0] = new Move(MoveName.Hack);
				moveset[1] = new Move(MoveName.DDoSAttack);
				moveset[2] = new Move(MoveName.DankMemes);
				moveset[3] = new Move(MoveName.GGRIP);
				break;
///////////////////////////////////////////////////////////////////////////////////////////////////	     
			case God:
				this.name = "God";
				this.type = "God";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 100.0f;
				this.defense = 100.0f;
				this.speed = 90.0f;
				this.evasion = 1.0f;
				this.effect = new ArrayList<Effect>();
				this.moveset = new Move[4];
		        this.currentMove = new Move();
				
		        moveset[0] = new Move(MoveName.AlmightyPush);
		        moveset[1] = new Move(MoveName.Mercy);
		        moveset[2] = new Move(MoveName.EstablishWrightocracy);
		        moveset[3] = new Move(MoveName.Enslave);
		        break;
///////////////////////////////////////////////////////////////////////////////////////////////////
			case Kevin:
				this.name = "Kevin";
				this.type = "Enlightened";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 50.0f;
				this.defense = 50.0f;
				this.speed = 50.0f;
				this.evasion = 1.0f;
				this.effect = new ArrayList<Effect>();
				this.moveset = new Move[4];
		        this.currentMove = new Move();
				
		        moveset[0] = new Move(MoveName.UseBathroom);
		        moveset[1] = new Move(MoveName.BlowNose);
		        moveset[2] = new Move(MoveName.GetWater);
		        moveset[3] = new Move(MoveName.OutOfExcuses);
		        break;
///////////////////////////////////////////////////////////////////////////////////////////////////
   
    	}

    }
    
    // Getters and Setters for attributes
    
    
    // Gets Compkemon move set and returns moves as a string
    public String getMoveset() {
    	String moves = "";
    	for (int i = 0; i < moveset.length; i++) {
    		moves += (i+1) + ". " + moveset[i] + "   "; 
    	}
    	return moves;
    }
    
    public void selectMove() {
    	Compkemon myCompkemon = Game.myCompkemon;
    	
    	int myMove = Integer.parseInt(Game.commandLine);
		switch (myMove) {
			case 1 : 
				myCompkemon.currentMove = myCompkemon.moveset[1];
				break;
			case 2 : 
				myCompkemon.currentMove = myCompkemon.moveset[2];
				break;
			case 3 : 
				myCompkemon.currentMove = myCompkemon.moveset[3];
				break;
			case 4 : 
				myCompkemon.currentMove = myCompkemon.moveset[4];
				break;
		}
    }
    
    public String toString() {
        String output = new String();
        output = this.name;
        return output;
    }

}