public class Move {
    
	String attackName;
	String type;
    int power;
    float accuracy;
    boolean hasEffect;
    boolean toSelf;
    boolean lastingEffect;
    String description;
    int modifier;
    MoveName moveName;
    
    public Move() {
    	this.attackName = "-";
    	this.type = "";
    	this.power = 0;
    	this.accuracy = 0.0f;
    	this.hasEffect = false;
    	this.toSelf = false;
    	this.lastingEffect = false;
    	this.description = "";
    }

    // Complete list of moves. Arg constructor for Move class
    public Move(MoveName name) {

        moveName = name;

        switch (moveName) {
	        case AlmightyPush:
	        	attackName = "Almighty Push";
	        	type = "God";
	        	power = 100;
	        	accuracy = 1.0f;
	        	hasEffect = false;
	        	toSelf = false;
	        	lastingEffect = false;
	        	description = "Massive gravitational expulsion of energy. Only the fat survive";
	        	break;
	        case Growl:
	        	attackName = "Growl";
	        	type = "Normal";
	            power = 0;
	            accuracy = 1.0f;
	            hasEffect = true;
	            toSelf = false;
	            lastingEffect = false;
	            description = "Opponent's attack lowered by 10";
	            modifier = -10;
	            break;
	        case Splash: 
	        	attackName = "Splash";
	        	type = "Normal";
	            power = 0;
	            accuracy = 1.0f;
	            hasEffect = false;
	            toSelf = false;
	            lastingEffect = false;
	            description = "You suck";
	            modifier = 0;
	            break;
	        case SwordsDance: 
            	attackName = "Swords Dance";
            	type = "Normal";
                power = 0;
                accuracy = 1.0f;
                hasEffect = true;
                toSelf = true;
                lastingEffect = false;
                description = "Attack boosted by 20";
                modifier = 20;
                break;       
            case Tackle:
            	attackName = "Tackle";
            	type = "Normal";
                power = 50;
                accuracy = .95f;
                hasEffect = false;
                lastingEffect = false;
                description = "The most mediocre attack in the world. User rushes recklessly at target";
                break;    
        }          
    }
    
    // Getters and Setters for move attributes
    
    public String getName() {
    	return attackName;
    }    	
    
    public String getType() {
    	return type;
    }
    
    public int getPower() {
    	return power;
    }
    
    public float getAccuracy() {
    	return accuracy;
    }
    
    public boolean isToSelf() {
    	return toSelf;
    }
    
    public boolean hasLastingEffect() {
    	return lastingEffect;
    }
    
    public boolean doesHaveEffect() {
    	return hasEffect;
    }
    
    public String getDescription() {
    	return description;    	
    }
    
    public int getModifier() {
    	return modifier;
    }

    // toString method that prints out the attack name when called
    public String toString() {
    	String output = attackName;
    	return output;
    }
    
}