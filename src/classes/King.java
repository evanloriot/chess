package classes;

public class King extends Piece {
	public King(String color) {
		super(color);
	}
	public String toString() {
		return super.toString() + "K";
	}
	//TODO
	public boolean isLegal(Piece[][] board, String command, String color) {
		return false;
	}
	public void move(Piece[][] board, String move) {
		
	}
}
