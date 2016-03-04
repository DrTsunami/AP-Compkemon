import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.ArrayList;



enum GameState{
	SELECTING_COMPKEMON
}

public class Game {
	GameState state;
	GamePanel panel;
	
	String commandLine = "";
	TextBox textBox;
	
	public void ProcessCommand(String command){
		
		String text = "";
		// TODO handle a command
		switch (state){
		case SELECTING_COMPKEMON:{	
			Select();
			break;
		}
		}
	}
	
	public void KeyPress(KeyEvent keyCode){
		char c = keyCode.getKeyChar();
		int i = (int)c;
		if (i == 10){ // enter
			ProcessCommand(commandLine);
			commandLine = "";
		} else {
			if (i >= 32 && i < 127) // other keys
				commandLine += c;
			else if (i == 8) // backspace
				if (commandLine.length() > 0)
					commandLine = commandLine.substring(0, commandLine.length() - 1);
		}
		panel.repaint();
	}

	
	public void consoleKeyDetector (KeyEvent k) {
		char c = k.getKeyChar();
		System.out.println(c);
	}
	
	
	public void MousePress(MouseEvent e){
		
	}

	public void init() {
		textBox = new TextBox(new Vector2(0, 0), new Vector2(200, 200));
		state = GameState.SELECTING_COMPKEMON;
		panel.repaint();
		textBox.AnimateText("Enter number corresponding to the Compkemon you wish to hack with: ");
		textBox.AnimateText("This is a test animation");
	}
		
	static Compkemon myCompkemon;
	static Compkemon enemy;
	static TypeTable typeTable = new TypeTable();
	static int turnCounter;
	
	public void Select() {
		int myCompkemonScanned = 0;
		myCompkemon = new Compkemon();
		enemy = new Compkemon();
		
		// TODO wait for user to select compkemon
		myCompkemonScanned = Integer.parseInt(commandLine);
		
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
		
		textBox.AnimateText("Congratulations, your chosen Compkemon is: " + myCompkemon);
		
		// set enemy compkemon here;
		enemy = new Compkemon(CompkemonList.Prototype);
		
		textBox.AnimateText("An enemy Compkemon hacked! You are under attack!");
		textBox.AnimateText("A wild " + enemy + " appeared!");
		textBox.AnimateText("Fight!");
		
		//Game.battleScene(myCompkemon, enemy);
		
		System.out.println("Battle has concluded");	
	}
	/*
	
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

	*/
	
	public void draw(Graphics2D g2d) {
		int windowWidth = GameWindow.ScreenWidth;
		int windowHeight = GameWindow.ScreenHeight; 
		// Sets background
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, windowWidth, windowHeight);
		
		textBox.Position = new Vector2(10, windowHeight - 130);
		textBox.Size = new Vector2(windowWidth - 20, 100);
		
		switch (state){
		case SELECTING_COMPKEMON:{
			
			Font font = new Font("consolas", Font.PLAIN, 18);
			g2d.setFont(font);
			g2d.setColor(Color.GREEN);
			GamePanel.drawString(g2d, "> Enter number corresponding to the Compkemon you wish to hack with: ", 5, 5);
			GamePanel.drawString(g2d, "1. Prototype" + "\n" + "2. Wrightson" + "\n" + "3. Alex" + "\n" + "4. Jeremiah" + "\n" + "5. Jackson" + "\n", 5, (5 + g2d.getFontMetrics().getHeight()));
			
			g2d.setColor(Color.GREEN);
			
			g2d.drawString("> ", 10, windowHeight - 10);
			g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
			
			textBox.Draw(g2d);
			break;
		}
		}
	}
}


