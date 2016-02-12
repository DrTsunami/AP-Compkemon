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
					myCompkemon.setHealth(myCompkemon.currentHealth - enemyMoveUsed.power);
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
	
	public static void damageCalculator(Compkemon user, Compkemon target, Move userMove) {
		
		float damage = 0.0f;
		float userAttack = user.getAttack();
		float targetDefense = target.getDefense();		
		
		if (userMove.power > 0) {			
			damage = (int)(.85 * ((userAttack)/(targetDefense)) * (userMove.power));
			System.out.println(target + " took damage!");
			target.setHealth(target.currentHealth - (int)(damage));
			if (target.currentHealth <= 0) {
				System.out.println(target + " HP: 0");
			} else {
				System.out.println(target + " HP: " + target.currentHealth);
			}
		} 
		
	}
}
