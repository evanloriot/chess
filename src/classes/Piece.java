package classes;

public class Piece {
	public String color;
	public boolean hasMoved;
	public Piece(String color) {
		this.color = color;
	}
	public String toString() {
		if(color.equals("Black")) {
			return "b";
		}
		else {
			return "w";
		}
	}
}
