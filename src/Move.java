public class Move {
    
	String attackName;
	String type;
    int power;
    float accuracy;
    boolean hasEffect;
    boolean toSelf;
    boolean lastingEffect;
    String effectAttribute;
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
    	this.effectAttribute = "";
    	this.description = "";
    }

    // Complete list of moves. Arg constructor for Move class
    public Move(MoveName name) {

        moveName = name;

        switch (moveName) {
	        case AlmightyKush:
	        	attackName = "Almighty Kush";
	        	type = "Moron";
	        	power = 100;
	        	accuracy = 1.0f;
	        	hasEffect = false;
	        	toSelf = false;
	        	lastingEffect = false;
	        	description = "Massive expulsion of kush. Only the moronic survive";
	        	break;
	        case AlmightyPush:
	        	attackName = "Almighty Push";
	        	type = "God";
	        	power = 150;
	        	accuracy = 1.0f;
	        	hasEffect = false;
	        	toSelf = false;
	        	lastingEffect = false;
	        	description = "Massive gravitational expulsion of energy. Only the fat survive";
	        	break;
	        case Apathy:
	        	attackName = "Apathy";
	        	type = "Cynic";
	        	power = 0;
	        	accuracy = 0.85f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = true;
	        	effectAttribute = "";
	        	description = "User stops caring. Damage multipliers against Cynic type are changed to 0.1. Exerts bad influence damage over time";
	        	break;
	        case Creator:
	        	attackName = "Creator";
	        	type = "God";
	        	power = 0;
	        	accuracy = 1.0f;
	        	hasEffect = false;
	        	toSelf = false;
	        	lastingEffect = false;
	        	effectAttribute = "";
	        	description = "Move for debugging. A God's creation...";
	        	break;
	        case Dab:
	        	attackName = "Dab";
	        	type = "Moron";
	        	power = 0;
	        	accuracy = 0.5f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = true;
	        	effectAttribute = "";
	        	description = "User dabs out of the way. Because it's a moronic move, it doesn't work very often";
	        	break;
	        case DogmaticBurst:
	        	attackName = "DogmaticBurst";
	        	type = "Enlightened";
	        	power = 90;
	        	accuracy = 1.0f;
	        	hasEffect = false;
	        	toSelf = false;
	        	lastingEffect = false;
	        	effectAttribute = "";
	        	description = "User lectures target";
	        	break;
	        case Enslave:
	        	attackName = "Enslave";
	        	type = "God";
	        	power = 70;
	        	accuracy = 100.0f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = false;
	        	effectAttribute = "";
	        	description = "Target is enslaved to Wrightson. Life force is enslaved, Wrigtson gains health";
	        	break;	        	
	        case EstablishWrightocracy:
	           	attackName = "Establish Wrightocracy";
	        	type = "God";
	        	power = 0;
	        	accuracy = 100.0f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = true;
	        	effectAttribute = "";
	        	description = "Wrightocracy is established over battle. 0.5 multipliers to 0.1 and 2.0 multipliers to 3.0";
	        	break;
	        case Growl:
	        	attackName = "Growl";
	        	type = "Meat";
	            power = 0;
	            accuracy = 1.0f;
	            hasEffect = true;
	            toSelf = false;
	            lastingEffect = false;
	            effectAttribute = "Attack";
	            description = "Opponent's attack lowered by 10";	            
	            modifier = 10;
	            break;
	        case Mercy:
	        	attackName = "Mercy";
	        	type = "God";
	            power = 0;
	            accuracy = 1.0f;
	            hasEffect = false;
	            toSelf = false;
	            lastingEffect = false;
	            description = "A God has mercy for His slaves";	            
	        	break;
	        case LSD:
	        	attackName = "LSD";
	        	type = "Enlightened";
	        	power = 0;
	        	accuracy = 100.0f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = false;
	        	effectAttribute = "Speed";
	        	description = "User understands everything in a new light. Speed sharply increases";
	        	break;	
	        case SatanicMissionary:
	        	attackName = "Satanic Missionary";
	        	type = "Moron";
	        	power = 0;
	        	accuracy = 0.75f;
	        	hasEffect = true;
	        	toSelf = false;
	        	lastingEffect = true;
	        	effectAttribute = "";
	        	description = "Target is converted to worship Satan. Target becomes Moronic type";
	        	break;
	        case Splash: 
	        	attackName = "Splash";
	        	type = "Moron";
	            power = 0;
	            accuracy = 1.0f;
	            hasEffect = false;
	            toSelf = false;
	            lastingEffect = false;
	            description = "You suck";
	            modifier = 0;
	            break;
	        case Squint:
	        	attackName = "Squint";
	        	type = "Enlightened";
	        	power = 0;
	        	accuracy = 1.0f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = true;
	        	effectAttribute = "Accuracy";
	        	description = "User squints and sees clearer. Next attack to hit without fail";
	        	break;
	        case StateOfAscendancy:
	        	attackName = "State of Ascendancy";
	        	type = "Cynic";
	        	power = 0;
	        	accuracy = 0.8f;
	        	hasEffect = true;
	        	toSelf = false;
	        	lastingEffect = true;
	        	effectAttribute = "";
	        	description = "Target is angered by inferiority, 50% chance attacking itself";
	        	break;	
	        case SwordsDance: 
            	attackName = "Swords Dance";
            	type = "Meat";
                power = 0;
                accuracy = 1.0f;
                hasEffect = true;
                toSelf = true;
                lastingEffect = false;
                effectAttribute = "Attack";
                description = "Attack boosted by 20";
                modifier = 20;
                break;       
            case Tackle:
            	attackName = "Tackle";
            	type = "Meat";
                power = 51;
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