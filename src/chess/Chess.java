package chess;

import java.util.ArrayList;
import java.util.Scanner;

import classes.Bishop;
import classes.King;
import classes.Knight;
import classes.Pawn;
import classes.Piece;
import classes.Queen;
import classes.Rook;

public class Chess {
	public static Piece[][] board;
	public static boolean check;
	public static boolean checkmate;
	public static boolean drawInitiated;
	public static boolean draw;
	public static boolean resign;
	public static String winner;
	public static King blackKing;
	public static King whiteKing;
	
	public static void main(String[] args) {
		board = new Piece[8][8];
		initializeGame();
		check = false;
		checkmate = false;
		draw = false;
		drawInitiated = false;
		resign = false;
		Scanner sc = new Scanner(System.in);
		while(!checkmate) {
			printBoard();
			boolean valid = false;
			while(!valid) {
				System.out.print("White's move: ");
				String white = sc.nextLine();
				System.out.println("");
				valid = whiteTurn(white);	
			}
			if(resign) {
				winner = "Black wins";
				System.out.println(winner);
				break;
			}
			else if(checkmate) {
				if(isInCheckmate("White")) {
					winner = "Black wins";
				}
				else {
					winner = "White wins";
				}
				System.out.println(winner);
				break;
			}
			else if(check) {
				System.out.println("Check\n");
			}
			else if(draw) {
				break;
			}
			printBoard();
			valid = false;
			while(!valid) {
				System.out.print("Black's move: ");
				String black = sc.nextLine();
				System.out.println("");
				valid = blackTurn(black);	
			}
			if(resign) {
				winner = "White wins";
				System.out.println(winner);
				break;
			}
			else if(check) {
				System.out.println("Check\n");
			}
			else if(checkmate) {
				System.out.println(winner);
				break;
			}
			else if(draw) {
				break;
			}
		}
	}
	
	public static boolean turn(String move, String color) {
		if(move.equals("resign")) {
			resign = true;
			return true;
		}
		else if(move.equals("draw")) {
			if(drawInitiated) {
				draw = true;
				return true;
			}
		}
		try {
			String pos = move.substring(0,2);
			Piece position = board[getColumn(pos)][getRow(pos)];
			if(position instanceof Rook) {
				Rook r = (Rook) position;
				if(r.isLegal(board, move, color, true)) {
					r.move(board, move);
					String oppColor = color.equals("White") ? "Black" : "White";
					if(isInCheck(board, oppColor) || isInCheck(board, color)) {
						check = true;
					}
					if(isInCheckmate("White") || isInCheckmate("Black")) {
						checkmate = true;
					}
					return true;
				}
				else {
					return false;
				}
			}
			else if(position instanceof Knight) {
				Knight r = (Knight) position;
				if(r.isLegal(board, move, color, true)) {
					r.move(board, move);
					String oppColor = color.equals("White") ? "Black" : "White";
					if(isInCheck(board, oppColor) || isInCheck(board, color)) {
						check = true;
					}
					if(isInCheckmate("White") || isInCheckmate("Black")) {
						checkmate = true;
					}
					return true;
				}
				else {
					return false;
				}
			}
			else if(position instanceof Bishop) {
				Bishop r = (Bishop) position;
				if(r.isLegal(board, move, color, true)) {
					r.move(board, move);
					String oppColor = color.equals("White") ? "Black" : "White";
					if(isInCheck(board, oppColor) || isInCheck(board, color)) {
						check = true;
					}
					if(isInCheckmate("White") || isInCheckmate("Black")) {
						checkmate = true;
					}
					return true;
				}
				else {
					return false;
				}
			}
			else if(position instanceof Queen) {
				Queen r = (Queen) position;
				if(r.isLegal(board, move, color, true)) {
					r.move(board, move);
					String oppColor = color.equals("White") ? "Black" : "White";
					if(isInCheck(board, oppColor) || isInCheck(board, color)) {
						check = true;
					}
					if(isInCheckmate("White") || isInCheckmate("Black")) {
						checkmate = true;
					}
					return true;
				}
				else {
					return false;
				}
			}
			else if(position instanceof King) {
				King r = (King) position;
				if(r.isLegal(board, move, color, true)) {
					r.move(board, move);
					String oppColor = color.equals("White") ? "Black" : "White";
					if(isInCheck(board, oppColor) || isInCheck(board, color)) {
						check = true;
					}
					if(isInCheckmate("White") || isInCheckmate("Black")) {
						checkmate = true;
					}
					return true;
				}
				else {
					return false;
				}
			}
			else if(position instanceof Pawn) {
				Pawn r = (Pawn) position;
				if(r.isLegal(board, move, color, true)) {
					r.move(board, move);
					String oppColor = color.equals("White") ? "Black" : "White";
					if(isInCheck(board, oppColor) || isInCheck(board, color)) {
						check = true;
					}
					if(isInCheckmate("White") || isInCheckmate("Black")) {
						checkmate = true;
					}
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Bad Input\n");
			return false;
		}
	}
	
	public static boolean whiteTurn(String move) {
		return turn(move, "White");
	}
	
	public static boolean blackTurn(String move) {
		return turn(move, "Black");
	}
	
	public static ArrayList<Piece> getPieces(String color){
		ArrayList<Piece> output = new ArrayList<Piece>();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] != null && board[i][j].color.equals(color)) {
					output.add(board[i][j]);
				}
			}
		}
		return output;
	}
	
	public static boolean isInCheck(Piece[][] board, String color) {
		String oppColor = color.equals("White") ? "Black" : "White";
		ArrayList<Piece> pieces = getPieces(oppColor);
		King k = color.equals("White") ? whiteKing : blackKing;
		for(int i = 0; i < pieces.size(); i++) {
			Piece p = pieces.get(i);
			if(p instanceof Rook) {
				Rook r = (Rook) p;
				String winMove = r.position + " " + k.position;
				if(r.isLegal(board, winMove, oppColor, false)) {
					return true;
				}
			}
			else if(p instanceof Knight) {
				Knight r = (Knight) p;
				String winMove = r.position + " " + k.position;
				if(r.isLegal(board, winMove, oppColor, false)) {
					return true;
				}
			}
			else if(p instanceof King) {
				King r = (King) p;
				String winMove = r.position + " " + k.position;
				if(r.isLegal(board, winMove, oppColor, false)) {
					return true;
				}
			}
			else if(p instanceof Bishop) {
				Bishop r = (Bishop) p;
				String winMove = r.position + " " + k.position;
				if(r.isLegal(board, winMove, oppColor, false)) {
					return true;
				}
			}
			else if(p instanceof Queen) {
				Queen r = (Queen) p;
				String winMove = r.position + " " + k.position;
				if(r.isLegal(board, winMove, oppColor, false)) {
					return true;
				}
			}
			else if(p instanceof Pawn) {
				Pawn r = (Pawn) p;
				String winMove = r.position + " " + k.position;
				if(r.isLegal(board, winMove, oppColor, false)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isInCheckmate(String color) {
		if(!isInCheck(board, color)) {
			return false;
		}
		ArrayList<Piece> pieces = getPieces(color);
		for(int i = 0; i < pieces.size(); i++) {
			Piece p = pieces.get(i);
			if(p instanceof Rook) {
				Rook x = (Rook) p;
				for(char col = 'a'; i <= 'h'; i++) {
					for(int row = 1; row <= 8; row++) {
						String move = p.position + " " + col + row;
						if(x.isLegal(board, move, color, false)) {
							Piece[][] possibleBoard = duplicateBoard();
							String pos = move.substring(0, 2);
							Rook y = (Rook) possibleBoard[getColumn(pos)][getRow(pos)];
							y.move(possibleBoard, move);
							if(!isInCheck(possibleBoard, color)) {
								return false;
							}
						}
					}
				}
			}
			else if(p instanceof Knight) {
				Knight x = (Knight) p;
				for(char col = 'a'; i <= 'h'; i++) {
					for(int row = 1; row <= 8; row++) {
						String move = p.position + " " + col + row;
						if(x.isLegal(board, move, color, false)) {
							Piece[][] possibleBoard = duplicateBoard();
							String pos = move.substring(0, 2);
							Knight y = (Knight) possibleBoard[getColumn(pos)][getRow(pos)];
							y.move(possibleBoard, move);
							if(!isInCheck(possibleBoard, color)) {
								return false;
							}
						}
					}
				}
			}
			else if(p instanceof Bishop) {
				Bishop x = (Bishop) p;
				for(char col = 'a'; i <= 'h'; i++) {
					for(int row = 1; row <= 8; row++) {
						String move = p.position + " " + col + row;
						if(x.isLegal(board, move, color, false)) {
							Piece[][] possibleBoard = duplicateBoard();
							String pos = move.substring(0, 2);
							Bishop y = (Bishop) possibleBoard[getColumn(pos)][getRow(pos)];
							y.move(possibleBoard, move);
							if(!isInCheck(possibleBoard, color)) {
								return false;
							}
						}
					}
				}
			}
			else if(p instanceof Queen) {
				Queen x = (Queen) p;
				for(char col = 'a'; i <= 'h'; i++) {
					for(int row = 1; row <= 8; row++) {
						String move = p.position + " " + col + row;
						if(x.isLegal(board, move, color, false)) {
							Piece[][] possibleBoard = duplicateBoard();
							String pos = move.substring(0, 2);
							Queen y = (Queen) possibleBoard[getColumn(pos)][getRow(pos)];
							y.move(possibleBoard, move);
							if(!isInCheck(possibleBoard, color)) {
								return false;
							}
						}
					}
				}
			}
			else if(p instanceof King) {
				King x = (King) p;
				for(char col = 'a'; i <= 'h'; i++) {
					for(int row = 1; row <= 8; row++) {
						String move = p.position + " " + col + row;
						if(x.isLegal(board, move, color, false)) {
							Piece[][] possibleBoard = duplicateBoard();
							String pos = move.substring(0, 2);
							King y = (King) possibleBoard[getColumn(pos)][getRow(pos)];
							y.move(possibleBoard, move);
							if(!isInCheck(possibleBoard, color)) {
								return false;
							}
						}
					}
				}
			}
			else if(p instanceof Pawn) {
				Pawn x = (Pawn) p;
				for(char col = 'a'; i <= 'h'; i++) {
					for(int row = 1; row <= 8; row++) {
						String move = p.position + " " + col + row;
						if(x.isLegal(board, move, color, false)) {
							Piece[][] possibleBoard = duplicateBoard();
							String pos = move.substring(0, 2);
							Pawn y = (Pawn) possibleBoard[getColumn(pos)][getRow(pos)];
							y.move(possibleBoard, move);
							if(!isInCheck(possibleBoard, color)) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	public static Piece[][] duplicateBoard(){
		Piece[][] output = new Piece[8][8];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				Piece p = board[i][j];
				if(p instanceof Rook) {
					output[i][j] = new Rook(p.color, p.position);
				}
				else if(p instanceof Knight) {
					output[i][j] = new Knight(p.color, p.position);
				}
				else if(p instanceof Bishop) {
					output[i][j] = new Bishop(p.color, p.position);
				}
				else if(p instanceof Queen) {
					output[i][j] = new Queen(p.color, p.position);
				}
				else if(p instanceof King) {
					output[i][j] = new King(p.color, p.position);
				}
				else if(p instanceof Pawn) {
					output[i][j] = new Pawn(p.color, p.position);
				}
			}
		}
		return output;
	}
	
	public static void printBoard() {
		for(int i = 8; i >= 1; i--) {
			for(char j = 'a'; j < 'i'; j++) {
				String position = "" + j + i;
				Piece piece = board[getColumn(position)][getRow(position)];
				if(piece != null) {
					System.out.print(piece);
				}
				else {
					if((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
						System.out.print("  ");
					}
					else{
						System.out.print("##");
					}
				}
				System.out.print(" ");
			}
			System.out.println(i);
		}
		for(char i = 'a'; i < 'i'; i++) {
			System.out.print(" " + i + " ");
		}
		System.out.println("\n");
	}
	
	public static void initializeGame() {
		board[getColumn("a")][getRow("a8")] = new Rook("Black", "a8");
		board[getColumn("b")][getRow("b8")] = new Knight("Black", "b8");
		board[getColumn("c")][getRow("c8")] = new Bishop("Black", "c8");
		board[getColumn("d")][getRow("d8")] = new Queen("Black", "d8");
		board[getColumn("e")][getRow("e8")] = new King("Black", "e8");
		blackKing = (King) board[getColumn("e")][getRow("e8")];
		board[getColumn("f")][getRow("f8")] = new Bishop("Black", "f8");
		board[getColumn("g")][getRow("g8")] = new Knight("Black", "g8");
		board[getColumn("h")][getRow("h8")] = new Rook("Black", "h8");
		for(char i = 'a'; i < 'i'; i++) {
			board[getColumn("" + i)][getRow(i + "7")] = new Pawn("Black", i + "7");
		}
		board[getColumn("a")][getRow("a1")] = new Rook("White", "a1");
		board[getColumn("b")][getRow("b1")] = new Knight("White", "b1");
		board[getColumn("c")][getRow("c1")] = new Bishop("White", "c1");
		board[getColumn("d")][getRow("d1")] = new Queen("White", "d1");
		board[getColumn("e")][getRow("e1")] = new King("White", "e1");
		whiteKing = (King) board[getColumn("e")][getRow("e1")];
		board[getColumn("f")][getRow("f1")] = new Bishop("White", "f1");
		board[getColumn("g")][getRow("g1")] = new Knight("White", "g1");
		board[getColumn("h")][getRow("h1")] = new Rook("White", "h1");
		for(char i = 'a'; i < 'i'; i++) {
			board[getColumn("" + i)][getRow(i + "2")] = new Pawn("White", i + "2");
		}
//		board[getColumn("e")][getRow("e2")] = new Queen("Black", "e2");
//		board[getColumn("f")][getRow("f3")] = new King("Black", "f3");
//		board[getColumn("d")][getRow("d1")] = new King("White", "d1");
//		blackKing = (King) board[getColumn("f")][getRow("f3")];
//		whiteKing = (King) board[getColumn("d")][getRow("d1")];
	}
	
	public static int getColumn(String position) {
		char col = position.charAt(0);
		switch(col) {
			case 'a':
				return 0;
			case 'b':
				return 1;
			case 'c':
				return 2;
			case 'd':
				return 3;
			case 'e':
				return 4;
			case 'f':
				return 5;
			case 'g':
				return 6;
			case 'h':
				return 7;
		}
		return -1;
	}
	
	public static int getRow(String position) {
		return Integer.parseInt(position.substring(1)) - 1;
	}
}
