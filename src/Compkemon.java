public class Compkemon {

    String name;
    String type;
    Move[] moveset;
    int health;
    int currentHealth;
    float attack;
    float defense;
    int speed;
    
    CompkemonList compkemonList;
    
    // Zero arg Constructor
    public Compkemon() {
		this.name = "";
        this.type = "";
        this.health = 0;
        this.currentHealth = 0;
        this.attack = 0.0f;
        this.defense = 0.0f;
        this.speed = 0; 
        this.moveset = new Move[4];
    }
    
    // List of available Compkemon. Arg constructor for Compkemon
    public Compkemon(CompkemonList compkemon) {
    	
    	compkemonList = compkemon;
    	
    	switch(compkemon) {
			case Prototype: 
				this.name = "Prototype";
		        this.type = "Normal";
		        this.health = 100;
		        this.currentHealth = 100;
		        this.attack = 80.0f;
		        this.defense = 70.0f;
		        this.speed = 80; 
		        this.moveset = new Move[4];
		
		        moveset[0] = new Move(MoveName.Tackle);
		        moveset[1] = new Move(MoveName.Growl);
		        moveset[2] = new Move(MoveName.SwordsDance);
		        moveset[3] = new Move(MoveName.Splash);
		        break;
			case Wrightson:
				this.name = "Wrightson";
				this.type = "God";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 100.0f;
				this.defense = 100.0f;
				this.speed = 100;
				this.moveset = new Move[4];
				
		        moveset[0] = new Move(MoveName.AlmightyPush);
		        moveset[1] = new Move(MoveName.Mercy);
		        moveset[2] = new Move(MoveName.EstablishWrightocracy);
		        moveset[3] = new Move(MoveName.Enslave);
		        break;
			case Alex:
				this.name = "Alex";
				this.type = "Cynic";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 50.0f;
				this.defense = 90.0f;
				this.speed = 70;
				this.moveset = new Move[4];
				
				moveset[0] = new Move(MoveName.Apathy);
		        moveset[1] = new Move(MoveName.DogmaticBurst);
		        moveset[2] = new Move(MoveName.LSD);
		        moveset[3] = new Move(MoveName.StateOfAscendancy);
		        break;
			case Jeremiah:
				this.name = "Jeremiah";
				this.type = "Moron";
				this.health = 100;
				this.currentHealth = 100;
				this.attack = 30.0f;
				this.defense = 30.0f;
				this.speed = 30;
				this.moveset = new Move[4];
				
				moveset[0] = new Move(MoveName.Apathy);
		        moveset[1] = new Move(MoveName.DogmaticBurst);
		        moveset[2] = new Move(MoveName.LSD);
		        moveset[3] = new Move(MoveName.StateOfAscendancy);
		        break;
	        
    	}

    }
    
    // Getters and Setters for attributes
    
    public String getName() {
    	return name;
    }
    
    public String getType() {
    	return type;
    }
    
    public int getHealth() {
    	return health;
    }
    
    public void setHealth(int health) {
    	this.currentHealth = health;
    }
    
    public float getAttack() {
    	return attack;
    }
    
    public void setAttack(int attack) {
    	this.attack = attack;
    }
    
    public float getDefense() {
    	return defense;
    }
    
    public void setDefense(int defense) {
    	this.defense = defense;
    }
    
    public int getSpeed() {
    	return speed;
    }
    
    public void setSpeed(int speed) {
    	this.speed = speed;
    }
    
    // Gets Compkemon move set and returns moves as a string
    public String getMoveset() {
    	String moves = "";
    	for (int i = 0; i < moveset.length; i++) {
    		moves += (i+1) + ". " + moveset[i] + "\t"; 
    	}
    	return moves;
    }
    
    public String toString() {
        String output = new String();
        output = this.name;
        return output;
    }

}