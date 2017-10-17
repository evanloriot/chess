package classes;

public class Piece {
	String color;
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
