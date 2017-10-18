package classes;

public class Pawn extends Piece {
	public Pawn(String color) {
		super(color);
	}
	public String toString() {
		return super.toString() + "p";
	}
	//TODO
	public boolean isLegal(Piece[][] board, String command, String color) {
		return false;
	}
	public void move(Piece[][] board, String move) {
		
	}
}
