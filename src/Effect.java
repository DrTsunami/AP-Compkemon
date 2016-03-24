public class Effect {
	
	TextBox textBox = Game.textBox;
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
	

	// TODO Need to start fixing current effects and add new effects
	
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
					textBox.AnimateText("Alex became apathetic!", false);				
					for(int i = 0; i < typeTable[2].length; i++) {
						typeTable[2][i] = 0.1f;
					}
				} else if (effectTurns < 5) {
					textBox.AnimateText("Alex is exerting bad influence. Turn: " + effectTurns, false);
					target.currentHealth -= 8;					
				} else if (effectTurns == 5) {
					textBox.AnimateText("Alex started caring a little bit. End Effect Turn: " + effectTurns, false);
					for(int i = 0; i < typeTable[2].length; i++) {
						typeTable[2][i] = 0.5f;
					}
					finished = true;					
				}				
				didApplyThisTurn = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case Dab :
				if (effectTurns == 0) {	
					textBox.AnimateText(user + " dabbed out of harm's way!", false);
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
				textBox.AnimateText(user + " consumed a big piece of meat! ", false);
				textBox.AnimateText(user + " recovered health!", false);
				user.currentHealth += 33;
				if (user.currentHealth > 100) {
					user.currentHealth = 100;
				}
				didApplyThisTurn = true;
				finished = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case Enslave : 
				textBox.AnimateText(target + " was enslaved to the Wrightocracy!", false);
				textBox.AnimateText(target + "'s energy was drained!", false);
				float drain = 0.5f * (BattleHandler.damageCalculator(user, target, new Move(MoveName.Enslave)));
				user.currentHealth += (int)drain;
				if (user.currentHealth > 100) {
					user.currentHealth = 100;
				}
				didApplyThisTurn = true;
				finished = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case EstablishWrightocracy:
				if (effectTurns == 0) {
					textBox.AnimateText("A Wrightocracy has been established", false);	
					for(int i = 0; i < typeTable[2].length; i++) {
						typeTable[5][i] = 3.0f;
						typeTable[i][5] = 0.1f;
					}
				} else if (effectTurns < 5) {
					textBox.AnimateText("A Wrightocracy looms over the area. Turn: " + effectTurns, false);;					
				} else if (effectTurns == 5) {
					textBox.AnimateText("The Wrightocracy has been lifted. End Effect Turn: " + effectTurns, false);
					typeTable[5][0] = 2.0f;
					typeTable[5][1] = 2.0f;
					typeTable[5][2] = 2.0f;
					typeTable[5][3] = 2.0f;
					typeTable[5][4] = 2.0f;
					typeTable[5][5] = 1.0f;
					typeTable[0][5] = 10.0f;
					typeTable[1][5] = 0.5f;
					typeTable[2][5] = 1.0f;
					typeTable[3][5] = 0.5f;
					typeTable[4][5] = 0.5f;
					finished = true;					
				}				
				didApplyThisTurn = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case LSD :
				user.speed += 20;
				textBox.AnimateText(user + " understands things in a new light! Speed sharply increased!", false);
				finished = true;
				didApplyThisTurn = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case MeatDance:
				user.attack += 20;
				textBox.AnimateText(user + "'s attack sharply rose!", false);
				finished = true;
				didApplyThisTurn = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case Meatpin:
				target.speed -= 10;
				textBox.AnimateText(target + " was pinned down by " + user + "!", false);
				textBox.AnimateText(target + "'s speed decreased!", false);
				finished = true;
				didApplyThisTurn = true;
				break;
////////////////////////////////////////////////////////////////////////////////////////////			
			case SatanicMissionary:
				String originalType = target.type;
				if (effectTurns == 0) {
					textBox.AnimateText(user + " has summoned the forces of darkness to convert the sane.", false);
					target.type = "Moron";
					didApplyThisTurn = true;
				} else if (effectTurns < 5) { 
					textBox.AnimateText(target + " is on its knees worshiping Satan.", false);
					didApplyThisTurn = true;
				} else if (effectTurns == 5) {
					textBox.AnimateText("Darkness has lifted", false);
					target.type = originalType;
					didApplyThisTurn = true;
					finished = true;
				}
				break;
////////////////////////////////////////////////////////////////////////////////////////////
			case Squint:
				if (effectTurns == 0) {	
					textBox.AnimateText(user + " can see with clarity!", false);
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
				textBox.AnimateText(target + " is feeling inferior to " + user, false);								
				if (accuracy > 0.5f) {
					target.currentMove = null;
					textBox.AnimateText("Target move set to null", false);
					textBox.AnimateText(target + " attacked itself in its fury", false);
					target.currentHealth -= 20;
				} 							
				finished = true;
				didApplyThisTurn = true;
				Game.ready = true;
				break;
		}
	}
}
