package classes;

import chess.Chess;

/**
 * The King class represents the king piece. It can move/attack in space in any directly adjacent space.
 * @author Evan Loriot
 * @author Joseph Klaszky
 */
public class King extends Piece {
	/**
	 * Constructor
	 * @param color  color of piece to be initialized
	 * @param position  position on board of piece to be initialized
	 */
	public King(String color, String position) {
		super(color, position);
	}
	
	/**
	 * Method to return String representation of Piece.
	 * @return String -- string representation of Piece
	 */
	public String toString() {
		return super.toString() + "K";
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
		if(!color.equals(this.color)) {
			if(canPrint) System.out.println("Illegal move, try again\n");
			return false;
		}
		try {
			String position = command.substring(0,2);
			String destination = command.substring(3,5);
			boolean castling = false;
			if(position.equals("e1") && destination.equals("g1")) {
				if(!hasMoved && board[Chess.getColumn("h1")][Chess.getRow("h1")] != null && !board[Chess.getColumn("h1")][Chess.getRow("h1")].hasMoved) {
					if(board[Chess.getColumn("f1")][Chess.getRow("f1")] == null && board[Chess.getColumn("g1")][Chess.getRow("g1")] == null) {
						castling = true;
					}
				}
				else {
					if(canPrint) System.out.println("Illegal move, try again\n");
					return false;
				}
			}
			else if(position.equals("e1") && destination.equals("c1")) {
				if(!hasMoved && board[Chess.getColumn("a1")][Chess.getRow("a1")] != null && !board[Chess.getColumn("a1")][Chess.getRow("a1")].hasMoved) {
					if(board[Chess.getColumn("b1")][Chess.getRow("b1")] == null && board[Chess.getColumn("c1")][Chess.getRow("c1")] == null && board[Chess.getColumn("d1")][Chess.getRow("d1")] == null) {
						castling = true;
					}
				}
				else {
					if(canPrint) System.out.println("Illegal move, try again\n");
					return false;
				}
			}
			else if(position.equals("e8") && destination.equals("g8")) {
				if(!hasMoved && board[Chess.getColumn("h8")][Chess.getRow("h8")] != null && !board[Chess.getColumn("h8")][Chess.getRow("h8")].hasMoved) {
					if(board[Chess.getColumn("f8")][Chess.getRow("f8")] == null && board[Chess.getColumn("g8")][Chess.getRow("g8")] == null) {
						castling = true;
					}
				}
				else {
					if(canPrint) System.out.println("Illegal move, try again\n");
					return false;
				}
			}
			else if(position.equals("e8") && destination.equals("c8")) {
				if(!hasMoved && board[Chess.getColumn("a8")][Chess.getRow("a8")] != null && !board[Chess.getColumn("a8")][Chess.getRow("a8")].hasMoved) {
					if(board[Chess.getColumn("b8")][Chess.getRow("b8")] == null && board[Chess.getColumn("c8")][Chess.getRow("c8")] == null && board[Chess.getColumn("d8")][Chess.getRow("d8")] == null) {
						castling = true;
					}
				}
				else {
					if(canPrint) System.out.println("Illegal move, try again\n");
					return false;
				}
			}
			else if(board[Chess.getColumn(destination)][Chess.getRow(destination)] != null && board[Chess.getColumn(destination)][Chess.getRow(destination)].color.equals(color)) {
				if(canPrint) System.out.println("Illegal move, try again\n");
				return false;
			}
			char posCol = position.charAt(0);
			int posRow = Integer.parseInt(position.substring(1, 2));
			char destCol = destination.charAt(0);
			int destRow = Integer.parseInt(destination.substring(1, 2));
			if(posCol == destCol && posRow == destRow) {
				if(canPrint) System.out.println("Illegal move, try again\n");
				return false;
			}
			if(castling) {
				if(command.length() == 11 && command.substring(6).equals("draw?")) {
					Chess.drawInitiated = true;
				}
				return true;
			}
			else {
				if(Math.abs(posRow - destRow) == 1 && Math.abs(posCol - destCol) == 1) {
					return true;
				}
				else if(Math.abs(posRow - destRow) == 1 && Math.abs(posCol - destCol) == 0) {
					return true;
				}
				else if(Math.abs(posRow - destRow) == 0 && Math.abs(posCol - destCol) == 1) {
					return true;
				}
				else {
					if(canPrint) System.out.println("Illegal move, try again\n");
					return false;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Bad Intput\n");
			return false;
		}
	}
	
	/**
	 * Performs superclass move and castling if applicable
	 * @param board  current setup of chess board
	 * @param move  move to be done
	 */
	public void move(Piece[][] board, String move) {
		super.move(board, move);
		String position = move.substring(0,2);
		String destination = move.substring(3,5);
		if(position.equals("e1") && destination.equals("g1")) {
			super.move(board, "h1 f1");
		}
		else if(position.equals("e1") && destination.equals("c1")) {
			super.move(board, "a1 d1");
		}
		else if(position.equals("e8") && destination.equals("g8")) {
			super.move(board, "h8 f8");
		}
		else if(position.equals("e8") && destination.equals("c8")) {
			super.move(board, "a8 d8");
		}
	}
}
