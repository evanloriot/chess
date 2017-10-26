package classes;

import chess.Chess;

public class Bishop extends Piece {
	public Bishop(String color, String position) {
		super(color, position);
	}
	public String toString() {
		return super.toString() + "B";
	}
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
			int posRow = Integer.parseInt(position.substring(1, 2));
			char destCol = destination.charAt(0);
			int destRow = Integer.parseInt(destination.substring(1, 2));
			if(posCol == destCol && posRow == destRow) {
				if(canPrint) System.out.println("Illegal move, try again\n");
				return false;
			}
			if(Math.abs(posCol - destCol) == Math.abs(posRow - destRow)) {
				legal = true;
			}
			else {
				if(canPrint) System.out.println("Illegal move, try again\n");
				return false;
			}
			if(legal) {
				if(destCol > posCol && destRow > posRow) {
					int row = posRow + 1;
					char col = (char) (posCol + 1);
					while(row < destRow && col < destCol) {
						if(board[Chess.getColumn("" + col + row)][Chess.getRow("" + col + row)] != null) {
							legal = false;
						}
						row++;
						col++;
					}
				}
				else if(destCol > posCol && destRow < posRow) {
					int row = posRow - 1;
					char col = (char) (posCol + 1);
					while(row > destRow && col < destCol) {
						if(board[Chess.getColumn("" + col + row)][Chess.getRow("" + col + row)] != null) {
							legal = false;
						}
						row--;
						col++;
					}
				}
				else if(destCol < posCol && destRow < posRow) {
					int row = posRow - 1;
					char col = (char) (posCol - 1);
					while(row > destRow && col > destCol) {
						if(board[Chess.getColumn("" + col + row)][Chess.getRow("" + col + row)] != null) {
							legal = false;
						}
						row--;
						col--;
					}
				}
				else {
					int row = posRow + 1;
					char col = (char) (posCol - 1);
					while(row < destRow && col > destCol) {
						if(board[Chess.getColumn("" + col + row)][Chess.getRow("" + col + row)] != null) {
							legal = false;
						}
						row++;
						col--;
					}
				}
			}
			if(legal && command.length() == 11 && command.substring(6).equals("draw?")) {
				Chess.drawInitiated = true;
			}
			if(legal) {
				return true;
			}
			else {
				if(canPrint) System.out.println("Illegal move, try again\n");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Bad Input\n");
			return false;
		}
	}
}
