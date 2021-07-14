public class Pos {

	public int x;
	public int y;

	public Pos(int a, int b) {

		x = a;
		y = b;

	} // constructor

	public String toString() {

		StringBuffer sb = new StringBuffer("(");
		sb.append(x).append(",").append(y);

		return sb.append(")").toString();
	} // toString

} // 