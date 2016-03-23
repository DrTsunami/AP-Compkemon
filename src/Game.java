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
}

public class Game {
	
	GamePanel panel;
	
	// Prepare Command Line box
	CommandProcessor commandProcessor;
	static String commandLine = "";
	static TextBox textBox;
	// Declare static Game variables
	static GameState state;
	static Compkemon myCompkemon;
	static Compkemon enemy;
	static TypeTable typeTable = new TypeTable();
	static int turnCounter;
	static int myMove;
	static int enemyMove;
	static Move firstMove = new Move();
	static Move secondMove = new Move();
	static int priority;
	static Compkemon loser = new Compkemon();
	static boolean gameOver = false;
	
	// Visual objects
	static Ground userGround;
	static Ground enemyGround;
	
	
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
		state = GameState.SELECTING_COMPKEMON;
		textBox.AnimateText("Welcome to the world of hacking!", false);
		textBox.AnimateText("Enter number corresponding to the Compkemon you wish to hack with: ", true);
		
		// override processCommand for commandProcessor in this state. When input is detected, perform this.
		commandProcessor = new CommandProcessor(){
			public void processCommand(String txt){
				// this will be called next time we press enter;
				Select();
				// TODO change gamestate locations to switch at the proper time
				//battleScene(myCompkemon, enemy);
				Intro();
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
		}
		
		textBox.AnimateText("Congratulations, your chosen Compkemon is: " + myCompkemon, false);	
		textBox.AnimateText("An enemy Compkemon hacked! You are under attack!", false);
		textBox.AnimateText("A wild " + enemy + " appeared!", false);
		textBox.AnimateText("Fight!", false);
		
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	

	// User chooses move to use
	public void ChooseMove() {
		
		state = GameState.CHOOSING_MOVE;
		
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
			
				// Display health bars
				BattleHandler.displayHealth(myCompkemon, enemy);	
				
				// Check for lingering effects on first
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
				turnCounter++;
			}
		};
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void Intro() {
		// FIXME you did something here to screw up animations
		userGround = new Ground(0, 0);
		
		state = GameState.INTRO;
		
		commandProcessor = new CommandProcessor(){
			public void processCommand(String txt) {
				// This is called next time we press enter
				if (Animations.done) {
					battleScene(myCompkemon, enemy);
				}
				
			}
		};
		
		

	}

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
		if (current.currentMove != null) {
			// User move begin					
			
			textBox.AnimateText(current + " used " + current.currentMove, false);
		
			// Move hit/miss
			if (BattleHandler.hitMiss(current.currentMove)) {
				// Alpha damage calculator and applier
				if (current.currentMove.power > 0) {
					textBox.AnimateText(other + " took damage!", false);
					other.currentHealth = (other.currentHealth - ((int)BattleHandler.damageCalculator(current, other, current.currentMove)));	
					if (other.currentHealth <= 0) {
						other.currentHealth = 0;
					}
					BattleHandler.displayHealth(myCompkemon, enemy);					
				}
				
				
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
					BattleHandler.displayHealth(myCompkemon, enemy);
				}
				
				
				// Splash salute - not done

			} else {
				textBox.AnimateText(current + "'s attack missed!", false);
			}
			
			
		}
		
		if (other.currentHealth <= 0) {
			System.out.println("I'm here! game over here!");
			loser = other;
			gameOver = true;
		}
		
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void battleScene(Compkemon myCompkemon, Compkemon enemy) {
		System.out.println("Battle STARTS");
		// Battle starts
		turnCounter = 0;
		textBox.AnimateText("Hello, welcome to a battle!", false);
		ChooseMove();
	} // end battleScene

	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void endGame(Compkemon loser) {
		state = GameState.END_GAME;
		textBox.AnimateText("Would you like to battle again? Y/N", true);
		
		commandProcessor = new CommandProcessor(){
			public void processCommand(String txt){
				// this will be called next time we press enter;
				switch (commandLine) {
					case "y" :
						Start();
						break;
					case "n" :
						System.exit(0);
						break;
				}
				
			}
		};
		textBox.AnimateText(loser + " has fainted!", false);
		
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
		
		
		
		// FIXME display pertinent text per gamestate
		switch (state){
		/*
			case WAITING_FOR_INPUT: {
				System.out.println("waiting for input");
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				break;
			}
		*/
			case SELECTING_COMPKEMON: {
				System.out.println("selecting compkemon");
				GamePanel.drawString(g2d, "1. Prototype" + "\n" + "2. Wrightson" + "\n" + "3. Alex" + "\n" + "4. Jeremiah" + "\n" + "5. Jackson" + "\n", 5, 5);
				g2d.setColor(Color.GREEN);
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				break;
			}
			
			// TODO make a battle intro state where animation will take place
			case INTRO: {
				System.out.println("Intro");
				userGround.move(0.1, 0);
				userGround.draw(g2d);
				textBox.Draw(g2d);
				panel.repaint();
				break;
			}
			
			case CHOOSING_MOVE: {
				System.out.println("choosing move");
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				break;
			}
			
			case APPLYING_EFFECTS: {
				System.out.println("applying effects");
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				break;
			}
			
			case END_GAME: {
				System.out.println("endgame");
				g2d.fillRect(0, 0, windowWidth, windowHeight);
				g2d.drawString("> ", 10, windowHeight - 10);
				textBox.Draw(g2d);
				break;
			}
			
		}
	}
}


