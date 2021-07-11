import java.util.Scanner;

public class Game {

	public static void main(String[] args) {

		//set up game
		int moves = 0;
		Player[] players = new Player[2]; 
		Player current;
		Scanner input = new Scanner(System.in);

		// ask user how many players (1 or 2)

		// set up players	
		players[0] = new User("Psych", 1, input);
		players[1] = new User("JJ", 2, input);

		Board game = new Board();
		while (true) {

			// pick whose turn it is
			current = players[moves % 2];

			// make them move
			current.move();
			moves++;

			// check if there is a winner
			if (game.checkWinner()) {

				System.out.println("Congratulations Player: " + current + " has won!");
				break;

			} // 

			// check that the game has ended?
			if (moves == 9) {
				break;
			}
		
		} // while
		
	}// main

}// Game class