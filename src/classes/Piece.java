package classes;

import chess.Chess;

public class Piece {
	public String color;
	public String position;
	public boolean hasMoved = false;
	public Piece(String color, String position) {
		this.color = color;
		this.position = position;
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
		if(board[Chess.getColumn(dest)][Chess.getRow(dest)] instanceof King) {
			Chess.checkmate = true;
			Chess.winner = board[Chess.getColumn(pos)][Chess.getRow(pos)].color + " wins";
		}
		board[Chess.getColumn(dest)][Chess.getRow(dest)] = board[Chess.getColumn(pos)][Chess.getRow(pos)];
		board[Chess.getColumn(dest)][Chess.getRow(dest)].hasMoved = true;
		board[Chess.getColumn(pos)][Chess.getRow(pos)] = null;
		board[Chess.getColumn(dest)][Chess.getRow(dest)].position = dest;
	}
}
