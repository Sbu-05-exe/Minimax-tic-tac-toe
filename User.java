import java.util.Scanner;
import java.util.function.Consumer;

/**
 *  A class that represents a human player. In other words, a scanner will be used to determine the move of the player
 */ 
public class User implements Player {

	private int playerNum;
	private Scanner in;
	private String name;
	private Consumer draw;

	public User(String aName, int aPlayerNum, Scanner aScanner) {

		name = aName;
		playerNum = aPlayerNum;
		in = aScanner;

	} // User Constructor;

	/** 
	 * User move methods, takes input from terminal and returns a Pos object.
	 * 	
	 * Does not return until the user has entered a valid input
	 * 
	 */

	public Point<Integer> move() {

		System.out.print("Please enter the co-ordinate that you want to play (x,y):");
		String input = in.nextLine();	

		Point<Integer> move = getMoveFromUserInput(input);

		// when user enters input, we expect it to be in the range 1-3 as labelled by the game
 		// since the game Object only accepts co-ordinates in range 0-2 because of the 2D array used to represent the games state
 		// we will decrease the x and y co-ordinates by 1
		move.setX(move.getX() - 1);
		move.setY(move.getY() - 1);
		move.setY(2 - move.getY()); // inverting the y so it keeps with the normal mapping of a cartesian plane that our user expects

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

	// Static utility Methods

	/**
	 * A method that checks if a character is within a certain range
	 */
	public static boolean isWithinRange(char x, char lower, char upper) {
		return (x >= lower) && (x <= upper);
	} // withinRange

	/** 
	 * Finds the first two numerical characters in a string
	 */	
	public static Point getMoveFromUserInput(String input) {

		// Pre condition: The input is a valid move contain 2 integers with values between 0 and 3 
		// int x = 0;
		// int y = 0;
		int[] coords = new int[2];

		int count = 0;
		for (int i = 0; i < input.length(); i++) {

			char c = input.charAt(i);

			if (isWithinRange(c, '1', '3')) {

				coords[count] = ((int) c) - 48;
				count++;

			}
			
		}

		return new Point(coords[0] ,coords[1]);

	} // getMoveFromUserInput

	public static void main(String[] args) {
		testMoveMethods(10);
	} // main

	/** 
	 * A method that test a number of user generated inputs 
	 * 
	 * @param reps - the amount of times to repeat the test
	 */
	public static void testMoveMethods(int reps) {
		
		Scanner in = new Scanner(System.in);
		User me = new User("Sibu", 1, in);

		for (int i = 0; i < reps; i++) {

			Point<Integer> move = me.move();
			System.out.println(move);
		}

		// This method has passed all the tests I could think of

	} // testMoveMethods

} // User
