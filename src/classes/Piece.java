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
	/**
	 * color of the piece
	 */
	public String color;
	
	/**
	 * position of the piece on the chessboard
	 */
	public String position;
	
	/**
	 * variable true if piece has moved this game, false otherwise 
	 */
	public boolean hasMoved = false;
	
	/**
	 * Constructor
	 * @param color  color of the piece to be initialized
	 * @param position  position of the piece to be initialized
	 */
	public Piece(String color, String position) {
		this.color = color;
		this.position = position;
	}
	
	/**
	 * Method to return a string representation of the piece
	 * @return string representation of piece
	 */
	public String toString() {
		if(color.equals("Black")) {
			return "b";
		}
		else {
			return "w";
		}
	}
	
	/**
	 * Performs piece movement, updates piece properties, performs captures, and sets winner if King is taken
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
