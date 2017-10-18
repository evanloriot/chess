package classes;

import chess.Chess;

public class Queen extends Piece {
	public Queen(String color) {
		super(color);
	}
	public String toString() {
		return super.toString() + "Q";
	}
	//TODO
	public boolean isLegal(Piece[][] board, String command, String color) {
		if(!color.equals(this.color)) {
			System.out.println("Illegal move, try again\n");
			return false;
		}
		String position = command.substring(0,2);
		String destination = command.substring(3,5);
		if(board[Chess.getColumn(destination)][Chess.getRow(destination)] != null && board[Chess.getColumn(destination)][Chess.getRow(destination)].color.equals(color)) {
			System.out.println("Illegal move, try again\n");
			return false;
		}
		boolean legal = false;
		char posCol = position.charAt(0);
		int posRow = Integer.parseInt(position.substring(1, 2));
		char destCol = destination.charAt(0);
		int destRow = Integer.parseInt(destination.substring(1, 2));
		if(posCol == destCol && posRow == destRow) {
			System.out.println("Illegal move, try again\n");
			return false;
		}
		if(Math.abs(posCol - destCol) == Math.abs(posRow - destRow)) {
			legal = true;
		}
		else if(posRow == destRow) {
			legal = true;
		}
		else if(posCol == destCol) {
			legal = true;
		}
		else {
			System.out.println("Illegal move, try again\n");
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
			else if(destCol < posCol && destRow > posCol) {
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
			else if(posCol != destCol && posRow == destCol) {
				if(destCol > posCol) {
					for(char i = (char)(posCol + 1); i < destCol; i++) {
						if(board[Chess.getColumn("" + i + posRow)][Chess.getRow("" + i + posRow)] != null) {
							legal = false;
						}
					}
				}
				else {
					for(char i = (char)(posCol - 1); i > destCol; i--) {
						if(board[Chess.getColumn("" + i + posRow)][Chess.getRow("" + i + posRow)] != null) {
							legal = false;
						}
					}
				}
			}
			else if(posRow != destRow && posCol == destCol) {
				if(destRow > posRow) {
					for(int i = (posRow + 1); i < destRow; i++) {
						if(board[Chess.getColumn("" + posCol + i)][Chess.getRow("" + posCol + i)] != null) {
							System.out.print("false");
							legal = false;
						}
					}
				}
				else {
					for(int i = (posRow - 1); i > destRow; i--) {
						if(board[Chess.getColumn("" + posCol + i)][Chess.getRow("" + posCol + i)] != null) {
							legal = false;
						}
					}
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
			System.out.println("Illegal move, try again\n");
			return false;
		}
	}
}
