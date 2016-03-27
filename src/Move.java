public class Move {
    
	String attackName;
	String type;
    int power;
    float accuracy;
    boolean hasEffect;
    boolean toSelf;
    boolean lastingEffect;
    String description;
    int priority;
    MoveName moveName;
    
    public Move() {
    	this.attackName = "-";
    	this.type = "";
    	this.power = 0;
    	this.accuracy = 0.0f;
    	this.hasEffect = false;
    	this.toSelf = false;
    	this.lastingEffect = false;
    	this.priority = 0;
    	this.description = "";
    }

    // Complete list of moves. Arg constructor for Move class
    public Move(MoveName name) {

        moveName = name;

        switch (moveName) {
	        case AlmightyKush:
	        	attackName = "Almighty Kush";
	        	type = "Moron";
	        	power = 75;
	        	accuracy = 0.75f;
	        	hasEffect = false;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
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
	        	priority = 0;
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
	        	priority = 0;
	        	description = "User stops caring. Damage multipliers against Cynic type are changed to 0.1. Exerts bad influence damage over time";
	        	break;
	        case ChopstickBarrage:
	        	attackName = "Chopstick Barrage";
	        	type = "Enlightened";
	        	power = 50;
	        	accuracy = 1.0f;
	        	hasEffect = true;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "A barrage of chopsticks are hurled at the foe";
	        	break;
	        case ChopstickSlam:
	        	attackName = "Chopstick Slam";
	        	type = "Enlightened";
	        	power = 40;
	        	accuracy = 1.0f;
	        	hasEffect = true;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "Foe is picked up and slammed down by a pair of chopsticks. Lowers defense.";
	        	break;
	        case ColorCorrection:
	        	attackName = "Color Correction";
	        	type = "Enlightened";
	        	power = 0;
	        	accuracy = 1.0f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "Fixes color blindness, attack sharply increases";
	        	break;
	        case Creator:
	        	attackName = "Creator";
	        	type = "God";
	        	power = 0;
	        	accuracy = 1.0f;
	        	hasEffect = false;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
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
	        	priority = 4;
	        	description = "User dabs out of the way. Because it's a moronic move, it doesn't work very often";
	        	break;
	        case DankMemes:
	        	attackName = "Dank Memes";
	        	type = "Cynic";
	        	power = 75;
	        	accuracy = 0.9f;
	        	hasEffect = true;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "Target subjected to the dankest of memes";
	        	break;
	        case DankSaxSolo:
	        	attackName = "Dank Sax Solo";
	        	type = "Musician";
	        	power = 50;
	        	accuracy = 0.9f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "User's soul is reinvigorated. Recovers health";
	        	break;
	        case DDoSAttack:
	        	attackName = "DDoS Attack";
	        	type = "Enlightened";
	        	power = 0;
	        	accuracy = 0.3f;
	        	hasEffect = true;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "Overwrites Opponent values to randomized stats";
	        	break;
	        case Dissonance:
	        	attackName = "Dissonance";
	        	type = "Musician";
	        	power = 70;
	        	accuracy = 0.8f;
	        	hasEffect = false;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "Bad pitch!!!!!";
	        	break;
	        case DogmaticBurst:
	        	attackName = "DogmaticBurst";
	        	type = "Enlightened";
	        	power = 80;
	        	accuracy = 0.9f;
	        	hasEffect = false;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "User lectures target";
	        	break;
	        case Eat:
	        	attackName = "Eat";
	        	type = "Meat";
	        	power = 0;
	        	accuracy = 0.7f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "Consumption of unfathomable amounts of food regenerates health";
	        	break;
	        case Enslave:
	        	attackName = "Enslave";
	        	type = "God";
	        	power = 20;
	        	accuracy = 100.0f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = false;
	        	priority = 0;
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
	        	priority = 0;
	        	description = "Wrightocracy is established over battle. 0.5 multipliers to 0.1 and 2.0 multipliers to 3.0";
	        	break;
	        case GGRIP:
	        	attackName = "GG RIP";
	        	type = "Enlightened";
	        	power = 0;
	        	accuracy = 0.25f;
	        	hasEffect = true;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "GG RIP";
	        	break;
	        case Hack:
	        	attackName = "Hack";
	        	type = "Enlightened";
	        	power = 0;
	        	accuracy = 1.0f;
	        	hasEffect = true;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "User hacks for more attack";
	        	break;
	        case Mercy:
	        	attackName = "Mercy";
	        	type = "God";
	            power = 0;
	            accuracy = 1.0f;
	            hasEffect = false;
	            toSelf = false;
	            lastingEffect = false;
	            priority = 0;
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
	        	priority = 0;
	        	description = "User understands everything in a new light. Speed sharply increases";
	        	break;	
	        case MeatDance:
	        	attackName = "Meat Dance";
	        	type = "Meat";
	        	power = 0;
	        	accuracy = 1.0f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "Ritual dance performed by flailing meaty arms in every which way. Boosts spirit. Attack sharply increases";
	        	break;
	        case Meatpin:
	        	attackName = "Meatpin";
	        	type = "Meat";
	        	power = 40;
	        	accuracy = 1.0f;
	        	hasEffect = true;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "Target is pinned by a big piece of meat. Target's speed lowered";
        		break;
	        case MeatThrow:
	        	attackName = "Meat Throw";
	        	type = "Meat";
	            power = 60;
	            accuracy = 1.0f;
	            hasEffect = false;
	            toSelf = false;
	            lastingEffect = false;
	            priority = 0;
	            description = "Calculated throw. Rarely misses";	            
	        	break;
	        case Meatquake:
	        	attackName = "Meatquake";
	        	type = "Meat";
	        	power = 70;
	        	accuracy = 1.0f;
	        	hasEffect = false;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "Massive quake created by a big piece of meat.";
        		break;
	        case PoleDance:
	        	attackName = "Pole(vault) Dance";
	        	type = "Meat";
	        	power = 0;
	        	accuracy = 0.8f;
	        	hasEffect = true;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "Nice pole dance.";
        		break;
	        case RKO:
	        	attackName = "RKO";
	        	type = "Meat";
	        	power = 90;
	        	accuracy = 0.3f;
	        	hasEffect = false;
	        	toSelf = false;
	        	lastingEffect = false;
	        	priority = 0;
	        	description = "RKO!!!!";
        		break;
	        case SatanicMissionary:
	        	attackName = "Satanic Missionary";
	        	type = "Moron";
	        	power = 0;
	        	accuracy = 0.75f;
	        	hasEffect = true;
	        	toSelf = false;
	        	lastingEffect = true;
	        	priority = 0;
	        	description = "Target is converted to worship Satan. Target becomes Moronic type";
	        	break;
	        case SexySaxMan:
	        	attackName = "Sexy Sax Man";
	        	type = "Musician";
	        	power = 0;
	        	accuracy = 0.75f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = true;
	        	priority = 0;
	        	description = "User becomes sexy for a few turns";
	        	break;
	        case Splash: 
	        	attackName = "Splash";
	        	type = "Moron";
	            power = 0;
	            accuracy = 1.0f;
	            hasEffect = true;
	            toSelf = false;
	            lastingEffect = false;
	            priority = 0;
	            description = "You suck";
	            break;
	        case Squint:
	        	attackName = "Squint";
	        	type = "Enlightened";
	        	power = 0;
	        	accuracy = 1.0f;
	        	hasEffect = true;
	        	toSelf = true;
	        	lastingEffect = true;
	        	description = "User squints and sees clearer. Next attack to hit without fail";
	        	break;
	        case StateOfAscendancy:
	        	attackName = "State of Ascendancy";
	        	type = "Cynic";
	        	power = 0;
	        	accuracy = 0.6f;
	        	hasEffect = true;
	        	toSelf = false;
	        	lastingEffect = true;
	        	priority = 3;
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
                priority = 0;
                description = "Attack boosted by 20";
                break;       
            case Tackle:
            	attackName = "Tackle";
            	type = "Meat";
                power = 50;
                accuracy = .95f;
                hasEffect = false;
                lastingEffect = false;
                priority = 0;
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

    
    // returns effect if there is one 
    public Effect getEffect(Compkemon user, Compkemon target) {
    	Effect effect = new Effect();
    	int effectInitCounter = Game.turnCounter;
    	switch (this.attackName) {
    		case "Apathy" :
    			effect = new Effect(EffectType.Apathy, user, target, effectInitCounter);
    			break;
    		case "Chopstick Barrage" :
    			effect = new Effect(EffectType.ChopstickBarrage, user, target, effectInitCounter);
    			break;
    		case "Chopstick Slam" :
    			effect = new Effect(EffectType.ChopstickSlam, user, target, effectInitCounter);
    			break;
    		case "Color Correction" :
    			effect = new Effect(EffectType.ColorCorrection, user, target, effectInitCounter);
    			break;
    		case "Dab" :
    			effect = new Effect(EffectType.Dab, user, target, effectInitCounter);
    			break;
    		case "Dank Memes" :
    			effect = new Effect(EffectType.DankMemes, user, target, effectInitCounter);
    			break;
    		case "Dank Sax Solo" :
    			effect = new Effect(EffectType.DankSaxSolo, user, target, effectInitCounter);
    			break;
    		case "DDoS Attack" :
    			effect = new Effect(EffectType.DDoSAttack, user, target, effectInitCounter);
    			break;
    		case "Eat" :
    			effect = new Effect(EffectType.Eat, user, target, effectInitCounter);
    			break;
    		case "Enslave" :
    			effect = new Effect(EffectType.Enslave, user, target,effectInitCounter);
    			break;
    		case "Establish Wrightocracy" :
    			effect = new Effect(EffectType.EstablishWrightocracy, user, target,effectInitCounter);
    			break;
    		case "GG RIP" :
    			effect = new Effect(EffectType.GGRIP, user, target,effectInitCounter);
    			break;
    		case "Hack" :
    			effect = new Effect(EffectType.Hack, user, target,effectInitCounter);
    			break;
    		case "LSD" : 
    			effect = new Effect(EffectType.LSD, user, target, effectInitCounter);
    			break;
    		case "Meat Dance" :
    			effect = new Effect(EffectType.MeatDance, user, target, effectInitCounter);
    			break;
    		case "Meatpin" :
    			effect = new Effect(EffectType.Meatpin, user, target, effectInitCounter);
    			break;
    		case "Pole(vault) Dance" :
    			effect = new Effect(EffectType.PoleDance, user, target,effectInitCounter);
    			break;
    		case "Satanic Missionary" :
    			effect = new Effect(EffectType.SatanicMissionary, user, target, effectInitCounter);
    			break;
    		case "Sexy Sax Man" :
    			effect = new Effect(EffectType.SexySaxMan, user, target, effectInitCounter);
    			break;
    		case "Splash" :
    			effect = new Effect(EffectType.Splash, user, target, effectInitCounter);
    			break;
    		case "Squint":
    			effect = new Effect(EffectType.Squint, user, target, effectInitCounter);
    			break;
    		case "State of Ascendancy" : 
    			effect = new Effect(EffectType.StateOfAscendancy, user, target, effectInitCounter);
    			break;
    	}
    	return effect;	
    }

    // toString method that prints out the attack name when called
    public String toString() {
    	String output = attackName;
    	return output;
    }
    
}