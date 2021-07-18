
public class Utility  {

	public static final int verticalCode = 0;
	public static final int horizontalCode = 3;
	public static final int diagonalCode = 6;
	public static final int SIZE = 500;
	public static final float BLOCK_SIZE = 500/3;

	// returns null if you clicked if on the line instead of inside a cell
	public static Point convertMouseXYtoValidMove(double mouseX, double mouseY) {

		int x = (int) mouseX;
		int y = (int) mouseY;

		if (x % BLOCK_SIZE < 5 || y % BLOCK_SIZE < 5) return null; // do nothing if you click on the border
		
		x /= BLOCK_SIZE;
		y /= BLOCK_SIZE;

		// 2-y because the co-ordinates are inverted
		return new Point(x,y);
	}

	public static <T extends Comparable> boolean isWithinRange (T element, T upper, T lower ) {
		
		// return x is an element of [lower,upper];

		return lower.compareTo(element) >= 0 && element.compareTo(upper) >= 0;

	} // isWithInRange();


	public static void main(String[] args) {

		// runTests();

	} // main

	public static void runTests() {

		System.out.println(isWithinRange('2', '1', '3')); // true
		System.out.println(isWithinRange('0', '1', '3')); // false
		System.out.println(isWithinRange(1,0,2));	 // false
		System.out.println(isWithinRange(0,0,2));	 // false
	
	} // runTests

} // Utility