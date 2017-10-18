package classes;

import chess.Chess;

public class Knight extends Piece {
	public Knight(String color) {
		super(color);
	}
	public String toString() {
		return super.toString() + "N";
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
		boolean legal;
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
			System.out.println("Illegal move, try again\n");
			return false;
		}
		if(legal && command.length() == 11 && command.substring(6).equalsIgnoreCase("draw?")) {
			Chess.drawInitiated = true;
		}
		return legal;
	}
	public void move(Piece[][] board, String move) {
		String pos = move.substring(0,2);
		String dest = move.substring(3,5);
		board[Chess.getColumn(dest)][Chess.getRow(dest)] = board[Chess.getColumn(pos)][Chess.getRow(pos)];
		board[Chess.getColumn(pos)][Chess.getRow(pos)] = null;
	}
}
