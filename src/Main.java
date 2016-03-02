import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;

public class Main {
	
	static Scanner scanner = new Scanner(System.in);
	static Compkemon myCompkemon;
	static Compkemon enemy;
	static TypeTable typeTable = new TypeTable();
	static int turnCounter;
	static Game game = new Game();
	
	public static void main(String[] args) {
		
		int myCompkemonScanned = 0;
		myCompkemon = new Compkemon();
		enemy = new Compkemon();
		
		// Gets user input for compkemon
		System.out.println("1. Prototype" + "\n" + "2. Wrightson" + "\n" + "3. Alex" + "\n" + "4. Jeremiah" + "\n" + 
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
			case 4:
				myCompkemon = new Compkemon(CompkemonList.Jeremiah);
		}
		
		System.out.println("Congratulations, your chosen Compkemon is: " + myCompkemon);
		
		// set enemy compkemon here;
		enemy = new Compkemon(CompkemonList.Prototype);
		
		System.out.println("An enemy Compkemon hacked! You are under attack!");
		System.out.println("A wild " + enemy + " appeared!");
		System.out.println("Fight!");
		
		Game.battleScene(myCompkemon, enemy);
		
		System.out.println("Battle has concluded");	
	} // end main method

	
}
