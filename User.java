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
	public Pos move() {

		String input;	
		Pos move = null;

		while (true) {

			System.out.print("Please enter the co-ordinate that you want to play (x,y):");
			input = in.nextLine();

			if (isValidInput(input)) {

				move = getMoveFromUserInput(input);
				break;

			} else {

				System.out.println("Invalid move please try again:");
			
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

	public void setDrawMethod(Consumer drawMethod) {
		draw = drawMethod;
	}; // setDrawMethod

	public void draw(Pos pos) {
		draw.accept(pos);
	}; // draw
	
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
	public static Pos getMoveFromUserInput(String input) {

		// Pre condition: The input is a valid move contain 2 integers with values between 0 and 3 
		// int x = 0;
		// int y = 0;
		int[] coords = new int[2];

		int count = 0;
		for (int i = 0; i < input.length(); i++) {

			char c = input.charAt(i);

			if (isWithinRange(c, '1', '3')) {

				// not using 48 because we want the move between the numbers [0,2] if we are going
				// to play it on a board, but a user most likely enters a number from [1,3] so we 
				// subtract 1 to balance
				coords[count] = (int) c - 49;
				count++;
			
			}
			
		}

		return new Pos(coords[0],coords[1]);

	} // getMoveFromUserInput

	/** 
	 * checks if a users move is valid or not
	 */
	public static boolean isValidInput(String input) {

		if (input.length() < 2) {
			
			return false;

		}

		int count = 0;
		for (char c: input.toCharArray()) {
			
			if (isWithinRange(c, '1', '3')) count++;

		}

		// only return true if there is a valijd x and y co-ordinate
		return count == 2;

	} // isValidInput;


	/** 
	 * A method that test a number of user generated inputs 
	 * 
	 * @param reps - the amount of times to repeat the test
	 */
	public static void testMoveMethods(int reps) {
		
		Scanner in = new Scanner(System.in);
		User me = new User("Sibu", 1, in);

		for (int i = 0; i < reps; i++) {

			Pos move = me.move();
			System.out.println(move);
		}

		// This method has passed all the tests I could think of

	} // testMoveMethods


} // Player