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

	final int SIZE = 500;
	final double BLOCK_SIZE = 500/3;

	GraphicsContext gc;
	Stage window;

	public void start(Stage primaryStage) {

		window = primaryStage;
		Group root = new Group();

		Canvas canvas = new Canvas(SIZE,SIZE);
		gc = canvas.getGraphicsContext2D();

		drawBoard();

		/*/ Test methods for drawing crosses and circles /*/
		// drawCircle(new Pos(0,0));
		// drawAllCircles();
		// drawCross(new Pos(0,0));
		// drawAllCrosses();

		root.getChildren().add(canvas); 


		window.setScene(new Scene(root));
		window.show();

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


	/** A method that is used to draw a cross on the canvas
	 * 	
	 * @param gc - the drawing pen used to drawn on the canvas
	 * @param pos - indicates which block to draw the cross in
	 * 
	 */	

	private void drawCross(Pos pos) {
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


		startX = x1 + BLOCK_SIZE * pos.x;
		startY = y1 + BLOCK_SIZE * (2 - pos.y);
		endX = x2 + BLOCK_SIZE * pos.x;
		endY = y2 + BLOCK_SIZE * (2 - pos.y);

		gc.strokeLine(startX,startY, endX, endY);
		gc.strokeLine(startX, endY, endX, startY);
		

	} // drawCross

	/** A method used to test if all cross will be drawn centered
	 * 
	 * draw All crosses
	 */
	private void drawAllCrosses() {

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {

				drawCross(new Pos(row,col));
			
			}
		}
	} // drawAllCrosses

	/** 
	 * 	A method that draws a circle on the canvas in a particular block
	 * 	
	 * @param gc - the "pen" used to draw the circle
	 * @param pos - the position to draw the circle in
	 * 
	 */

	private void drawCircle(Pos pos) {
		
		/* PRE-CONDITION
			pos.x and pos.y must be in the range [0,2]
		*/
		int offset = 50;

		double radius = (BLOCK_SIZE) - offset;
		double x = offset/2.0;
		double y = offset/2.0;

		gc.strokeOval(x + BLOCK_SIZE * pos.x, y + BLOCK_SIZE * (2 - pos.y), radius, radius);

	}// drawBoard

	/**
	 * A method used to test if all the circles will be drawn centrally
	 * 
	 * @param gc - The "pen" used to draw the circles on the canvas
	 * 
	 */

	private void drawAllCircles() {

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {

				drawCircle(new Pos(row, col));

			} //inner for
		} // outer for
	} // drawAllCircles

	public static void main(String[] args) {
		launch(args);
	} // main

} // app