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
						
	}
	
	
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
				System.out.println(myCompkemon + " used " + myMoveUsed);
				
				if (myMoveUsed.power > 0) {
					System.out.println(enemy + " took damage!");
					enemy.setHealth(enemy.currentHealth - myMoveUsed.power);
					System.out.println(enemy + " HP: " + enemy.currentHealth);
				}
				
				if (enemy.currentHealth <= 0) {
					loser = enemy;
					break;					
				}
				
				System.out.println(enemy + " used " + enemyMoveUsed);
				if (enemyMoveUsed.power > 0) {
					System.out.println(myCompkemon + " took damage!");
					myCompkemon.setHealth(myCompkemon.currentHealth - enemyMoveUsed.power);
					System.out.println(myCompkemon + " HP: " + myCompkemon.currentHealth);
				}
				
				if (myCompkemon.currentHealth <= 0) {
					loser = myCompkemon;
					break;					
				}
				
			}
		}
		
		System.out.println(loser + " has fainted");
	}
}
