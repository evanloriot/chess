package classes;

public class Queen extends Piece {
	public Queen(String color) {
		super(color);
	}
	public String toString() {
		return super.toString() + "Q";
	}
	//TODO
	public boolean isLegal(Piece[][] board, String command, String color) {
		return false;
	}
	public void move(Piece[][] board, String move) {
		
	}
}
