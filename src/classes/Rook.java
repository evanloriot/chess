package classes;

import chess.Chess;

public class Rook extends Piece {
	public Rook(String color) {
		super(color);
	}
	public String toString() {
		return super.toString() + "R";
	}
	//TODO
	public boolean isLegal(Piece[][] board, String command, String color) {
		return false;
	}
	public void move(Piece[][] board, String move) {
		
	}
}
