import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	
	static Scanner scanner = new Scanner(System.in);
	static Compkemon myCompkemon;
	static Compkemon enemy;
	static TypeTable typeTable = new TypeTable();
	static int turnCounter;
	
	public static void main(String[] args) {
		
		int myCompkemonScanned = 0;
		myCompkemon = new Compkemon();
		enemy = new Compkemon();
		
		// Gets user input for compkemon
		System.out.println("1. Prototype" + "\n" + "2. Wrightson" + "\n" + "3. Alex" + "\n" + 
							"Enter number corresponding to the Compkemon you wish to hack with: ");
		myCompkemonScanned = scanner.nextInt();
		
		switch(myCompkemonScanned) {
			case 1:
				myCompkemon =  new Compkemon(CompkemonList.Prototype);
				break;
			case 2:
				myCompkemon =  new Compkemon(CompkemonList.Wrightson);
				break;
			case 3:
				myCompkemon = new Compkemon(CompkemonList.Alex);
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void battleScene(Compkemon myCompkemon, Compkemon enemy) {
		
		int myMove;
		int enemyMove;
		Move firstMove = new Move();
		Move secondMove = new Move();
		int priority;
		turnCounter = 0;
		Compkemon loser = new Compkemon();
		
		while (myCompkemon.currentHealth > 0 || enemy.currentHealth > 0) {
			
			System.out.println("Choose move: " + myCompkemon.getMoveset());
			myMove = scanner.nextInt();	
			enemyMove = (int)(Math.random() * enemy.moveset.length);
			
			myCompkemon.currentMove = myCompkemon.moveset[myMove - 1];
			enemy.currentMove = enemy.moveset[enemyMove];	
			
			priority = priorityCalculator(myCompkemon, myCompkemon.currentMove, enemy, enemy.currentMove);
			Compkemon first = new Compkemon();
			Compkemon second = new Compkemon();
			
			// Establish priority
			if (priority == 1) {
				first = myCompkemon;
				second = enemy;
				firstMove = myCompkemon.currentMove;
				secondMove = enemy.currentMove;
			} else if (priority == 0) {
				first = enemy;
				second = myCompkemon;
				firstMove = enemy.currentMove;
				secondMove = myCompkemon.currentMove;
			}
			
			
			ArrayList<Effect> firstEffects = first.effect;
			ArrayList<Effect> secondEffects = second.effect;
			
			// Display health bars
			displayHealth(myCompkemon, enemy);		
			
			// Check for lingering Effects on first 
			if (firstEffects.size() > 0) {
				for (int i = 0; i < firstEffects.size(); i++) {
					firstEffects.get(i).Update();
					if (firstEffects.get(i).finished) {
						firstEffects.remove(i);
					}
				}
			} 
			
			if (firstMove != null) {
				// User move begin					
				System.out.println(first + " used " + firstMove);
				
				// Move hit/miss
				if (hitMiss(firstMove)) {
					// Alpha damage calculator and applier
					if (firstMove.power > 0) {
						System.out.println(second + " took damage!");
						second.setHealth(second.currentHealth - ((int)damageCalculator(first, second, firstMove)));	
						if (second.currentHealth <= 0) {
							second.currentHealth = 0;
						}
						displayHealth(myCompkemon, enemy);					
					}
					
					
					// Check for move effect. If true, effects are applied
					if (firstMove.hasEffect) {
						Effect effect = firstMove.getEffect(first, second);
						
						if (firstMove.toSelf) {
							firstEffects.add(effect);
							for (int i = 0; i < firstEffects.size(); i++) {
								firstEffects.get(i).Update();
							} 
						} else {
							secondEffects.add(effect);
							for (int i = 0; i < secondEffects.size(); i++) {
								secondEffects.get(i).Update();
								if (secondEffects.get(i).finished) {
									secondEffects.remove(i);
								}
							}
						}
						
						System.out.println("Added an effect!");
					}
					
					
					// Splash salute - not done
	
				} else {
					System.out.println(first + "'s attack missed!");
				}
				
				// Check for enemy health. If fainted, end the game
				if (second.currentHealth <= 0) {
					loser = second;
					break;					
				}
				
				// Enemy move begin
				
				// Check for lingering effects on second Compkemon
				if (secondEffects.size() > 0) {
					for (int i = 0; i < secondEffects.size(); i++) {
						secondEffects.get(i).Update();
						if (secondEffects.get(i).finished) {
							secondEffects.remove(i);
						}
					}
				}
				
				System.out.println(second + " used " + secondMove);	
				
				// Hit or miss
				if (hitMiss(secondMove)) {						
					// Alpha damage calculator and applier
					if (secondMove.power > 0) {
						System.out.println(first + " took damage!");
						first.setHealth(first.currentHealth - ((int)damageCalculator(second, first, secondMove)));
						if (first.currentHealth <= 0) {
							first.currentHealth = 0;
						}
						displayHealth(myCompkemon, enemy);
					}
					
					// Check for move effect. If true, effects are applied
					if (secondMove.hasEffect) {
						//passiveModifier(enemy, myCompkemon, enemyMoveUsed);
					}
					
					// Splash salute
					/*
					if (!enemyMoveUsed.hasEffect || enemyMoveUsed.power == 0) {
						System.out.println("Nothing happened. The enemy literally sucks.");
					}
					*/
				} else  {
					System.out.println(second + "'s attack missed!");
				}
				
				// Check for user health. If fainted, end the game
				if (first.currentHealth <= 0) {
					loser = first;
					break;					
				}
				
				// Turn tracker increases
				turnCounter++;
			}
		}
		
		System.out.println(loser + " has fainted");
	} // end battleScene
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
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
	
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	
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
				case "Defense":
					if (moveUsed.modifier == 10) {
						System.out.println(user + "'s" + " Defense increased!");
						user.defense += moveUsed.modifier;
					} else if (moveUsed.modifier == 20) {
						System.out.println(user + "'s" + " Defense sharply increased!");
						user.defense += moveUsed.modifier;						
					}
					break;
				case "Speed":
					if (moveUsed.modifier == 10) {
						System.out.println(user + "'s" + " Speed increased!");
						user.speed += moveUsed.modifier;
					} else if (moveUsed.modifier == 20) {
						System.out.println(user + "'s" + " Speed sharply increased!");
						user.speed += moveUsed.modifier;						
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
				case "Defense":
					if (moveUsed.modifier == 10) {
						System.out.println(target + "'s" + " Defense decreased!");
						target.defense -= moveUsed.modifier;
					} else if (moveUsed.modifier == 20) {
						System.out.println(target + "'s" + " Defense sharply decreased!");
						target.defense -= moveUsed.modifier;
					}
					break;
				case "Speed":
					if (moveUsed.modifier == 10) {
						System.out.println(target + "'s" + " Speed decreased!");
						target.speed -= moveUsed.modifier;
					} else if (moveUsed.modifier == 20) {
						System.out.println(target + "'s" + " Speed sharply decreased!");
						target.speed -= moveUsed.modifier;
					}
					break;
			}
		}
		
	} // end passiveModifier
	
	*/
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static float damageCalculator(Compkemon user, Compkemon target, Move userMove) {
		
		float damage = 0.0f;
		float userAttack = user.getAttack();
		float targetDefense = target.getDefense();		
		
		damage = (int)((.85 * ((userAttack)/(targetDefense)) * (userMove.power)) * damageMultiplier(user, target, userMove));
		
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
			System.out.println("It pales in comparison to a God!");
		} else if (typeMultiplier == 0.5f) {
			System.out.println("It's not very effective");
		} else if (typeMultiplier == 1.0f) {
			// Normal damage
		} else if (typeMultiplier == 2.0f) {
			System.out.println("It's super-effective!");
		} else if (typeMultiplier == 3.0f) {
			System.out.println(target + " has been subjected to the Wrightocracy!");
		} else if (typeMultiplier == 10.0f) {
			System.out.println("A God has been converted to moronic Satanism! It's ultra-effective!!");
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Checks move input
	public boolean verifyInput(int input) {		
		boolean isValid = false;		
		if (input == 1 || input == 2 || input == 3 || input == 4) {
			isValid = true;
		}
		return isValid;		
	}
	
}
