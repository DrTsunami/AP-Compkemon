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
	SELECTING_COMPKEMON,
	BATTLE,
	CHOOSING_MOVE,
	APPLYING_EFFECTS,
}

public class Game {
	static GameState state;
	GamePanel panel;
	
	static String commandLine = "";
	TextBox textBox;


	public void init() {
		textBox = new TextBox(new Vector2(0, 0), new Vector2(200, 200));
		state = GameState.SELECTING_COMPKEMON;
		panel.repaint();
		textBox.AnimateText("Welcome to the world of hacking!", false);
		textBox.AnimateText("Enter number corresponding to the Compkemon you wish to hack with: ", true);
	}
	
	public void ProcessCommand(String command){
		String text = "";
		// TODO handle a command
		
		if (command.length() > 0) {
			switch (state){
				case SELECTING_COMPKEMON:{	
					Select();
					commandLine = "";
					state = GameState.BATTLE;
					System.out.println("State switched to Battle");
					System.out.println("CommandLine is deleted");
					//ChooseMove();
					battleScene(myCompkemon, enemy);
					break;
				}
				case BATTLE : {
					break;
				}
				case CHOOSING_MOVE : {
					myCompkemon.selectMove();
					commandLine = "";
					System.out.println("CommandLine is deleted");
					
					break;
				}
			}
		} 
	}
	
	public void KeyPress(KeyEvent keyCode){
		char c = keyCode.getKeyChar();
		int i = (int)c;
		if (i == 10){ // enter
			//if waiting for enter to continue
			//	continue
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
	
	public void MousePress(MouseEvent e){
		
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	static Compkemon myCompkemon;
	static Compkemon enemy;
	static TypeTable typeTable = new TypeTable();
	static int turnCounter;
	
	public void Select() {
		int myCompkemonScanned = 0;
		myCompkemon = new Compkemon();
		enemy = new Compkemon();
		enemy = new Compkemon(CompkemonList.Prototype);
		
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
		
		textBox.AnimateText("Congratulations, your chosen Compkemon is: " + myCompkemon, false);	
		textBox.AnimateText("An enemy Compkemon hacked! You are under attack!", false);
		textBox.AnimateText("A wild " + enemy + " appeared!", false);
		textBox.AnimateText("Fight!", false);
		
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Declare static variables
	static int myMove;
	static int enemyMove;
	static Move firstMove = new Move();
	static Move secondMove = new Move();
	static int priority;
	static Compkemon loser = new Compkemon();
	
	public void ChooseMove() {
		
		state = GameState.CHOOSING_MOVE;
		textBox.AnimateText("Choose move: " + myCompkemon.getMoveset(), true);
		enemyMove = (int)(Math.random() * enemy.moveset.length);
		enemy.currentMove = enemy.moveset[enemyMove];	
		
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
		if (current.currentMove != null) {
			// User move begin					
			System.out.println(current + " used " + firstMove);
			
			// Move hit/miss
			if (BattleHandler.hitMiss(firstMove)) {
				// Alpha damage calculator and applier
				if (firstMove.power > 0) {
					System.out.println(other + " took damage!");
					other.health = (other.currentHealth - ((int)BattleHandler.damageCalculator(current, other, firstMove)));	
					if (other.currentHealth <= 0) {
						other.currentHealth = 0;
					}
					BattleHandler.displayHealth(myCompkemon, enemy);					
				}
				
				
				// Check for move effect. If true, effects are applied
				if (firstMove.hasEffect) {
					Effect effect = firstMove.getEffect(current, other);
					
					if (firstMove.toSelf) {
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
					
					System.out.println("Added an effect!");
					BattleHandler.displayHealth(myCompkemon, enemy);
				}
				
				
				// Splash salute - not done

			} else {
				System.out.println(current + "'s attack missed!");
			}
		}
		
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	public void battleScene(Compkemon myCompkemon, Compkemon enemy) {

		// Battle starts
		turnCounter = 0;
		System.out.println("switched to Battle state");
		textBox.AnimateText("Hello, welcome to a battle!", false);
		
		//while (myCompkemon.currentHealth > 0 || enemy.currentHealth > 0) {
			
			//FIXME break up battle into more method
			
			// Choose move
			ChooseMove();
			
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
			// TODO make sure this block does not get deleted
			if (second.currentHealth <= 0) {
				loser = second;
				//break;					
			}

			// Enemy move begin
			EffectHandler(second);
			MoveHandler(second, first);
				
			// Check for first health. If fainted, end the game
			if (first.currentHealth <= 0) {
				loser = first;
				//break;					
			}
			
			// Turn tracker increases
			turnCounter++;
			
			
		//} // end while loop
		
		System.out.println(loser + " has fainted");
	} // end battleScene

	
	
	public void draw(Graphics2D g2d) {
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

				GamePanel.drawString(g2d, "> Enter number corresponding to the Compkemon you wish to hack with: ", 5, 5);
				GamePanel.drawString(g2d, "1. Prototype" + "\n" + "2. Wrightson" + "\n" + "3. Alex" + "\n" + "4. Jeremiah" + "\n" + "5. Jackson" + "\n", 5, (5 + g2d.getFontMetrics().getHeight()));
				
				g2d.setColor(Color.GREEN);
				
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				break;
			}
			
			case CHOOSING_MOVE: {
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				break;
			}
			
			case APPLYING_EFFECTS: {
				g2d.drawString("> ", 10, windowHeight - 10);
				g2d.drawString(commandLine, 20 + 5, windowHeight - 10);
				textBox.Draw(g2d);
				break;
			}
			
		
			
		}
	}
}


