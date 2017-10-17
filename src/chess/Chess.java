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
	public static String winner;
	
	public static void main(String[] args) {
		board = new Piece[8][8];
		initializeGame();
		check = false;
		checkmate = false;
		Scanner sc = new Scanner(System.in);
		while(!checkmate) {
			printBoard();
			String white = sc.nextLine();
			whiteTurn(white);
			if(check) {
				System.out.println("Check!\n");
			}
			else if(checkmate) {
				System.out.println(winner);
				break;
			}
			String black = sc.nextLine();
			blackTurn(black);
			if(check) {
				System.out.println("Check!\n");
			}
			else if(checkmate) {
				System.out.println(winner);
				break;
			}
		}
		
	}
	
	public static void whiteTurn(String move) {
		
	}
	
	public static void blackTurn(String move) {
		
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
		board[getColumn("e")][getRow("e8")] = new Queen("Black");
		board[getColumn("d")][getRow("d8")] = new King("Black");
		board[getColumn("f")][getRow("f8")] = new Bishop("Black");
		board[getColumn("g")][getRow("g8")] = new Knight("Black");
		board[getColumn("h")][getRow("h8")] = new Rook("Black");
		for(char i = 'a'; i < 'i'; i++) {
			board[getColumn("" + i)][getRow(i + "7")] = new Pawn("Black");
		}
		board[getColumn("a")][getRow("a1")] = new Rook("White");
		board[getColumn("b")][getRow("b1")] = new Knight("White");
		board[getColumn("c")][getRow("c1")] = new Bishop("White");
		board[getColumn("e")][getRow("e1")] = new Queen("White");
		board[getColumn("d")][getRow("d1")] = new King("White");
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
