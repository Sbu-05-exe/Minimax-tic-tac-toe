/**
 * CPU class, is a class that represents a computer playing tic-tac-toe.
 * The idea behind this class is that I will build the game such that users can play 
 * against each other or against a computer. Preferrably using the minimax algo
 * 
 */

public class CPU implements Player {

	private int playerNum;
	private String name;

	public CPU(String aName, int playerNum) {
		
		name = aName;
	
	} // CPU

	public Pos move() {

		// This is where I assume the minimax algorithm would come in

		// get available spots

		// pick a spot

		return null;

	} // move

	@Override
	public int getPlayerNum() {

		return playerNum;
			
	} // getPlayerNum;

	@Override
	public String getName() {
	
		return name;
	
	} // getName

	public String toString() {

		return name;

	} // toString();

} //