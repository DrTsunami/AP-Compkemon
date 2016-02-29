
public class Effect {
	
	String name;
	EffectType effectType;
	int initTurnTracker;
	Compkemon user;
	Compkemon target;
	boolean finished;
	
	
	public Effect() {
		// empty 
	}
	

	
	public Effect (EffectType effect, Compkemon user , Compkemon target, int initTurn) {
		effectType = effect;		
		this.user = user;
		this.target = target;
		this.initTurnTracker = initTurn;

		this.finished = false;
	}
	
	
	public void Update() {
		
		float[][] typeTable = Main.typeTable.damageTable;
		int turnCounter = Main.turnCounter;
		int effectTurns = turnCounter - initTurnTracker;
		
		switch (effectType) {
			case Apathy :
				if (effectTurns == 0) {
					System.out.println("Alex became apathetic!");				
					for(int i = 0; i < typeTable[2].length; i++) {
						typeTable[2][i] = 0.1f;
					}
				} else if (effectTurns < 5) {
					System.out.println("Alex is exerting bad influence. Turn: " + effectTurns);
					
				} else if (effectTurns == 5) {
					System.out.println("Alex started caring a little bit. End Effect Turn: " + effectTurns);
					for(int i = 0; i < typeTable[2].length; i++) {
						typeTable[2][i] = 0.5f;
					}
					finished = true;					
				}				
				break;
			case LSD :
				user.speed += 20;
				System.out.println("User understands things in a new light! Speed sharply increased!");
				break;
			case StateOfAscendancy : 
				float accuracy = (float) Math.random();
				System.out.println(target + "is feeling inferior to" + user);
				if (accuracy > 0.5f) {
					// TODO State of Ascendancy handlers
				} else {
					
				}
				break;
		
		}
	}
}
