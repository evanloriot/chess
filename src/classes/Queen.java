package classes;

public class Queen extends Piece {
	public Queen(String color) {
		super(color);
	}
	public String toString() {
		return super.toString() + "Q";
	}
}
