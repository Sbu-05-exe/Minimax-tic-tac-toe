import java.util.Scanner;

/**
 *  A class that represents a human player. In other words, a scanner will be used to determine the move of the player
 */ 
public class User implements Player {

	private int playerNum;
	private Scanner in;
	private String name;

	public User(String aName, int aPlayerNum, Scanner aScanner) {

		name = aName;
		playerNum = aPlayerNum;
		in = aScanner;

	} // User;

	/** 
	 * Finds the first two numerical characters in a string
	 */
	public Pos getMove(String input) {

		for (int i = 0; i < input.length(); i++) {

			char c = input.charAt(i);

			
		}

		return Pos;

	} // getMove

	/** 
	 * checks if a users move is valid or not
	 */
	public boolean isValidInput(String input) {

		if (input.length() < 2) {
			return false;
		}

		int count = 0;
		for (char c: String input) {

			if ((int) c > 48 && (int) c < 58) count++;
		
		}

		return count == 2;

	} // isValidInput;

	public Pos move() {

		String input;	
		Pos move = null;

		while (true) {

			System.out.print("Please enter the co-ordinate that you want to play (x,y):");
			input = in.nextLine();

			if (isValidInput(input)) {
				move = new Pos(input.charAt(0), input.charAt(2));
				break;
			}
 
		} // while

		return move;

	} // move

	@Override
	public int getPlayerNum() {
		return playerNum;
	} // getPlayerNum

	@Override
	public String getName() {
		return name;
	} // getName

	@Override
	public String toString() {
		return name;
	} // toString

	public static void main(String[] args) {

		char zero = '0';
		char nine = '9';

		int iZero = (int) zero;
		int iNine = (int) nine;
		// int zero = 0;
		// int nine = 9;

		System.out.println(iZero);
		System.out.println(iNine);	
		
	} // main

} // Player