package classes;

import chess.Chess;

public class Pawn extends Piece {
	public boolean enpassant = false;
	public boolean canBePassed = false;
	
	public Pawn(String color, String position) {
		super(color, position);
	}
	public String toString() {
		return super.toString() + "p";
	}
	public boolean isLegal(Piece[][] board, String command, String color, boolean canPrint) {
		Chess.drawInitiated = false;
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
			Piece p1 = posCol > 'a' ? board[Chess.getColumn("" + (char)(destCol - 1) + destRow)][Chess.getRow(destination)] : null;
			Piece p2 = posCol < 'h' ? board[Chess.getColumn("" + (char)(destCol + 1) + destRow)][Chess.getRow(destination)] : null;
			if(p1 != null && p1 instanceof Pawn) {
				Pawn p = (Pawn) p1;
				if(!p.color.equals(this.color)) {
					canBePassed = true;
				}
			}
			else if(p2 != null && p2 instanceof Pawn) {
				Pawn p = (Pawn) p2;
				if(!p.color.equals(this.color)) {
					canBePassed = true;
				}
			}
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
			Chess.drawInitiated = true;
			return true;
		}
		else {
			if(canPrint) System.out.println("Illegal move, try again\n");
			return false;
		}
		} catch(Exception e){
			System.out.println("Bad Input\n");
			return false;
		}
	}
	public void move(Piece[][] board, String move) {
		super.move(board, move);
		if(color.equals("White") && move.charAt(4) == '8' || color.equals("Black") && move.charAt(4) == '1' && move.length() == 7) {
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
				}
			}
			
		}
		if(enpassant) {
			board[Chess.getColumn(move.substring(3,5))][Chess.getRow(move.substring(0,2))] = null;
			enpassant = false;
		}
	}
}
