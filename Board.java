public class Board {

	// constructor 
	private int size; 
	private int[][] board;

	public Board(int aSize) {

		size = aSize;
		board = new int[size][size];

	}

	public Board() {
		this(3);
	}

	// move

	// public void (int x, int y, int playerNum) {


	// } 
	
	// todo: checkwinner

	public boolean verticalChecks() {

		for (int col = 0; col < size; col++ ) {

			// if all entries are equal then someone one

			int first = board[col][0];
			int second = board[col][1];
			int third = board[col][2];

			if (first != 0 && first == second && second == third) {
				return true;
			}

		}

		return false;

	} // verticalChecks

	public boolean horizontalChecks() {

		for (int row = 0; row < size; row++ ) {

			// if all entries are equal then someone one
			int first = board[0][row];
			int second = board[1][row];
			int third = board[2][row];
			if (first != 0 && first == second && second == third) {
			
				return true;
			
			} //if

		} // for

		return false;

	} // verticalChecks

	public boolean diagonalChecks() {

		if (board[1][1] == 0) {
			return false;
		}

		if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}

		if (board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
			return true;
		}

		return false;

	} // diagonalChecks

	public boolean checkWinner() {

		return (verticalChecks() || horizontalChecks() || diagonalChecks());
		
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

	public void playMove(int x, int y, int playerNum) {

		y = 2 - y;

		board[y][x] = playerNum;

	}

	public void playMove(Pos pos, int playerNum) {
		this.playMove(pos.x, pos.y, playerNum);
	} // overloaded move

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