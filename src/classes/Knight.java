package classes;

import chess.Chess;

public class Knight extends Piece {
	public Knight(String color) {
		super(color);
	}
	public String toString() {
		return super.toString() + "N";
	}
	//TODO
	public boolean isLegal(Piece[][] board, String command, String color) {
		return false;
	}
	public void move(Piece[][] board, String move) {
		
	}
}
