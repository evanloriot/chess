package classes;

import chess.Chess;

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
	public void move(Piece[][] board, String move) {
		String pos = move.substring(0,2);
		String dest = move.substring(3,5);
		board[Chess.getColumn(dest)][Chess.getRow(dest)] = board[Chess.getColumn(pos)][Chess.getRow(pos)];
		board[Chess.getColumn(pos)][Chess.getRow(pos)] = null;
	}
}
