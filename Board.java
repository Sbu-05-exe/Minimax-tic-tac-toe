public class Board {

	// constructor 
	private int size;
	private int winCode;
	private int[][] board;

	public Board(int aSize) {

		winCode = -1;
		size = aSize;
		board = new int[size][size];

	}

	public Board() {
		this(3);
	}

	public boolean isCellEmpty(int x, int y) {

		System.out.println(new Point(x,y));
		return board[y][x] == 0;
	} //isCellEmpty

	private boolean verticalWin() {

		for (int col = 0; col < size; col++ ) {

			// if all entries are equal then someone one

			int first = board[col][0];
			int second = board[col][1];
			int third = board[col][2];

			if (first != 0 && first == second && second == third) {

				winCode = col + Utility.verticalCode;

				return true;
			}

		}

		return false;

	} // verticalWin

	private boolean horizontalWin() {

		for (int row = 0; row < size; row++ ) {

			// if all entries are equal then someone one
			int first = board[0][row];
			int second = board[1][row];
			int third = board[2][row];

			if (first != 0 && first == second && second == third) {
				
				winCode = row + Utility.horizontalCode;
				return true;
			
			} //if

		} // for

		return false;

	} // horizontalWin

	private boolean diagonalWinner() {

		if (board[1][1] == 0) {
			// if the middle cell is empty, then no one could have possibly won using a diagonal 
			// possibly win so we return early
			return false;

		} else {

			if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
				winCode = 0 + Utility.diagonalCode;
				return true;
			}

			if (board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
				winCode = 1 + Utility.diagonalCode;
				return true;
			}
		
			return false;
		}


	} // diagonalWinner

	public boolean hasWinner() {

		return winCode != -1; 

	} // checkWinner

	public void checkForWinner() {
		
		// using an if statement so we don't have to check all 3
		if (verticalWin()){
			
			return;

		} else if (horizontalWin()) {
			
			return;

		} else if (diagonalWinner()) {
			
			return;
		
		}

	}

	public int getWinCode() {

		return winCode;
		
	} // checkWinner

	public String toString() {

		StringBuffer sb = new StringBuffer(""); 
		for (int row = 0; row < size; row++) {

			for (int col = 0; col < size; col++) {

				sb.append(board[row][col]);

				if (col != size-1) {
					sb.append(" | ");
				}

			} // inner for
			sb.append("\n");

			// syntactic sugar (option)
			if (row != size-1) {

				sb.append("---------");
				sb.append("\n");

			}
		
		} // outer for	

		return sb.toString();

	}// toString

	/**
	 * A method that checks if a players move is valid or not
	 * 
	 */

	public boolean isValidMove(int x, int y) {

		//A move is invalid
		// 1 - the cell that someone is trying to move to, is not empty
		// 2 - the co-ordinate of the cell is out of bounds of our double board array

		// First check if move is valid
		if (!isCellEmpty(x,y)) {

			return false; // invalid move case 1

		} else if (!Utility.isWithinRange(x,0,2) || !Utility.isWithinRange(y,0,2)) {

			return false; // invalid move case 2 
			
		}

		return true;

	} // isValidMove

	/**
	 *  Method that places a players move onto the board and returns true if the move was valid and false if the move was not valid
	 * 
	 */

	public boolean playMove(int x, int y, int playerNum) {

		if (isValidMove(x,y)) {

			board[y][x] = playerNum;
			return true;
		
		} else {

			return false;
		}
		
		

	}// playMove

	public boolean playMove(int x, int y, Player player) {
		
		return this.playMove(x,y, player.getPlayerNum());

	} // overloaded playMove

	public boolean playMove(Point<Integer> p, int playerNum) {
	
		return this.playMove(p.getX(), p.getY(), playerNum);
	
	} // overloaded playMove

	public boolean playMove(Point<Integer> p, Player player) {

		return this.playMove(p.getX(), p.getY(), player.getPlayerNum());

	} // overload playMove;

	// public static void main(String[] args) {
		
	// 	Board board = new Board();

	// 	// System.out.println(board);

	// 	// diagonal winner
	// 	// board.move(0,0,1);
	// 	// board.move(1,1,1);
	// 	// board.move(1,2,1);

	// 	board.move(0,0,1);
	// 	board.move(0,1,1);
	// 	board.move(0,2,1);

	// 	System.out.println(board);
	// 	System.out.println(board.checkWinner()); // true

	// }

} // Board