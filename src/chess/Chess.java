package chess;

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
		String pos = move.substring(0,2);
		Piece position = board[getColumn(pos)][getRow(pos)];
		if(position instanceof Rook) {
			Rook r = (Rook) position;
			if(r.isLegal(board, move, color, true)) {
				r.move(board, move);
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
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public static boolean whiteTurn(String move) {
		return turn(move, "White");
	}
	
	public static boolean blackTurn(String move) {
		return turn(move, "Black");
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
		board[getColumn("a")][getRow("a8")] = new Rook("Black");
		board[getColumn("b")][getRow("b8")] = new Knight("Black");
		board[getColumn("c")][getRow("c8")] = new Bishop("Black");
		board[getColumn("d")][getRow("d8")] = new Queen("Black");
		board[getColumn("e")][getRow("e8")] = new King("Black");
		blackKing = (King) board[getColumn("e")][getRow("e8")];
		blackKing.position = "e8";
		board[getColumn("f")][getRow("f8")] = new Bishop("Black");
		board[getColumn("g")][getRow("g8")] = new Knight("Black");
		board[getColumn("h")][getRow("h8")] = new Rook("Black");
		for(char i = 'a'; i < 'i'; i++) {
			board[getColumn("" + i)][getRow(i + "7")] = new Pawn("Black");
		}
		board[getColumn("a")][getRow("a1")] = new Rook("White");
		board[getColumn("b")][getRow("b1")] = new Knight("White");
		board[getColumn("c")][getRow("c1")] = new Bishop("White");
		board[getColumn("d")][getRow("d1")] = new Queen("White");
		board[getColumn("e")][getRow("e1")] = new King("White");
		whiteKing = (King) board[getColumn("e")][getRow("e1")];
		whiteKing.position = "e1";
		board[getColumn("f")][getRow("f1")] = new Bishop("White");
		board[getColumn("g")][getRow("g1")] = new Knight("White");
		board[getColumn("h")][getRow("h1")] = new Rook("White");
		for(char i = 'a'; i < 'i'; i++) {
			board[getColumn("" + i)][getRow(i + "2")] = new Pawn("White");
		}
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
