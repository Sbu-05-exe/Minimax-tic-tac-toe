
public class Point <T extends Number> {

	private T x;
	private T y;

	public Point(T a, T b) {

		x = a;
		y = b;

	} // constructor

	public String toString() {

		StringBuffer sb = new StringBuffer("(");
		sb.append(x).append(",").append(y);

		return sb.append(")").toString();
	} // toString

	public T getX() {
		return x;
	} // getX

	public T getY() {
		return y;
	} // getY

	public void setX(T num) {
		x = num;
	} //setY

	public void setY(T num) {
		y = num;
	} // setY

	public static void main(String[] args) {
		
		Point location = new Point(1,2);
		System.out.println(location);
	
	} // main

} // 