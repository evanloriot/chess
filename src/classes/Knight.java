package classes;

import chess.Chess;

/**
 * The Knight class represents the knight piece. It must move in an L shape
 * -- two squares vertically and one square horizontally, or two squares horizontally and one square vertically
 * @author Evan Loriot
 * @author Joseph Klaszky
 */
public class Knight extends Piece {
	/**
	 * Constructor
	 * @param color  color of piece to be initialized
	 * @param position  position of piece to be initialized on board
	 */
	public Knight(String color, String position) {
		super(color, position);
	}
	
	/**
	 * Method to return a string representation of piece
	 * @return string representation of piece
	 */
	public String toString() {
		return super.toString() + "N";
	}
	
	/**
	 * This method takes a look at the current board and the user's move to see if 
	 * this would be a legal move.
	 * @param board the current set up of the game board
	 * @param command gotten from user input
	 * @param color color of the current user's pieces
	 * @param canPrint true if illegal moves are allowed to be printed
	 * @return true if the entered move is legal, false otherwise
	 * @exception IndexOutOfBoundsException -- if the user enters badly formatted input.
	 * @see IndexOutOfBoundsException
	 */
	public boolean isLegal(Piece[][] board, String command, String color, boolean canPrint) {
		Chess.drawInitiated = false;
		if(!color.equals(this.color)) {
			if(canPrint) System.out.println("Illegal move, try again\n");
			return false;
		}
		try {
			String position = command.substring(0,2);
			String destination = command.substring(3,5);
			if(board[Chess.getColumn(destination)][Chess.getRow(destination)] != null && board[Chess.getColumn(destination)][Chess.getRow(destination)].color.equals(color)) {
				if(canPrint) System.out.println("Illegal move, try again\n");
				return false;
			}
			boolean legal = false;
			char posCol = position.charAt(0);
			int posRow = Chess.getRow(position);
			char destCol = destination.charAt(0);
			int destRow = Chess.getRow(destination);
			if(Math.abs(destCol - posCol) == 2 && Math.abs(destRow - posRow) == 1) {
				legal = true;
			}
			else if(Math.abs(destCol - posCol) == 1 && Math.abs(destRow - posRow) == 2) {
				legal =  true;
			}
			else {
				if(canPrint) System.out.println("Illegal move, try again\n");
				return false;
			}
			if(legal && command.length() == 11 && command.substring(6).equals("draw?")) {
				Chess.drawInitiated = true;
			}
			return legal;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Bad Input\n");
			return false;
		}
	}
}
