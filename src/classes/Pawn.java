package classes;

public class Pawn extends Piece {
	public Pawn(String color) {
		super(color);
	}
	public String toString() {
		return super.toString() + "p";
	}
}
