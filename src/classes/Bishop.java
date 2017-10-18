package classes;

import chess.Chess;

public class Bishop extends Piece {
	public Bishop(String color) {
		super(color);
	}
	public String toString() {
		return super.toString() + "B";
	}
	//TODO
	public boolean isLegal(Piece[][] board, String command, String color) {
		return false;
	}
	public void move(Piece[][] board, String move) {
		
	}
}
