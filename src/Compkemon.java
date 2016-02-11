public class Compkemon {

    String name;
    String type;
    Move[] moveset;
    int health;
    int currentHealth;
    int attack;
    int defense;
    int speed;
    
    CompkemonList compkemonList;
    
    // Zero arg Constructor
    public Compkemon() {
		this.name = "";
        this.type = "";
        this.health = 0;
        this.currentHealth = 0;
        this.attack = 0;
        this.defense = 0;
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
		        this.attack = 80;
		        this.defense = 80;
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
				this.attack = 100;
				this.defense = 100;
				this.speed = 100;
				this.moveset = new Move[4];
				
		        moveset[0] = new Move(MoveName.AlmightyPush);
		        moveset[1] = new Move();
		        moveset[2] = new Move();
		        moveset[3] = new Move();
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
    
    public int getAttack() {
    	return attack;
    }
    
    public void setAttack(int attack) {
    	this.attack = attack;
    }
    
    public int getDefense() {
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