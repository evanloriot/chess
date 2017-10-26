package classes;

import chess.Chess;

/**
 * The Piece class is the is the super for all other pieces. Provides the overall
 * structure for all the sub classes.  
 * @author Evan Loriot
 * @author Joseph Klaszky
 *
 */
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
	
	/**
	 * (Evan I'm not super sure what this does)
	 * Seems to just check if the king is captured, if so it ends the game. 
	 * @param board the current board
	 * @param move -- the user's current command
	 */
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
