import javax.swing.*;

public class Requirements {
	
	boolean bool;
	int[] numberArray;
	
	public Requirements() {
		numberArray = new int[5];
	}
	
	public void whileFunction() {
		bool = false;
		
		while (bool = false) {
			System.out.println("haha i'm a boolean and a while loop!");
			bool = true;
		}
	}
	
	public void forEachFunction() {
		for (int i : numberArray) {
			System.out.println("number counter: " + i);
		}
	}
	
	public void paneFunction() {
		JOptionPane.showInputDialog("Enter your name");
	}
	
	
	
	
	
}
