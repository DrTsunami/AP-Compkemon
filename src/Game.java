import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;


enum GameState{
	SELECTING_COMPKEMON
}

public class Game {
	GameState state;
	GamePanel panel;
	
	public void KeyPress(KeyEvent keyCode){
		// TODO handle key press
	}
	public void MousePress(MouseEvent e){
		// TODO handle mouse press
	}

	public void init() {

		
		// Gets user input for compkemon
		System.out.println("1. Prototype" + "\n" + "2. Wrightson" + "\n" + "3. Alex" + "\n" + "4. Jeremiah" + "\n" + "5. Jackson" + "\n" + 
							"Enter number corresponding to the Compkemon you wish to hack with: ");
		
		state = GameState.SELECTING_COMPKEMON;
		panel.repaint();
	}
		
	public void Select() {
		int myCompkemonScanned = 0;
		myCompkemon = new Compkemon();
		enemy = new Compkemon();
		
		myCompkemonScanned = scanner.nextInt();
		// TODO wait for user to select comple
		
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
			case 4:
				myCompkemon = new Compkemon(CompkemonList.Jeremiah);
				break;
			case 5:
				myCompkemon = new Compkemon(CompkemonList.Jackson);
				break;
		}
		
		System.out.println("Congratulations, your chosen Compkemon is: " + myCompkemon);
		
		// set enemy compkemon here;
		enemy = new Compkemon(CompkemonList.Prototype);
		
		System.out.println("An enemy Compkemon hacked! You are under attack!");
		System.out.println("A wild " + enemy + " appeared!");
		System.out.println("Fight!");
		
		Game.battleScene(myCompkemon, enemy);
		
		System.out.println("Battle has concluded");	
	}

	
	static Scanner scanner = new Scanner(System.in);
	static Compkemon myCompkemon;
	static Compkemon enemy;
	static TypeTable typeTable = new TypeTable();
	static int turnCounter;
	
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
			
			priority = BattleHandler.priorityCalculator(myCompkemon, myCompkemon.currentMove, enemy, enemy.currentMove);
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
			BattleHandler.displayHealth(myCompkemon, enemy);		
			
			// Check for lingering Effects on first 
			if (firstEffects.size() > 0) {
				for (int i = 0; i < firstEffects.size(); i++) {
					firstEffects.get(i).didApplyThisTurn = false;
					firstEffects.get(i).Update();
					if (firstEffects.get(i).finished) {
						firstEffects.remove(i);
					}
				}
			} 
			
			if (first.currentMove != null) {
				// User move begin					
				System.out.println(first + " used " + firstMove);
				
				// Move hit/miss
				if (BattleHandler.hitMiss(firstMove)) {
					// Alpha damage calculator and applier
					if (firstMove.power > 0) {
						System.out.println(second + " took damage!");
						second.health = (second.currentHealth - ((int)BattleHandler.damageCalculator(first, second, firstMove)));	
						if (second.currentHealth <= 0) {
							second.currentHealth = 0;
						}
						BattleHandler.displayHealth(myCompkemon, enemy);					
					}
					
					
					// Check for move effect. If true, effects are applied
					if (firstMove.hasEffect) {
						Effect effect = firstMove.getEffect(first, second);
						
						if (firstMove.toSelf) {
							firstEffects.add(effect);
							for (int i = 0; i < firstEffects.size(); i++) {
								if (!firstEffects.get(i).didApplyThisTurn) {
									firstEffects.get(i).Update();
									if (firstEffects.get(i).finished) {
										firstEffects.remove(i);
										i--;
									}
								}								
							} 
						} else {
							secondEffects.add(effect);
							for (int i = 0; i < secondEffects.size(); i++) {
								if (!secondEffects.get(i).didApplyThisTurn) {
									secondEffects.get(i).Update();
									if (secondEffects.get(i).finished) {
										secondEffects.remove(i);
										i--;
									}
								}	
							}
						}
						
						System.out.println("Added an effect!");
						BattleHandler.displayHealth(myCompkemon, enemy);
					}
					
					
					// Splash salute - not done
	
				} else {
					System.out.println(first + "'s attack missed!");
				}
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
					secondEffects.get(i).didApplyThisTurn = false;
					secondEffects.get(i).Update();
					if (secondEffects.get(i).finished) {
						secondEffects.remove(i);
					}
				}
			}
			
			if (second.currentMove != null) {
				System.out.println(second + " used " + secondMove);	
				
				// Hit or miss
				if (BattleHandler.hitMiss(secondMove)) {						
					// Alpha damage calculator and applier
					if (secondMove.power > 0) {
						System.out.println(first + " took damage!");
						first.health = (first.currentHealth - ((int)BattleHandler.damageCalculator(second, first, secondMove)));
						if (first.currentHealth <= 0) {
							first.currentHealth = 0;
						}
						BattleHandler.displayHealth(myCompkemon, enemy);
					}
					
					// Check for move effect. If true, effects are applied
					if (secondMove.hasEffect) {
						Effect effect = secondMove.getEffect(second, first);
						
						if (secondMove.toSelf) {
							secondEffects.add(effect);
							for (int i = 0; i < secondEffects.size(); i++) {
								if (!secondEffects.get(i).didApplyThisTurn) {
									secondEffects.get(i).Update();
									if (secondEffects.get(i).finished) {
										secondEffects.remove(i);
										i--;
									}
								}
							} 
						} else {
							firstEffects.add(effect);
							for (int i = 0; i < firstEffects.size(); i++) {
								if (!firstEffects.get(i).didApplyThisTurn) {
									firstEffects.get(i).Update();
									if (firstEffects.get(i).finished) {
										firstEffects.remove(i);
										i--;
									}
								}
							}
						}
						
						System.out.println("Added an effect!");
						BattleHandler.displayHealth(myCompkemon, enemy);
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
				
			}
			// Check for user health. If fainted, end the game
			if (first.currentHealth <= 0) {
				loser = first;
				break;					
			}
			
			// Turn tracker increases
			turnCounter++;
			
		}
		
		System.out.println(loser + " has fainted");
	} // end battleScene
	
	
	public void draw(Graphics2D g2d) {
		switch (state){
		case SELECTING_COMPKEMON:{
			
			Font font = new Font("consolas", Font.PLAIN, 12);
			
			g2d.setFont(font);
			g2d.setColor(Color.GREEN);
			g2d.drawString("test" + "\n" + "test2", 100, 100);
			System.out.println(g2d.getFont());
			
			/*
			g2d.drawString("1. Prototype" + "\n" + "2. Wrightson" + "\n" + "3. Alex" + "\n" + "4. Jeremiah" + "\n" + "5. Jackson" + "\n" + 
								"Enter number corresponding to the Compkemon you wish to hack with: ", 100, 100);
			*/
			break;
		}
		}
	}
	
}


