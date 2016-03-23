import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.ArrayList;


// Handles the GameStates
enum GameState{
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
	
	
	// Performed on initialization
	public void init() {
		// Draw command line box
		textBox = new TextBox(new Vector2(0, 0), new Vector2(200, 200));
		panel.repaint();
		
		// Go to next stage
		Start();
		
	}
	
	// Performed after initialization
	public void Start() {
		// Change state and perform welcome messages
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
				//Intro();
			}
		};
	}
	
	// Processes commands
	public void ProcessCommand(String command){

		String text = "";
		
		if (command.length() > 0) {
			if (commandProcessor != null)
				commandProcessor.processCommand(text);
		}
	}
	
	// Handles key events
	public void KeyPress(KeyEvent keyCode){
		// Takes keyCode (key) and gets the character and turns it into the integer
		char c = keyCode.getKeyChar();
		int i = (int)c;
		
		// If enter key detected...
		if (i == 10 && commandLine.length() > 0){ 
			// if Enter is pressed and the commandLine receives text, then process it and clear the text
			ProcessCommand(commandLine);
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
		enemy = new Compkemon();
		enemy = new Compkemon(CompkemonList.Prototype);
		
		// wait for user to select compkemon
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
			// TODO add cases for everything else
		}
		
		
		textBox.AnimateText("Congratulations, your chosen Compkemon is: " + myCompkemon, false);	
		textBox.AnimateText("An enemy Compkemon hacked! You are under attack!", false);
		textBox.AnimateText("A wild " + enemy + " appeared!", false);
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
				/*
				// Check for lingering effects on first
				// TODO fix effect orders. Not always proper
				
				EffectHandler(first);
				MoveHandler(first, second);
					
				// Check for enemy health. If fainted, end the game
				

				if (gameOver) {
					endGame(loser);
				} else {
					// Enemy move begin
					EffectHandler(second);
					MoveHandler(second, first);
					
					if (gameOver) {
						endGame(loser);		
					} else {
						ChooseMove();
					}
				}
				// Turn tracker increases
				 * 
				 * 
				 */
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
		if (compkemon.effect.size() > 0) {
			for (int i = 0; i < compkemon.effect.size(); i++) {
				compkemon.effect.get(i).didApplyThisTurn = false;
				compkemon.effect.get(i).Update();
				if (compkemon.effect.get(i).finished) {
					compkemon.effect.remove(i);
				}
			}
		} 
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
					textBox.AnimateText(other + " took damage!", false);
					other.currentHealth = (other.currentHealth - ((int)BattleHandler.damageCalculator(current, other, current.currentMove)));	
					if (other.currentHealth <= 0) {
						other.currentHealth = 0;
					}					
				}
			// TODO make sure to re-add Effect Handler thing here
				
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
			
			textBox.AnimateText("Added an effect!", false);
			
			ready = true;
		}
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void battleScene(Compkemon myCompkemon, Compkemon enemy) {
		System.out.println("Battle STARTS");
		// Battle starts
		userHealthBox = new HealthBox(myCompkemon, GameWindow.ScreenWidth - 380, 400);
		enemyHealthBox = new HealthBox(enemy, 100 , 50);
		
		turnCounter = 0;
		textBox.AnimateText("Hello, welcome to a battle!", false);
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
						Start();
						break;
					case "n" :
						System.exit(0);
						break;
				}
				
			}
		};
		
	}
	
	
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
		
			case SELECTING_COMPKEMON: {
				System.out.println("selecting compkemon");
				GamePanel.drawString(g2d, "1. Prototype" + "\n" + "2. Wrightson" + "\n" + "3. Alex" + "\n" + "4. Jeremiah" + "\n" + "5. Jackson" + "\n", 5, 5);
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
			// FIXME continue applying new form of waiting using the queue size and ready boolean
			case CHOOSING_MOVE: {
				System.out.println("choosing move");
				userGround.draw(g2d);
				enemyGround.draw(g2d);
				userHealthBox.draw(g2d);
				enemyHealthBox.draw(g2d);
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				
				if (!WaitingForInput()) {
					MoveHandler(first, second);
					state = GameState.APPLYING_FIRST_MOVE;
				}
				
				break;
			}
			case APPLYING_FIRST_MOVE: {
				System.out.println("Applying First Move");
				userGround.draw(g2d);
				enemyGround.draw(g2d);
				userHealthBox.draw(g2d);
				enemyHealthBox.draw(g2d);
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
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				if (!WaitingForInput()) {
					if (!animating) {
						if (gameOver) {
							endGame(loser);
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
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				
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
				System.out.println("endgame");
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				break;
				// FIXME something is wrong with Prototype at the start of the game
			}
			
		}
	}
}


