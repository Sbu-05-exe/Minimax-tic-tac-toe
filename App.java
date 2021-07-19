import java.util.function.Consumer;
import javafx.application.Application;

import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.shape.ArcType;

public class App extends Application {

	final int SIZE = Utility.SIZE;
	final double BLOCK_SIZE = Utility.BLOCK_SIZE;

	GraphicsContext gc;
	Stage gameStage;
	Canvas canvas;
	Game game;

	public Scene getGameScene() {

		Group root = new Group();

		canvas = new Canvas(Utility.SIZE, Utility.SIZE);
		gc = canvas.getGraphicsContext2D();

		canvas.setOnMouseClicked(event -> onPlayerClick(event.getX(), event.getY()));
		root.getChildren().add(canvas); 

		return new Scene(root);
	
	} // setUI

	public void start(Stage primaryStage) {

		// set the scene lol
		primaryStage.setScene(getGameScene());
		primaryStage.show();
		primaryStage.setTitle("TIC-TAC-TOE");

		// create game instance
		game = new Game();
		// set up game
		game.addUser("Player 1");
		game.addUser("Player 2");
		game.setFirstPlayer();

		// draw the game
		drawBoard();

		// Game tictactoe = new Game(this::drawCross , this::drawCircle);
		// tictactoe.startGame();

	} // start

	/** 
	 * A method used to draw the tic-tac-toe board
	 */

	private void drawBoard() {
		gc.setFill(Color.BLUE);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(5);

		for (int i = 1; i < 3; i++) {
			double y = SIZE/3.0 * i;
			gc.strokeLine(0,  y ,SIZE, y);
		}

		for (int i = 1; i < 3; i++) {
			double x = SIZE/3.0 * i;
			gc.strokeLine(x, 0, x, SIZE);
		
		}
	} // drawBoard

	/**
	 *  A method that executes everytime the canvas is clicked 
	 */
	public void onPlayerClick(double x, double y) {

		if (!game.isOver()) {

			Point move = Utility.convertMouseXYtoValidMove(x,y);

			// check valid move
			if (move == null) {

				// move is null when user clicks too close to border of a cell
				return;
			
			}

			// get player from game instances		
			if (game.playMove(move)) {
				//  playmvoe only return true if the move is valid

				Player currentPlayer = game.getCurrentPlayer();

				if (currentPlayer.getPlayerNum() == 1) {
					
					// player 1 draws crosses
					drawCross(move);

				} else {

					// player 2 draws circles
					drawCircle(move);

				}
				
				// switch the current player for next click;
				game.changeCurrentPlayer();		
				game.printBoard();

				// check game status

				if (game.hasWinner()) {

					drawWin(game.getWinCode());
					game.endGame();
					System.out.println("Congratulations! " + currentPlayer + " has won!");

				} else if (game.checkDraw()) {

					game.endGame();
					System.out.println("It's a tie!");
				 
				}
			} // if move is valid

		} else {

			System.out.println("The game has ended");
		} 

	} // onPlayerClick

	/** A method that is used to draw a cross on the canvas
	 * 	
	 * @param pos - indicates which block to draw the cross in
	 * 
	 */	

	private void drawCross(Point<Integer> p) {
		/* PRE CONDITION 
			pos.x and pos.y must be within bounds [0,2]
		*/

		double offset = SIZE * 0.055;

		double x1,y1,x2,y2;
		double startX, startY, endX, endY;

		x1 = 0 + offset;
		y1 = 0 + offset;
		x2 = SIZE/3.0 - offset;
		y2 = SIZE/3.0 - offset;


		startX = x1 + BLOCK_SIZE * p.getX();
		startY = y1 + BLOCK_SIZE * (p.getY());
		endX = x2 + BLOCK_SIZE * p.getX();
		endY = y2 + BLOCK_SIZE * (p.getY());

		gc.strokeLine(startX, startY, endX, endY);
		gc.strokeLine(startX, endY, endX, startY);
		
	} // drawCross

	/** A method used to test if all cross will be drawn centered
	 * 
	 * 	draw All crosses
	 */
	private void drawAllCrosses() {

		for (int row = 0; row < 3; row++) {

			for (int col = 0; col < 3; col++) {

				drawCross(new Point(row,col));
			
			}
		}

	} // drawAllCrosses

	/** 
	 * 	A method that draws a circle on the canvas in a particular block
	 * 	
	 * @param pos - the position to draw the circle in
	 * 
	 */

	private void drawCircle(Point<Integer> p) {
		
		/* PRE-CONDITION
			p.getX() and pos.getY() must be in the range [0,2]
		*/
		int offset = 50;

		double radius = (Utility.BLOCK_SIZE) - offset;
		double x = offset/2.0;
		double y = offset/2.0;

		gc.strokeOval(x + Utility.BLOCK_SIZE * p.getX(), y + Utility.BLOCK_SIZE * (p.getY()), radius, radius);

	}// drawBoard

	/**
	 * A method used to test if all the circles will be drawn centrally
	 * 
	 */

	private void drawAllCircles() {

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {

				drawCircle(new Point(row, col));

			} //inner for

		} // outer for

	} // drawAllCircles

	private void drawWin(int num) {
		// the determinant, is what I will use to determine which win line to 
		int determinant = num / 3;

		int offset = 20;
		long x1, x2, y1, y2;
		long midpoint = Math.round(Utility.BLOCK_SIZE/2);
		y1 = y2 = x2 = x1 = 0;

		switch (determinant) {
			case 0: 
				/* horizontal win */
				// line will spane the length of the board less the offset on both end
				x1 = 0 + offset;
				x2 = Utility.SIZE - offset;
				y1 = midpoint + Math.round(Utility.BLOCK_SIZE * num);
				y2 = y1; 

				break;
			case 1:

				/* vertical win */
				System.out.println("Vertical win");
				y1 = 0 + offset;
				y2 = Utility.SIZE - offset;
				x1 = midpoint + Math.round(Utility.BLOCK_SIZE * (num % 3));
				x2 = x1; 

				break;

			case 2:
				/* diagonal win */

				long inversionFactor = (num % 3) * Utility.SIZE;
				// long inversionFactor = 0;
				x1 = 0 + offset;
				y1 = inversionFactor - (0 + offset);
				x2 = Utility.SIZE - offset;
				y2 = inversionFactor - (Utility.SIZE - offset);

				x1 = Math.abs(x1);
				y1 = Math.abs(y1);
				x2 = Math.abs(x2);
				y2 = Math.abs(y2);
				break;

			default:
				throw new Error("Invalid WinCode");

		}

		gc.setLineWidth(10);
		gc.setStroke(Color.RED);
		gc.strokeLine(x1,y1, x2, y2);


	} // drawWin

	public static void main(String[] args) {
		
		launch(args);

	} // main

} // app