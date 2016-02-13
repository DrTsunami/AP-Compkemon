import java.util.Scanner;

public class Main {
	
	static Scanner scanner = new Scanner(System.in);
	static Compkemon myCompkemon;
	static Compkemon enemy;
	
	public static void main(String[] args) {
		
		int myCompkemonScanned = 0;
		myCompkemon = new Compkemon();
		enemy = new Compkemon();
		
		// Gets user input for compkemon
		System.out.println("1. Prototype" + "\n" + "2. Wrightson" + "\n" + 
							"Enter number corresponding to the Compkemon you wish to hack with: ");
		myCompkemonScanned = scanner.nextInt();
		
		switch(myCompkemonScanned) {
			case 1:
				myCompkemon =  new Compkemon(CompkemonList.Prototype);
				break;
			case 2:
				myCompkemon =  new Compkemon(CompkemonList.Wrightson);
				break;
		}
		
		System.out.println("Congratulations, your chosen Compkemon is: " + myCompkemon);
		
		// set enemy compkemon here;
		enemy = new Compkemon(CompkemonList.Prototype);
		
		System.out.println("An enemy Compkemon hacked! You are under attack!");
		System.out.println("A wild " + enemy + " appeared!");
		System.out.println("Fight!");
		
		battleScene(myCompkemon, enemy);
		
		System.out.println("Battle has concluded");	
	} // end main method
	
	
	public static void battleScene(Compkemon myCompkemon, Compkemon enemy) {
		
		int myMove;
		int enemyMove;
		Compkemon loser = new Compkemon();
		
		while (myCompkemon.currentHealth > 0 || enemy.currentHealth > 0) {
			
			System.out.println("Choose move: " + myCompkemon.getMoveset());
			myMove = scanner.nextInt();	
			enemyMove = (int)(Math.random() * enemy.moveset.length);
			
			Move myMoveUsed = myCompkemon.moveset[myMove - 1];
			Move enemyMoveUsed = enemy.moveset[enemyMove];
			
			if (myCompkemon.speed > enemy.speed) {	
				// ---------- USER FASTER THAN ENEMY ----------
				System.out.println(myCompkemon + " used " + myMoveUsed);
				
				// Alpha damage calculator and applier
				if (myMoveUsed.power > 0) {
					System.out.println(enemy + " took damage!");
					enemy.setHealth(enemy.currentHealth - ((int)damageCalculator(myCompkemon, enemy, myMoveUsed)));
					if (enemy.currentHealth <= 0) {
						System.out.println(enemy + " HP: 0");
					} else {
						System.out.println(enemy + " HP: " + enemy.currentHealth);
					}
				}
				
				
				// Check for move effect. If true, effects are applied
				if (myMoveUsed.hasEffect) {
					passiveModifier(myCompkemon, enemy, myMoveUsed);
				}
				
				// Splash salute 
				/*
				if (!myMoveUsed.hasEffect || myMoveUsed.power == 0) {
					System.out.println("Nothing happened. You literally suck.");
				}
				*/
				
				// Check for enemy health. If fainted, end the game
				if (enemy.currentHealth <= 0) {
					loser = enemy;
					break;					
				}
				
				// Enemy move begin
				System.out.println(enemy + " used " + enemyMoveUsed);				

				// Alpha damage calculator and applier
				if (enemyMoveUsed.power > 0) {
					System.out.println(myCompkemon + " took damage!");
					myCompkemon.setHealth(myCompkemon.currentHealth - ((int)damageCalculator(enemy, myCompkemon, enemyMoveUsed)));
					if (myCompkemon.currentHealth <= 0) {
						System.out.println(myCompkemon + " HP: 0");
					} else {
						System.out.println(myCompkemon + " HP: " + myCompkemon.currentHealth);
					}

				}
				
				// Check for move effect. If true, effects are applied
				if (enemyMoveUsed.hasEffect) {
					passiveModifier(enemy, myCompkemon, enemyMoveUsed);
				}
				
				// Splash salute
				/*
				if (!enemyMoveUsed.hasEffect || enemyMoveUsed.power == 0) {
					System.out.println("Nothing happened. The enemy literally sucks.");
				}
				*/
				
				// Check for user health. If fainted, end the game
				if (myCompkemon.currentHealth <= 0) {
					loser = myCompkemon;
					break;					
				}
				
			} else {
				// ---------- USER SLOWER THAN ENEMY ----------				
			}
		}
		
		System.out.println(loser + " has fainted");
	} // end battleScene
	
	public static void passiveModifier(Compkemon user, Compkemon target, Move moveUsed) {		
		
		if (moveUsed.toSelf) {
			switch (moveUsed.effectAttribute) {
				case "Attack":
					if (moveUsed.modifier == 10) {
						System.out.println(user + "'s" + " Attack increased!");
						user.attack += moveUsed.modifier;
					} else if (moveUsed.modifier == 20) {
						System.out.println(user + "'s" + " Attack sharply increased!");
						user.attack += moveUsed.modifier;						
					}
					break;		
			}
		} else {
			switch (moveUsed.effectAttribute) {
				case "Attack":
					if (moveUsed.modifier == 10) {
						System.out.println(target + "'s" + " Attack decreased!");
						target.attack -= moveUsed.modifier;
					} else if (moveUsed.modifier == 20) {
						System.out.println(target + "'s" + " Attack sharply decreased!");
						target.attack -= moveUsed.modifier;
					}
					break;
			}
		}
		
	} // end passiveModifier
	
	public static float damageCalculator(Compkemon user, Compkemon target, Move userMove) {
		
		float damage = 0.0f;
		float userAttack = user.getAttack();
		float targetDefense = target.getDefense();		
		
		damage = (int)(.85 * ((userAttack)/(targetDefense)) * (userMove.power));
		
		return damage;
		
	}
	
	public static float damageMultiplier(Compkemon user, Compkemon target, Move userMove) {
		
		float multiplier = 0.0f;
		int userType = 0;
		int targetType = 0;		
		float[][] damageTable = new float[6][6];
		
		switch (user.type) {
			case "Moron" :
				userType = 0;
				break;
			case "Meat" : 
				userType = 1;				
				break;
			case "Cynic" :
				userType = 2;
				break;
			case "Enlightened" :
				userType = 3;
				break;
			case "Musician" :
				userType = 4;
				break;
			case "God" :
				userType = 5;
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
		
		damageTable[0][0] = ;
		damageTable[0][1] = ;
		damageTable[0][2] = ;
		damageTable[0][3] = ;
		damageTable[0][4] = ;
		damageTable[0][5] = ;
		damageTable[1][0] = ;
		damageTable[1][1] = ;
		damageTable[1][2] = ;
		damageTable[1][3] = ;
		damageTable[1][4] = ;
		damageTable[1][5] = ;
		damageTable[2][0] = ;
		damageTable[2][1] = ;
		damageTable[2][2] = ;
		damageTable[2][3] = ;
		damageTable[2][4] = ;
		damageTable[2][5] = ;
		damageTable[3][0] = ;
		damageTable[3][1] = ;
		damageTable[3][2] = ;
		damageTable[3][3] = ;
		damageTable[3][4] = ;
		damageTable[3][5] = ;
		damageTable[4][0] = ;
		damageTable[4][1] = ;
		damageTable[4][2] = ;
		damageTable[4][3] = ;
		damageTable[4][4] = ;
		damageTable[4][5] = ;
		damageTable[5][0] = ;
		damageTable[5][1] = ;
		damageTable[5][2] = ;
		damageTable[5][3] = ;
		damageTable[5][4] = ;
		damageTable[5][5] = ;
		
		
		return multiplier;
		
	}
	
}
