public class Move {
    
	String attackName;
	String type;
    int power;
    float accuracy;
    boolean hasEffect;
    boolean toSelf;
    String effect;
    int modifier;
    MoveName moveName;

    public Move(MoveName name) {

        moveName = name;

        switch (moveName) {
            case Tackle:
            	attackName = "Tackle";
                power = 50;
                accuracy = .95f;
                hasEffect = false;
                break;
            case Growl:
            	attackName = "Growl";
                power = 0;
                accuracy = 1.0f;
                hasEffect = true;
                toSelf = false;
                effect = "Opponent's attack lowered by 10";
                modifier = -10;
                break;
            case SwordsDance: 
            	attackName = "Swords Dance";
                power = 0;
                accuracy = 1.0f;
                hasEffect = true;
                toSelf = true;
                effect = "Attack boosted by 20";
                modifier = 20;
                break;                
        }          
    }
    
    public String toString() {
    	String output = attackName;
    	return output;
    }
    
}