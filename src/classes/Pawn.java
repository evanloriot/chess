package classes;

import chess.Chess;
/**
 * The Pawn class represents the pawn piece. It can only move directly forward and attack
 * square adjacent diagonals directly in front of it. It can be promoted to another piece if 
 * it reaches the last row opposite of its starting position.
 * @author Evan Loriot
 * @author Joseph Klaszky
 */
public class Pawn extends Piece {
	/**
	 * variable designating if a pawn is performing an en passant move
	 */
	public boolean enpassant = false;
	
	/**
	 * variable designating if a pawn can be passed en passant
	 */
	public boolean canBePassed = false;
	
	/**
	 * Constructor
	 * @param color  color of piece to be initialized
	 * @param position  position of piece on board to be initialized
	 */
	public Pawn(String color, String position) {
		super(color, position);
	}
	
	/**
	 * Method to return string representation of piece.
	 * @return String representation of piece
	 */
	public String toString() {
		return super.toString() + "p";
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
		boolean canBePassed = false;
		if(!color.equals(this.color)) {
			if(canPrint) System.out.println("Illegal move, try again\n");
			return false;
		}
		try{
			String position = command.substring(0,2);
			String destination = command.substring(3,5);
			if(board[Chess.getColumn(destination)][Chess.getRow(destination)] != null && board[Chess.getColumn(destination)][Chess.getRow(destination)].color.equals(color)) {
				if(canPrint) System.out.println("Illegal move, try again\n");
				return false;
			}

		boolean legal = false;
		char posCol = position.charAt(0);
		int posRow = Integer.parseInt(position.substring(1, 2));
		char destCol = destination.charAt(0);
		int destRow = Integer.parseInt(destination.substring(1, 2));
		if(posCol == destCol && posRow == destRow) {
			if(canPrint) System.out.println("Illegal move, try again\n");
			return false;
		}		
		if(!hasMoved && destCol == posCol && Math.abs(destRow - posRow) == 2) {
			legal = true;
			canBePassed = true;
		}
		else if(destCol != posCol && Math.abs(destCol - posCol) == 1) {
			if(board[Chess.getColumn(destination)][Chess.getRow(position)] != null && board[Chess.getColumn(destination)][Chess.getRow(position)] instanceof Pawn) {
				Pawn p = (Pawn)board[Chess.getColumn(destination)][Chess.getRow(position)];
				if(p.canBePassed && !p.color.equals(this.color)) {
					if((this.color.equals("White") && destRow > posRow) || (this.color.equals("Black") && destRow < posRow)) {
						enpassant = true;
						legal = true;
					}
				}
			}
			if(board[Chess.getColumn(destination)][Chess.getRow(destination)] != null && !board[Chess.getColumn(destination)][Chess.getRow(destination)].color.equals(this.color)) {
				if((this.color.equals("White") && destRow > posRow && Math.abs(destRow - posRow) <= 1) || (this.color.equals("Black") && destRow < posRow && Math.abs(destRow - posRow) <= 1)) {
					legal = true;
				}
			}
		}
		else if(destCol == posCol && (this.color.equals("White") && destRow > posRow && Math.abs(destRow - posRow) <= 1) || (this.color.equals("Black") && destRow < posRow && Math.abs(destRow - posRow) <= 1)) {
			if((this.color.equals("White") && destRow > posRow && Math.abs(destRow - posRow) <= 1) || (this.color.equals("Black") && destRow < posRow && Math.abs(destRow - posRow) <= 1)) {
				if(board[Chess.getColumn(destination)][Chess.getRow(destination)] != null && !board[Chess.getColumn(destination)][Chess.getRow(destination)].color.equals(this.color)){
					legal = false;
				} else {
					legal = true;
				}
			}
		}
		if(legal) {
			Chess.clearEnPassant(color);
			if(canBePassed) {
				this.canBePassed = true;
			}
			if(legal && command.length() == 11 && command.substring(6).equals("draw?")) {
				Chess.drawInitiated = true;
			}
			return true;
		}
		else {
			if(canPrint) System.out.println("Illegal move, try again\n");
			return false;
		}
		} catch(IndexOutOfBoundsException e){
			System.out.println("Bad Input\n");
			return false;
		}
	}
	
	/**
	 * Performs superclass move and performs promotion or en passant if applicable
	 * @param board  Piece array of current chessboard
	 * @param move  move input by player
	 */
	public void move(Piece[][] board, String move) {
		super.move(board, move);
		if(color.equals("White") && move.charAt(4) == '8' || color.equals("Black") && move.charAt(4) == '1') {
			String position = move.substring(3,5);
			String color = board[Chess.getColumn(position)][Chess.getRow(position)].color;
			if(move.length() == 7) {
				char promotion = move.charAt(6);
				switch(promotion) {
					case 'R':
						board[Chess.getColumn(position)][Chess.getRow(position)] = new Rook(color, position);
					case 'N':
						board[Chess.getColumn(position)][Chess.getRow(position)] = new Knight(color, position);
					case 'B':
						board[Chess.getColumn(position)][Chess.getRow(position)] = new Bishop(color, position);
					case 'Q':
						board[Chess.getColumn(position)][Chess.getRow(position)] = new Queen(color, position);
					default:
						board[Chess.getColumn(position)][Chess.getRow(position)] = new Queen(color, position);
				} 
			} else {
				board[Chess.getColumn(position)][Chess.getRow(position)] = new Queen(color, position);
			}
			
		}
		if(enpassant) {
			board[Chess.getColumn(move.substring(3,5))][Chess.getRow(move.substring(0,2))] = null;
			enpassant = false;
		}
	}
}
