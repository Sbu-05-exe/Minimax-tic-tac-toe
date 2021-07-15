import java.util.Scanner;
import java.util.function.Consumer;

public class Game {	

	Player[] players = new Player[2]; 
	Board game;

	public Game(Consumer<Pos> aDrawReferenceForCrosses, Consumer<Pos>aDrawReferenceForCircles) {
		
		players = new Player[2];
		Scanner input = new Scanner(System.in);
		// ask user how many players (1 or 2)

		// set up players
		players[0] = new User("Psych", 1, input);
		User userOne = (User) players[0];
		userOne.setDrawMethod(aDrawReferenceForCrosses);

		players[1] = new User("JJ", 2, input);
		User userTwo = (User) players[1];
		userTwo.setDrawMethod(aDrawReferenceForCircles);

		//set up board
		game = new Board();

	}//


	/**
	 *  This is the method that contains the game logic
	 * 
	 *  In future, I would like to add parameters to the game so that we can allow the user to set it up
	 * 
	 */
	public void startGame() {
		
		//set up game
		int moves = 0;
		Player currentPlayer;

		while (true) {

			currentPlayer = players[moves % 2]; // pick whose turn it is

			//visibility of system status
			System.out.println("It is " + currentPlayer.toString() + "'s turn");
			Pos movePlayed = currentPlayer.move();
			int playerNum = currentPlayer.getPlayerNum();
			game.playMove(movePlayed, playerNum); // make them move
			moves++; // track how many moves have been made


			// print the game for debugging purpose
			System.out.println(); // making the outputer look less cluttered
			System.out.println(game);

			// check if someone has one
			if (game.checkWinner()) {

				System.out.println("Congratulations Player: " + currentPlayer + " has won!");
				break;

			} // 

			// check that the game has ended?
			if (moves >= 9) {

				System.out.println("It's a tie!");
				break;
			
			}
		
		} // while
		
	} // startGame

	public static void main(String[] args) {

		System.out.println("Please work");
		// make a while loop and ask the user if they want to quit
		// startGame();
		
	}// main

}// Game class