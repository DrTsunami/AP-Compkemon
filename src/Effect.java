
public class Effect {
	
	String name;
	EffectType effectType;
	int initTurnTracker;
	Compkemon user;
	Compkemon target;
	boolean finished;
	boolean didApplyThisTurn;
	
	
	public Effect() {
		// empty 
	}
	

	
	public Effect (EffectType effect, Compkemon user , Compkemon target, int initTurn) {
		effectType = effect;		
		this.user = user;
		this.target = target;
		this.initTurnTracker = initTurn;
		this.didApplyThisTurn = false;
		this.finished = false;
	}
	
	public void Update() {
		
		float[][] typeTable = Main.typeTable.damageTable;
		int turnCounter = Main.turnCounter;
		int effectTurns = turnCounter - initTurnTracker;
		
		switch (effectType) {
////////////////////////////////////////////////////////////////////////////////////////////
			case Apathy :
				if (effectTurns == 0) {
					System.out.println("Alex became apathetic!");				
					for(int i = 0; i < typeTable[2].length; i++) {
						typeTable[2][i] = 0.1f;
					}
				} else if (effectTurns < 5) {
					System.out.println("Alex is exerting bad influence. Turn: " + effectTurns);
					target.currentHealth -= 8;
					
				} else if (effectTurns == 5) {
					System.out.println("Alex started caring a little bit. End Effect Turn: " + effectTurns);
					for(int i = 0; i < typeTable[2].length; i++) {
						typeTable[2][i] = 0.5f;
					}
					finished = true;					
				}				
				didApplyThisTurn = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case Enslave : 
				System.out.println(target + " was enslaved to the Wrightocracy!");
				System.out.println(target + "'s energy was drained!");
				float drain = 0.5f * (Main.damageCalculator(user, target, new Move(MoveName.Enslave)));
				user.currentHealth += (int)drain;
				if (user.currentHealth > 100) {
					user.currentHealth = 100;
				}
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case LSD :
				user.speed += 20;
				System.out.println("User understands things in a new light! Speed sharply increased!");
				didApplyThisTurn = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////				
			case StateOfAscendancy : 
				float accuracy = (float) Math.random();
				System.out.println(target + " is feeling inferior to " + user);								
				if (accuracy > 0.5f) {
					target.currentMove = null;
					System.out.println("Target move set to null");
					System.out.println(target + " attacked itself in its fury");
					target.currentHealth -= 20;
				} 							
				finished = true;
				didApplyThisTurn = true;
				break;
		}
	}
}
