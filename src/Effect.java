public class Effect {
	
	String name;
	EffectType effectType;
	int initTurnTracker;
	Compkemon user;
	Compkemon target;
	boolean finished;
	boolean didApplyThisTurn;
	
	Move tempMove;
	float tempFloat;
	
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
		this.tempMove = new Move();
		this.tempFloat = 0.0f;
	}
	
	public void Update() {
		
		float[][] typeTable = Game.typeTable.damageTable;
		int turnCounter = Game.turnCounter;
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
			case Dab :
				if (effectTurns == 0) {	
					System.out.println(target + " dabbed out of harm's way!");
					tempMove = target.currentMove;
					tempFloat = target.currentMove.accuracy;
					target.currentMove.accuracy = 0.0f;
					didApplyThisTurn = true;
				} else if (effectTurns == 1) {
					tempMove.accuracy = tempFloat;
					didApplyThisTurn= true;
					finished = true;
				}
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case Eat :
				System.out.println(user + " consumed a big piece of meat! ");
				System.out.println(user + " recovered health!");
				user.currentHealth += 33;
				if (user.currentHealth > 100) {
					user.currentHealth = 100;
				}
				didApplyThisTurn = true;
				finished = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case Enslave : 
				System.out.println(target + " was enslaved to the Wrightocracy!");
				System.out.println(target + "'s energy was drained!");
				float drain = 0.5f * (Game.damageCalculator(user, target, new Move(MoveName.Enslave)));
				user.currentHealth += (int)drain;
				if (user.currentHealth > 100) {
					user.currentHealth = 100;
				}
				didApplyThisTurn = true;
				finished = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case LSD :
				user.speed += 20;
				System.out.println(user + " understands things in a new light! Speed sharply increased!");
				didApplyThisTurn = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case MeatDance:
				user.attack += 20;
				System.out.println(user + "'s attack sharply rose!");
				didApplyThisTurn = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case Meatpin:
				target.speed -= 10;
				System.out.println(target + " was pinned down by" + user + "!");
				System.out.println(target + "'s speed decreased!");
				break;
////////////////////////////////////////////////////////////////////////////////////////////			
			case SatanicMissionary:
				String originalType = target.type;
				if (effectTurns == 0) {
					System.out.println(user + " has summoned the forces of darkness to convert the sane.");
					target.type = "Moron";
					didApplyThisTurn = true;
				} else if (effectTurns == 5) {
					System.out.println("Darkness has lifted");
					target.type = originalType;
					didApplyThisTurn = true;
					finished = true;
				}
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case Squint:
				if (effectTurns == 0) {	
					System.out.println(user + " can see with clarity!");
					didApplyThisTurn = true;
				} else if (effectTurns < 2) {
					tempMove = user.currentMove;
					tempFloat = user.currentMove.accuracy;
					user.currentMove.accuracy = 1.0f;
					didApplyThisTurn = true;
				} else if (effectTurns == 2) {
					tempMove.accuracy = tempFloat;
					didApplyThisTurn= true;
					finished = true;
				}
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
