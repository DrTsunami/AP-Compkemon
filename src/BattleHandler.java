import javax.swing.*;

public class BattleHandler {
	
	static TypeTable typeTable = Game.typeTable;
	
	public static Compkemon randomEnemy() {
		
		Compkemon enemy = new Compkemon();
		int random = (int)(Math.random() * 12);
		
		switch(random) {
			case 1:
				enemy =  new Compkemon(CompkemonList.Prototype);
				break;
			case 2:
				enemy =  new Compkemon(CompkemonList.Aidan);
				break;
			case 3:
				enemy = new Compkemon(CompkemonList.Alex);
				break;
			case 4:
				enemy = new Compkemon(CompkemonList.Hieu);
				break;
			case 5:
				enemy = new Compkemon(CompkemonList.Jackson);
				break;
			case 6:
				enemy = new Compkemon(CompkemonList.Jeremiah);
				break;
			case 7:
				enemy = new Compkemon(CompkemonList.Kenny);
				break;
			case 8:
				enemy = new Compkemon(CompkemonList.Noah);
				break;
			case 9:
				enemy = new Compkemon(CompkemonList.Ryan);
				break;
			case 10:
				enemy = new Compkemon(CompkemonList.Trevor);
				break;
			case 11:
				// TODO change wrightson's name to God
				enemy = new Compkemon(CompkemonList.God);
				break;
		}
		return enemy;
	}
	
	public static Compkemon customEnemy(String name) {
		Compkemon customEnemy = new Compkemon();
		
		switch(name) {
			case "prototype":
				customEnemy =  new Compkemon(CompkemonList.Prototype);
				break;
			case "aidan":
				customEnemy =  new Compkemon(CompkemonList.Aidan);
				break;
			case "alex":
				customEnemy = new Compkemon(CompkemonList.Alex);
				break;
			case "hieu":
				customEnemy = new Compkemon(CompkemonList.Hieu);
				break;
			case "jackson":
				customEnemy = new Compkemon(CompkemonList.Jackson);
				break;
			case "jeremiah":
				customEnemy = new Compkemon(CompkemonList.Jeremiah);
				break;
			case "kenny":
				customEnemy = new Compkemon(CompkemonList.Kenny);
				break;
			case "noah":
				customEnemy = new Compkemon(CompkemonList.Noah);
				break;
			case "ryan":
				customEnemy = new Compkemon(CompkemonList.Ryan);
				break;
			case "trevor":
				customEnemy = new Compkemon(CompkemonList.Trevor);
				break;
			case "god":
				// TODO change wrightson's name to God
				customEnemy = new Compkemon(CompkemonList.God);
				break;
			default:
				customEnemy = null;
				JOptionPane.showConfirmDialog(null, "You don't know how to type");
				break;
		}
		return customEnemy;
		// FIXME set customenemy to null at the end of the game
	}
	
	
	
	// Determines Speed and Priority
	
	public static int priorityCalculator(Compkemon user, Move userMove, Compkemon enemy, Move enemyMove) {
		int priority = 0;
		
		if (userMove.priority > enemyMove.priority) {
			priority = 1;
		} else if (userMove.priority < enemyMove.priority) {
			priority = 0;
		} else if (userMove.priority == enemyMove.priority) {
			if (user.speed > enemy.speed) {
				priority = 1;
			} else if  (user.speed < enemy.speed) {
				priority = 0;
			}
		}		
		return priority;
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Method that displays health bar
	public static void displayHealth(Compkemon user, Compkemon enemy) {
		
		
		
		
		//*			OLD CONSOLE DISPLAY
		
		// Print user health bar
		System.out.print(user + " HP: " + user.currentHealth + "/" + user.health + "\t" + "[");
		for (int i = 0; i < user.health; i = i+3) {
			if (user.currentHealth <= 0) {
				System.out.print(" ");
			} else {
				if (i < user.currentHealth) {
					System.out.print("/");	
				} else {
					System.out.print(" ");
				}	
			}				
		}		
		System.out.print("]");		
		System.out.println(); // insert line

		
		// Print enemy health bar
		System.out.print(enemy + " HP: " + enemy.currentHealth + "/" + enemy.health + "\t" + "[");
		for (int i = 0; i < enemy.health; i = i+3) {
			if (enemy.currentHealth <= 0) {
				System.out.print(" ");
			} else {
				if (i < enemy.currentHealth) {
					System.out.print("/");	
				} else {
					System.out.print(" ");
				}
			}					
		}
		System.out.print("]");		
		System.out.println(); // insert line
		
		//*/
		
		
	
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static float damageCalculator(Compkemon user, Compkemon target, Move userMove) {
		
		float damage = 0.0f;
		float userAttack = user.attack;
		float targetDefense = target.defense;		
		
		damage = (int)((.85 * ((userAttack)/(targetDefense)) * (userMove.power)) * damageMultiplier(user, target, userMove));
		damage = (int)((float)damage * 0.6f);
		return damage;
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static float damageMultiplier(Compkemon user, Compkemon target, Move userMove) {
		
		float multiplier = 0.0f;
		int moveType = 0;
		int targetType = 0;		
		
		float typeMultiplier = 0.0f;
		float sameTypeMultiplier = 0.0f;

		
		switch (userMove.type) {
			case "Moron" :
				moveType = 0;
				break;
			case "Meat" : 
				moveType = 1;				
				break;
			case "Cynic" :
				moveType = 2;
				break;
			case "Enlightened" :
				moveType = 3;
				break;
			case "Musician" :
				moveType = 4;
				break;
			case "God" :
				moveType = 5;
				break;				
		}
		
		switch (target.type) {
			case "Moron" :
				targetType = 0;
				break;
			case "Meat" : 
				targetType = 1;				
				break;
			case "Cynic" :
				targetType = 2;
				break;
			case "Enlightened" :
				targetType = 3;
				break;
			case "Musician" :
				targetType = 4;
				break;
			case "God" :
				targetType = 5;
				break;				
		}
		
		if (userMove.type.equals(user.type)) {
			sameTypeMultiplier = 1.5f;
		} else {
			sameTypeMultiplier = 1.0f;
		}		
		typeMultiplier = typeTable.getMultiplier(moveType, targetType);	
		
		multiplier = typeMultiplier * sameTypeMultiplier;
		
		System.out.println("Multiplier is : " + multiplier);
		
		// Print out multiplier statement
		if (typeMultiplier == 0.1f) {
			Game.textBox.AnimateText("It pales in comparison to a God!", false);
		} else if (typeMultiplier == 0.5f) {
			Game.textBox.AnimateText("It's not very effective", false);
		} else if (typeMultiplier == 1.0f) {
			// Normal damage
		} else if (typeMultiplier == 2.0f) {
			Game.textBox.AnimateText("It's super-effective!", false);
		} else if (typeMultiplier == 3.0f) {
			Game.textBox.AnimateText(target + " has been subjected to the Wrightocracy!", false);
		} else if (typeMultiplier == 10.0f) {
			Game.textBox.AnimateText("A God has been converted to worship Satanism! It's ultra-effective!!", false);
		} else if (typeMultiplier == 0.0f) {
			Game.textBox.AnimateText("It doesn't effect " + target + "..." , false);
		}

		return multiplier;		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
		
	public static boolean hitMiss(Move move) {
		boolean didHit = false;
		float accuracy = move.accuracy;
		float percentCalc = (float)Math.random();
		
		if (percentCalc <= accuracy) {
			didHit = true;
		}
		
		return didHit;
	
	}


}
