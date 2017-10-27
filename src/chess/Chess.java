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

/**
 * The chess class is runs the main loop for the game
 * and does the book keeping for who's turn it is, 
 * where each of the pieces are, prints out the board when
 * needed and whether the king is in check or checkmate.  
 * @author Evan Loriot
 * @author Joseph Klaszky
 */
public class Chess {
	/**
	 * Piece array of the current official board for the game
	 */
	public static Piece[][] board;
	
	/**
	 * true if either king is in check, false otherwise
	 */
	public static boolean check;
	
	/**
	 * variable true when either king is in checkmate, false otherwise
	 */
	public static boolean checkmate;
	
	/**
	 * variable true if one player initiates a draw, false otherwise
	 */
	public static boolean drawInitiated;
	
	/**
	 * variable true if player has confirmed a draw initiation, false otherwise
	 */
	public static boolean draw;
	
	/**
	 * variable true if either player has resigned
	 */
	public static boolean resign;
	
	/**
	 * variable containing the winning text to output at the end of the game, either: Black wins or White wins
	 */
	public static String winner;
	
	/**
	 * pointer to black king
	 */
	public static King blackKing;
	
	/**
	 * pointer to white king
	 */
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
				System.out.println("Checkmate");
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
			else if(checkmate) {
				if(isInCheckmate("White")) {
					winner = "Black wins";
				}
				else {
					winner = "White wins";
				}
				System.out.println("Checkmate");
				System.out.println(winner);
				break;
			}
			else if(check) {
				System.out.println("Check\n");
			}
			else if(draw) {
				break;
			}
		}
	}
	
	/**
	 * This method ensures you the user can only perform EnPassant when it's legal.
	 * @param color used so that only one color's flags are flipped
	 */
	public static void clearEnPassant(String color) {
		ArrayList<Piece> pieces = getPieces(color);
		for(int i = 0; i < pieces.size(); i++) {
			Piece p = pieces.get(i);
			if(p instanceof Pawn) {
				((Pawn) p).canBePassed = false;
			}
		}
	}
	
	/**
	 * Does book keeping for each turn and tells the main method if it can continue to the
	 * next turn or if it needs to re-do current turn.
	 * @param move user input for the move they would like to make
	 * @param color current color
	 * @return boolean -- true if the input is valid and main can continue to the next turn,
	 * false otherwise
	 * @exception IndexOutOfBoundsException -- if the user enters badly formatted input.
	 * @see IndexOutOfBoundsException
	 */
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
			if(position == null) {
				System.out.println("Illegal move, try again\n");
				return false;
			}
			if(position instanceof Rook) {
				Rook r = (Rook) position;
				if(r.isLegal(board, move, color, true)) {
					clearEnPassant(color);
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
					clearEnPassant(color);
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
					clearEnPassant(color);
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
					clearEnPassant(color);
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
					clearEnPassant(color);
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
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Bad Input\n");
			return false;
		}
	}
	
	/**
	 * Calls turn() method with proper color.
	 * @param move user input for the move they would like to make
	 * @return boolean -- true if the input is valid and main can continue to the next turn,
	 * false otherwise
	 */
	public static boolean whiteTurn(String move) {
		return turn(move, "White");
	}
	
	/**
	 * Calls turn() method with proper color.
	 * @param move user input for the move they would like to make
	 * @return boolean -- true if the input is valid and main can continue to the next turn,
	 * false otherwise
	 */
	public static boolean blackTurn(String move) {
		return turn(move, "Black");
	}
	
	/**
	 * Get's an arrayList of all remaining pieces left on
	 * the board of a certain color.
	 * @param color used in a check to ensure only that the method is only 
	 * grabbing pieces of the correct color
	 * @return ArrayList(Piece) -- list of all pieces of a certain color
	 */
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
	
	/**
	 * A method that returns true if the a king is currently under attack by a piece of a different color by checking all the opposing pieces
	 * ability to take the targeted king
	 * @param board A 2-D array that represents the board
	 * @param color  the color of the king to check whether it is in check
	 * @return boolean -- true if the king of the color is currently under attack, false otherwise
	 */
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
	
	/**
	 * A method that returns true if the a king is currently checkmated by the opposite player, 
	 * that is the king is under attack and no move by any piece can take the king to safty
	 * @param color  the color of the king to check for checkmate on
	 * @return boolean -- true if the king is checkmated, false otherwise
	 */
	public static boolean isInCheckmate(String color) {
		if(!isInCheck(board, color)) {
			return false;
		}
		ArrayList<Piece> pieces = getPieces(color);
		for(int i = 0; i < pieces.size(); i++) {
			Piece p = pieces.get(i);
			if(p instanceof Rook) {
				Rook x = (Rook) p;
				for(char col = 'a'; col <= 'h'; col++) {
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
				for(char col = 'a'; col <= 'h'; col++) {
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
				for(char col = 'a'; col <= 'h'; col++) {
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
				for(char col = 'a'; col <= 'h'; col++) {
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
				for(char col = 'a'; col <= 'h'; col++) {
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
				for(char col = 'a'; col <= 'h'; col++) {
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
	
	/**
	 * Makes a copy of the current board, used for
	 * checking for possible future configuration to find checkmate
	 * @return A 2-D array representing a board
	 */
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
	
	/**
	 * Iterates over the entire board and prints to stdout either the 
	 * piece at a give square or the color of the square
	 */
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
	
	/**
	 * Setups up the initial board for the start of the game.
	 */
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
	}
	
	/**
	 * Takes the user input for the color and turns it into an int
	 * for use for moves and such.
	 * @param position gotten from user input
	 * @return an int representing the column entered by the user
	 */
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
	
	/**
	 * Gets the proper row by taking user input and reducing by 1
	 * @param position gotten from used input
	 * @return an int representing the column entered by the user
	 */
	public static int getRow(String position) {
		return Integer.parseInt(position.substring(1)) - 1;
	}
}
