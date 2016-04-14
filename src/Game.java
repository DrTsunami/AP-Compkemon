import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;


// Handles the GameStates
enum GameState{
	START,
	WAITING_FOR_INPUT,
	SELECTING_COMPKEMON,
	INTRO,
	CHOOSING_MOVE,
	APPLYING_MOVE,
	APPLYING_EFFECTS,
	END_GAME, 
	APPLYING_FIRST_MOVE, 
	SHOW_FIRST_DAMAGE, 
	APPLYING_EFFECTS_FIRST, 
	APPLYING_SECOND_MOVE, 
	APPLYING_EFFECTS_SECOND,
	CHECK_EFFECTS_1,
	CHECK_EFFECTS_2,
}


public class Game {
	
	GamePanel panel;
	
	// Prepare Command Line box
	CommandProcessor commandProcessor;
	static String commandLine = "";
	static TextBox textBox;
	static boolean ready;
	
	// Declare static Game variables
	static GameState state;
	static Compkemon myCompkemon;
	static Compkemon enemy;
	static Compkemon first;
	static Compkemon second;
	static TypeTable typeTable = new TypeTable();
	static int turnCounter;
	static int myMove;
	static int enemyMove;
	static Move firstMove = new Move();
	static Move secondMove = new Move();
	static HealthBox firstHealthBox;
	static HealthBox secondHealthBox;
	static int priority;
	static Compkemon loser = new Compkemon();
	static boolean gameOver = false;
	
	// Visual objects
	static boolean animating;
	static Ground userGround;
	static Ground enemyGround;
	static HealthBox userHealthBox;
	static HealthBox enemyHealthBox;
	// TODO properly assign sprites
	static Sprite sprite1;
	static Sprite sprite2;
	static Requirements req;
	
	static String customEnemy;
	
	// Performed on initialization
	public void init() {
		
		req = new Requirements();
		req.whileFunction();
		req.forEachFunction();
		
		// Draw command line box
		textBox = new TextBox(new Vector2(0, 0), new Vector2(200, 200));
		panel.repaint();
		// Go to next stage
		customEnemy = null;
		Start();
		
	}
	
	// Performed after initialization
	public void Start() {
		// Change state and perform welcome messages
		state = GameState.START;
	}
	
	// Called at start of game
	public void BeginGame() {
		ready = false;
		gameOver = false;
		state = GameState.SELECTING_COMPKEMON;
		textBox.AnimateText("Welcome to the world of hacking!", false);
		textBox.AnimateText("Enter number corresponding to the Compkemon you wish to hack with: ", true);
		// override processCommand for commandProcessor in this state. When input is detected, perform this.
		commandProcessor = new CommandProcessor(){
			public void processCommand(String txt){
				// this will be called next time we press enter;
				Select();
			}
		};
	}
	
	// Processes commands
	public void ProcessCommand(String command){

		// resets text string. Clears it.
		String text = "";
		
		if (command.length() > 0) {
			// 
			if (commandProcessor != null)
				commandProcessor.processCommand(text);
		}
	}
	
	// Handles key events
	public void KeyPress(KeyEvent keyCode){
		// Takes keyCode (key) and gets the character and turns it into the integer
		char c = keyCode.getKeyChar();
		int i = (int)c;
		
		if (state == GameState.START && i == 10) {
			state = GameState.SELECTING_COMPKEMON;
			BeginGame();
			System.out.println("Beginning game");
		}
		
		if (state == GameState.START && i == 27) {
			enemy = BattleHandler.customEnemy(JOptionPane.showInputDialog("Enter command"));
			System.out.println(enemy);
		}
		
		// If enter key detected...
		if (i == 10 && commandLine.length() > 0){ 
			// if Enter is pressed and the commandLine receives text, then process it and clear the text
			ProcessCommand(commandLine);
    		textBox.EnterToContinue(keyCode);
			commandLine = "";
		} else {
			// otherwise, if any other key is pressed, add it to commandLine and continue
			if (i >= 32 && i < 127) // other keys
				commandLine += c;
			else if (i == 8) { // backspace
				// if Backspace is pressed, delete a character from commandLine
				if (commandLine.length() > 0)
					commandLine = commandLine.substring(0, commandLine.length() - 1);
			} else if (i == 10) {
				// if Enter is pressed and there's nothing, run EnterToContinue
	    		textBox.EnterToContinue(keyCode);
			}
			
		}
		panel.repaint();
	}
	
	public void MousePress(MouseEvent e){
		
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// User selects the Compkemon to use
	public void Select() {
		int myCompkemonScanned = 0;
		myCompkemon = new Compkemon();
		// TODO maybe make a game mode where you have to fight throuhg 
		if (enemy == null) {
			enemy = new Compkemon();
			enemy = BattleHandler.randomEnemy();
		}
		
		// wait for user to select compkemon
		myCompkemonScanned = Integer.parseInt(commandLine);
		
		switch(myCompkemonScanned) {
			case 1:
				myCompkemon =  new Compkemon(CompkemonList.God);
				break;
			case 2:
				myCompkemon =  new Compkemon(CompkemonList.Aidan);
				break;
			case 3:
				myCompkemon = new Compkemon(CompkemonList.Alex);
				break;
			case 4:
				myCompkemon = new Compkemon(CompkemonList.Hieu);
				break;
			case 5:
				myCompkemon = new Compkemon(CompkemonList.Jackson);
				break;
			case 6:
				myCompkemon = new Compkemon(CompkemonList.Jeremiah);
				break;
			case 7:
				myCompkemon = new Compkemon(CompkemonList.Kenny);
				break;
			case 8:
				myCompkemon = new Compkemon(CompkemonList.Noah);
				break;
			case 9:
				myCompkemon = new Compkemon(CompkemonList.Ryan);
				break;
			case 10:
				myCompkemon = new Compkemon(CompkemonList.Trevor);
				break;
			case 11:
				// TODO change wrightson's name to God
				myCompkemon = new Compkemon(CompkemonList.God);
				break;
			case 12:
				myCompkemon = new Compkemon(CompkemonList.Kevin);
				break;
			
			default: 
				textBox.AnimateText("Invalid command!", true);
				Start();
				break;
		}
		
		
		textBox.AnimateText("Congratulations, your chosen Compkemon is: " + myCompkemon, false);	
		textBox.AnimateText("An enemy Compkemon hacked! You are under attack!", false);
		textBox.AnimateText("Fight!", false);
		ready = true;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	

	// User chooses move to use
	public void ChooseMove() {
		
		ready = false;
		textBox.AnimateText("Choose move: " + myCompkemon.getMoveset(), true);
		commandProcessor = new CommandProcessor(){
			// Overwrite processCommand function for choosing a move
			public void processCommand(String txt){
				txt = commandLine;
				int moveInput = Integer.parseInt(txt);
				myCompkemon.currentMove = myCompkemon.moveset[(moveInput - 1)];
				enemyMove = (int)(Math.random() * enemy.moveset.length);
				enemy.currentMove = enemy.moveset[enemyMove];

				// Priority handler
				priority = BattleHandler.priorityCalculator(myCompkemon, myCompkemon.currentMove, enemy, enemy.currentMove);
				
				first = new Compkemon();
				second = new Compkemon();
				// Establish priority
				if (priority == 1) {
					first = myCompkemon;
					second = enemy;
					firstMove = myCompkemon.currentMove;
					secondMove = enemy.currentMove;
					firstHealthBox = userHealthBox;
					secondHealthBox = enemyHealthBox;
				} else if (priority == 0) {
					first = enemy;
					second = myCompkemon;
					firstMove = enemy.currentMove;
					secondMove = myCompkemon.currentMove;
					firstHealthBox = enemyHealthBox;
					secondHealthBox = userHealthBox;
				}
				ready = true;
				turnCounter++;
			}
		};
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	
	public void Intro() {
		userGround = new Ground(0, 500);
		enemyGround = new Ground(GameWindow.ScreenWidth - 250, 200);
		state = GameState.INTRO;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Recursive method to wait for input
	public boolean WaitingForInput() {
		boolean waiting = true;
		if (textBox.animateQueue.size() == 1 && ready) {
			System.out.println("Clear!");
			waiting = false;
		} else {
			waiting = true;
		}
		return waiting;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void EffectHandler(Compkemon compkemon) {
		ready = false;
		
		if (compkemon.effect.size() > 0) {
			for (int i = 0; i < compkemon.effect.size(); i++) {
				compkemon.effect.get(i).didApplyThisTurn = false;
				compkemon.effect.get(i).Update();
				if (compkemon.effect.get(i).finished) {
					compkemon.effect.remove(i);
				}
			}
		} 
		
		ready = true;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void MoveHandler(Compkemon current, Compkemon other) {
		
		ready = false;
		
		if (current.currentMove != null) {
			// User move begin					
			
			textBox.AnimateText(current + " used " + current.currentMove, false);
		
			// Move hit/miss
			if (BattleHandler.hitMiss(current.currentMove)) {
				if (current.currentMove.power > 0) {
					other.currentHealth = (other.currentHealth - ((int)BattleHandler.damageCalculator(current, other, current.currentMove)));	
					if (other.currentHealth <= 0) {
						other.currentHealth = 0;
					}					
				}
				
			} else {
				textBox.AnimateText(current + "'s attack missed!", false);
				current.currentMove = null;
			}
			
			ready = true;
			
		}
		
		if (other.currentHealth <= 0) {
			System.out.println("I'm here! game over here!");
			loser = other;
			gameOver = true;
			ready = true;
		}
		
		
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void MoveEffect(Compkemon current, Compkemon other) {
		// Check for move effect. If true, effects are applied
		if (current.currentMove.hasEffect) {
			Effect effect = current.currentMove.getEffect(current, other);
			
			if (current.currentMove.toSelf) {
				current.effect.add(effect);
				for (int i = 0; i < current.effect.size(); i++) {
					if (!current.effect.get(i).didApplyThisTurn) {
						current.effect.get(i).Update();
						if (current.effect.get(i).finished) {
							current.effect.remove(i);
							i--;
						}
					}								
				} 
			} else {
				other.effect.add(effect);
				for (int i = 0; i < other.effect.size(); i++) {
					if (!other.effect.get(i).didApplyThisTurn) {
						other.effect.get(i).Update();
						if (other.effect.get(i).finished) {
							other.effect.remove(i);
							i--;
						}
					}	
				}
			}
		}
		
		if (current.currentHealth <= 0) {
			System.out.println("I'm here! game over here!");
			loser = current;
			gameOver = true;
			ready = true;
		} else if (other.currentHealth <= 0) {
			System.out.println("I'm here! game over here!");
			loser = other;
			gameOver = true;
			ready = true;
		}

		ready = true;
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void battleScene(Compkemon myCompkemon, Compkemon enemy) {
		// Battle starts
		sprite1 = new Sprite(myCompkemon, (int)userGround.getX() + 25, (int)userGround.getY() - 175);
		sprite2 = new Sprite(enemy, (int)enemyGround.getX() + 25, (int)enemyGround.getY() - 175);
		userHealthBox = new HealthBox(myCompkemon, GameWindow.ScreenWidth - 380, 400);
		enemyHealthBox = new HealthBox(enemy, 100 , 50);
		
		turnCounter = 0;
		textBox.AnimateText("A wild virus appeared!", false);
		ChooseMove();
	} // end battleScene

	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void endGame(Compkemon loser) {
		state = GameState.END_GAME;
		textBox.AnimateText(loser + " has been hacked!", false);
		textBox.AnimateText("Would you like to battle again? Y/N", true);
		
		commandProcessor = new CommandProcessor(){
			public void processCommand(String txt){
				// this will be called next time we press enter;
				switch (commandLine) {
					case "y" :
						state = GameState.SELECTING_COMPKEMON;
						BeginGame();
						break;
					case "n" :
						Start();
						break;
				}
				
			}
		};
		
	}
	
	
	public static int frame = 0;
	// TODO clean up this method. It's redundant and messy
	public void draw(Graphics2D g2d) {
		// This keeps drawing over and over once in case
		int windowWidth = GameWindow.ScreenWidth;
		int windowHeight = GameWindow.ScreenHeight; 
		// Sets background
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, windowWidth, windowHeight);
		
		textBox.Position = new Vector2(10, windowHeight - 130);
		textBox.Size = new Vector2(windowWidth - 20, 100);
		
		Font font = new Font("consolas", Font.PLAIN, 18);
		g2d.setFont(font);
		g2d.setColor(Color.GREEN);
	
		
		switch (state){
			case START: {
				System.out.println("Start");
				font = new Font("consolas", Font.PLAIN, 60);
				g2d.setFont(font);
				g2d.drawString("AP COMPKEMON", (windowWidth/2) - 200, windowHeight/2 - 100);
				font = new Font("consolas", Font.PLAIN, 36);
				g2d.setFont(font);
				g2d.drawString("Press 'Enter' to Start!", (windowWidth/2) - 200 - 30, windowHeight/2 - 100 + 150);
				// TODO somehow add flashing and moing elements
				//if (frame % 60 == 0) {
					//g2d.drawString("_", (windowWidth/2) + 230, windowHeight/2 - 100 + 150);
				//} else if (frame % 30 == 0) {
					
				//}
				break;
			}
		
			case SELECTING_COMPKEMON: {
				System.out.println("selecting compkemon");
				GamePanel.drawString(g2d, "1. Prototype" + "\n" + 
									"2. Aidan" + "\n" + 
									"3. Alex" + "\n" + 
									"4. Hieu" + "\n" + 
									"5. Jackson" + "\n" + 
									"6. Jeremiah" + "\n" + 
									"7. Kenny" + "\n" + 
									"8. Noah" + "\n" + 
									"9. Ryan" + "\n" + 
									"10. Trevor" + "\n",
									5, 5);
				g2d.setColor(Color.GREEN);
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				// Use this code to wait for input
				if (!WaitingForInput()) {
					Intro();
				} else {
					System.out.println(textBox.animateQueue.size());
				}
				break;
			}
			
			case INTRO: {
				animating = true;
				Animations.intro(g2d, userGround, enemyGround);
				textBox.Draw(g2d);
				if (!animating) {
					battleScene(myCompkemon, enemy);
					userHealthBox.draw(g2d);
					enemyHealthBox.draw(g2d);
					state = GameState.CHOOSING_MOVE;
				}
				break;
			}
			case CHOOSING_MOVE: {
				System.out.println("choosing move");
				// TODO properly assign sprites;
				userGround.draw(g2d);
				enemyGround.draw(g2d);
				userHealthBox.draw(g2d);
				enemyHealthBox.draw(g2d);
				g2d.drawImage(sprite1.getImage(), sprite1.getX(), sprite1.getY(), 200, 200, null);  
				g2d.drawImage(sprite2.getImage(), sprite2.getX(), sprite2.getY(), 200, 200, null);  
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				
				if (!WaitingForInput()) {
					EffectHandler(first);
					state = GameState.CHECK_EFFECTS_1;
				}
				
				break;
			}
			case CHECK_EFFECTS_1: {
				System.out.println("Checking first effects");
				userGround.draw(g2d);
				enemyGround.draw(g2d);
				userHealthBox.draw(g2d);
				enemyHealthBox.draw(g2d);
				g2d.drawImage(sprite1.getImage(), sprite1.getX(), sprite1.getY(), 200, 200, null);  
				g2d.drawImage(sprite2.getImage(), sprite2.getX(), sprite2.getY(), 200, 200, null);  
				textBox.Draw(g2d);
				if (!WaitingForInput()) {
					Animations.damaged(g2d, firstHealthBox);
					Animations.damaged(g2d, secondHealthBox);
					if (!animating) {
						if (gameOver) {
							endGame(loser);
						} else {
							MoveHandler(first, second);
							state = GameState.APPLYING_FIRST_MOVE;
						}
					}
				}
				break;
			}
			case APPLYING_FIRST_MOVE: {
				System.out.println("Applying First Move");
				userGround.draw(g2d);
				enemyGround.draw(g2d);
				userHealthBox.draw(g2d);
				enemyHealthBox.draw(g2d);
				g2d.drawImage(sprite1.getImage(), sprite1.getX(), sprite1.getY(), 200, 200, null);  
				g2d.drawImage(sprite2.getImage(), sprite2.getX(), sprite2.getY(), 200, 200, null);  
				if (!WaitingForInput()) {
					Animations.damaged(g2d, secondHealthBox);
					if (!animating) {
						if (gameOver) {
							endGame(loser);
						} else {
							if (first.currentMove != null) {
								MoveEffect(first, second);
								state = GameState.APPLYING_EFFECTS_FIRST;
							} else {
								MoveHandler(second, first);
								state = GameState.APPLYING_SECOND_MOVE;
							}
						}
					}
				}
				textBox.Draw(g2d);
				break;
			}
			
			case APPLYING_EFFECTS_FIRST: {
				System.out.println("applying first effects");
				userGround.draw(g2d);
				enemyGround.draw(g2d);
				userHealthBox.draw(g2d);
				enemyHealthBox.draw(g2d);
				g2d.drawImage(sprite1.getImage(), sprite1.getX(), sprite1.getY(), 200, 200, null);  
				g2d.drawImage(sprite2.getImage(), sprite2.getX(), sprite2.getY(), 200, 200, null);  
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				
				// FIXME move this block back inside  waiting for input just in case
				Animations.damaged(g2d, firstHealthBox);
				Animations.damaged(g2d, secondHealthBox);
				
				if (!WaitingForInput()) {
					if (!animating) {
						if (gameOver) {
							endGame(loser);
						} else {
							EffectHandler(second);
							state = GameState.CHECK_EFFECTS_2;
						}
					}
				}
				
				break;
			}
			case CHECK_EFFECTS_2: {
				System.out.println("Checking second effects");
				userGround.draw(g2d);
				enemyGround.draw(g2d);
				userHealthBox.draw(g2d);
				enemyHealthBox.draw(g2d);
				g2d.drawImage(sprite1.getImage(), sprite1.getX(), sprite1.getY(), 200, 200, null);  
				g2d.drawImage(sprite2.getImage(), sprite2.getX(), sprite2.getY(), 200, 200, null);  
				textBox.Draw(g2d);
				if (!WaitingForInput()) {
					Animations.damaged(g2d, firstHealthBox);
					Animations.damaged(g2d, secondHealthBox);
					if (!animating) {
						if (gameOver) {
							endGame(loser);
						} else if (second.currentMove == null) {
							ChooseMove();
							state = GameState.CHOOSING_MOVE;
						} else {
							MoveHandler(second, first);
							state = GameState.APPLYING_SECOND_MOVE;
						}
					}
				}
				break;
			}
			case APPLYING_SECOND_MOVE: {
				System.out.println("Applying Second Move");
				userGround.draw(g2d);
				enemyGround.draw(g2d);
				userHealthBox.draw(g2d);
				enemyHealthBox.draw(g2d);
				g2d.drawImage(sprite1.getImage(), sprite1.getX(), sprite1.getY(), 200, 200, null);  
				g2d.drawImage(sprite2.getImage(), sprite2.getX(), sprite2.getY(), 200, 200, null);  
				if (!WaitingForInput()) {
					Animations.damaged(g2d, firstHealthBox);
					if (!animating) {
						if (gameOver) {
							endGame(loser);
						} else {
							if (second.currentMove != null) {
								MoveEffect(second, first);
								state = GameState.APPLYING_EFFECTS_SECOND;
							} else {
								ChooseMove();
								state = GameState.CHOOSING_MOVE;
							}
							
						}
					}
				}
				textBox.Draw(g2d);
				break;
			}
			
			case APPLYING_EFFECTS_SECOND: {
				System.out.println("applying second effects");
				userGround.draw(g2d);
				enemyGround.draw(g2d);
				userHealthBox.draw(g2d);
				enemyHealthBox.draw(g2d);
				g2d.drawImage(sprite1.getImage(), sprite1.getX(), sprite1.getY(), 200, 200, null);  
				g2d.drawImage(sprite2.getImage(), sprite2.getX(), sprite2.getY(), 200, 200, null);  
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				
				// FIXME for some reason health animations dont always occur completely. Jumps throuhg animation checking loop
				// FIXME move this block back inside  waiting for input just in case
				Animations.damaged(g2d, firstHealthBox);
				Animations.damaged(g2d, secondHealthBox);
				
				if (!WaitingForInput()) {
					if (!animating) {
						if (gameOver) {
							endGame(loser);
						} else {
							ChooseMove();
							state = GameState.CHOOSING_MOVE;
						}
					}
				}
				
				break;
			}
			
			case END_GAME: {
				userGround.draw(g2d);
				enemyGround.draw(g2d);
				userHealthBox.draw(g2d);
				enemyHealthBox.draw(g2d);
				Animations.damaged(g2d, userHealthBox);
				Animations.damaged(g2d, enemyHealthBox);
				if (loser.equals(myCompkemon)) {
					g2d.drawImage(sprite2.getImage(), sprite2.getX(), sprite2.getY(), 200, 200, null);  
				} else {
					g2d.drawImage(sprite1.getImage(), sprite1.getX(), sprite1.getY(), 200, 200, null);  
				}
				System.out.println("endgame");
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				break;
			}
			
		}
		
		frame++;
	}
}


