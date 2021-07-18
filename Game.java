import java.util.Scanner;

public class Game {	

	private boolean gameOver; 
	private int moves;
	private int numPlayers = 0;
	private Player[] players = new Player[2]; 
	private Player currentPlayer;
	private Board board;
	private Scanner input = new Scanner(System.in); 

	public Game() {

		gameOver = false;
		board = new Board();

	} // Game constructor

	public void endGame() {
		gameOver = true;
	} // false

	public boolean isOver() {
		return gameOver;
	} // isGameOver

	public boolean hasWinner() {
		board.checkForWinner();
		return board.hasWinner();
	} // hasWinner;

	public int getWinCode() {
		return board.getWinCode();

	} // getWinStatus

	public boolean checkDraw() {
		System.out.println("moves played: " + moves);
		return moves >= 9;
	} // checkdraw

	public void addUser(String aUsername) {

		players[numPlayers] = new User(aUsername, numPlayers+1 , input);
		numPlayers++;

	} // addPlayerOne

	public boolean playMove(Point move) {

		return board.playMove(move, currentPlayer);

	} // playMove

	public boolean isValidMove(Point<Integer> move) {

		return board.isValidMove(move.getX(), move.getY());

	} // move

	public void printBoard() {

		System.out.println(board);

	} // printBoard

	public Player getCurrentPlayer() {

		return currentPlayer;
	
	} // getCurrentPlayer

	public void setFirstPlayer() {

		moves = 0;
		currentPlayer = players[0];

	} // setFirst Player 

	public void changeCurrentPlayer() {
		moves++;
		currentPlayer = players[moves % 2]; 

	} // switch

	public void setUpPlayers() {
		// hard code players

		addUser("Psych");
		addUser("Kidus");

		//TODO ask user for input for their names
		// and ask them who they want to play against, user or player

	} // setUpPlayers

	/**
	 *  This is the method that contains the game logic
	 * 
	 *  In future, I would like to add parameters to the game so that we can allow the user to set it up
	 * 
	 */
	public void play() {
		
		//set up game
		int moves = 0;
		Point<Integer> move;
		boolean validMove; 

		while (true) {
		//the game has started

			//visibility of system status
			System.out.println("It is " + currentPlayer.toString() + "'s turn");

			do {

				move = currentPlayer.move();
				validMove = board.isValidMove(move.getX(), move.getY()); 

			} while (!validMove);

			System.out.println("outside of the loop");
			board.playMove(move, currentPlayer); // make them move

			// print the game for debugging purPointe
			printBoard();

			// check if someone has won
			board.checkForWinner(); // have ot check if someone first
			if (board.hasWinner()) {

				System.out.println("Congratulations! " + currentPlayer + " has won!");
				endGame();
				break;

			}  

			// check that the game has drawn
			if (checkDraw()) {

				System.out.println("It's a tie!");
				endGame();
				break;
			
			}

			// make it the next players turn
			changeCurrentPlayer();
		
		} // while
		
	} // startGame

	public static void main(String[] args) {

		Game tictactoe = new Game();
		tictactoe.setUpPlayers();
		tictactoe.setFirstPlayer();
		tictactoe.play();

	}// main

}// Game class
