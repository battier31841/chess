package gmfb.chess.core.printer;

import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.ChessPieceKey;
import gmfb.chess.core.piece.pieces.BishopPiece;
import gmfb.chess.core.piece.pieces.KingPiece;
import gmfb.chess.core.piece.pieces.KnightPiece;
import gmfb.chess.core.piece.pieces.QueenPiece;
import gmfb.chess.core.piece.pieces.RookPiece;

import java.io.PrintStream;
import java.util.Arrays;

public class BoardPrinterImpl implements BoardPrinter
{
	public void printBoard(ChessBoard chessBoard, PrintStream printStream)
	{
		String[][] layout = buildLayoutMatrix(chessBoard);

		printTwoNewLines(printStream);
		printStream.println("--------------------------------------------------");
		printStream.println("7      XXXXXX      XXXXXX      XXXXXX      XXXXXX|");
		printStream.println("7      X    X      X    X      X    X      X    X|");
		printStream.println("7  " + layout[0][7] + "  X " + layout[1][7] + " X  " + layout[2][7]
		    + "  X " + layout[3][7] + " X  " + layout[4][7] + "  X " + layout[5][7] + " X  "
		    + layout[6][7] + "  X " + layout[7][7] + " X|");
		printStream.println("7      X    X      X    X      X    X      X    X|");
		printStream.println("7      XXXXXX      XXXXXX      XXXXXX      XXXXXX|");
		printStream.println("6XXXXXX      XXXXXX      XXXXXX      XXXXXX      |");
		printStream.println("6X    X      X    X      X    X      X    X      |");
		printStream.println("6X " + layout[0][6] + " X  " + layout[1][6] + "  X " + layout[2][6]
		    + " X  " + layout[3][6] + "  X " + layout[4][6] + " X  " + layout[5][6] + "  X "
		    + layout[6][6] + " X  " + layout[7][6] + "  |");
		printStream.println("6X    X      X    X      X    X      X    X      |");
		printStream.println("6XXXXXX      XXXXXX      XXXXXX      XXXXXX      |");
		printStream.println("5      XXXXXX      XXXXXX      XXXXXX      XXXXXX|");
		printStream.println("5      X    X      X    X      X    X      X    X|");
		printStream.println("5  " + layout[0][5] + "  X " + layout[1][5] + " X  " + layout[2][5]
		    + "  X " + layout[3][5] + " X  " + layout[4][5] + "  X " + layout[5][5] + " X  "
		    + layout[6][5] + "  X " + layout[7][5] + " X|");
		printStream.println("5      X    X      X    X      X    X      X    X|");
		printStream.println("5      XXXXXX      XXXXXX      XXXXXX      XXXXXX|");
		printStream.println("4XXXXXX      XXXXXX      XXXXXX      XXXXXX      |");
		printStream.println("4X    X      X    X      X    X      X    X      |");
		printStream.println("4X " + layout[0][4] + " X  " + layout[1][4] + "  X " + layout[2][4]
		    + " X  " + layout[3][4] + "  X " + layout[4][4] + " X  " + layout[5][4] + "  X "
		    + layout[6][4] + " X  " + layout[7][4] + "  |");
		printStream.println("4X    X      X    X      X    X      X    X      |");
		printStream.println("4XXXXXX      XXXXXX      XXXXXX      XXXXXX      |");
		printStream.println("3      XXXXXX      XXXXXX      XXXXXX      XXXXXX|");
		printStream.println("3      X    X      X    X      X    X      X    X|");
		printStream.println("3  " + layout[0][3] + "  X " + layout[1][3] + " X  " + layout[2][3]
		    + "  X " + layout[3][3] + " X  " + layout[4][3] + "  X " + layout[5][3] + " X  "
		    + layout[6][3] + "  X " + layout[7][3] + " X|");
		printStream.println("3      X    X      X    X      X    X      X    X|");
		printStream.println("3      XXXXXX      XXXXXX      XXXXXX      XXXXXX|");
		printStream.println("2XXXXXX      XXXXXX      XXXXXX      XXXXXX      |");
		printStream.println("2X    X      X    X      X    X      X    X      |");
		printStream.println("2X " + layout[0][2] + " X  " + layout[1][2] + "  X " + layout[2][2]
		    + " X  " + layout[3][2] + "  X " + layout[4][2] + " X  " + layout[5][2] + "  X "
		    + layout[6][2] + " X  " + layout[7][2] + "  |");
		printStream.println("2X    X      X    X      X    X      X    X      |");
		printStream.println("2XXXXXX      XXXXXX      XXXXXX      XXXXXX      |");
		printStream.println("1      XXXXXX      XXXXXX      XXXXXX      XXXXXX|");
		printStream.println("1      X    X      X    X      X    X      X    X|");
		printStream.println("1  " + layout[0][1] + "  X " + layout[1][1] + " X  " + layout[2][1]
		    + "  X " + layout[3][1] + " X  " + layout[4][1] + "  X " + layout[5][1] + " X  "
		    + layout[6][1] + "  X " + layout[7][1] + " X|");
		printStream.println("1      X    X      X    X      X    X      X    X|");
		printStream.println("1      XXXXXX      XXXXXX      XXXXXX      XXXXXX|");
		printStream.println("0XXXXXX      XXXXXX      XXXXXX      XXXXXX      |");
		printStream.println("0X    X      X    X      X    X      X    X      |");
		printStream.println("0X " + layout[0][0] + " X  " + layout[1][0] + "  X " + layout[2][0]
		    + " X  " + layout[3][0] + "  X " + layout[4][0] + " X  " + layout[5][0] + "  X "
		    + layout[6][0] + " X  " + layout[7][0] + "  |");
		printStream.println("0X    X      X    X      X    X      X    X      |");
		printStream.println("0XXXXXX      XXXXXX      XXXXXX      XXXXXX      |");
		printStream.println(" 000000111111222222333333444444555555666666777777|");
		printTwoNewLines(printStream);

	}

	private void printTwoNewLines(PrintStream printStream)
	{
		printStream.println();
		printStream.println();
	}

	private String[][] buildLayoutMatrix(ChessBoard chessBoard)
	{
		String[][] squares = buildLayoutWithEmptySqyares();

		for (ChessPieceKey key : chessBoard.getPieces().keySet())
		{
			ChessPiece piece = chessBoard.getPiece(key);
			Position currentPostion = piece.getCurrentPostion();
			squares[piece.getCurrentPostion().getxPosition()][currentPostion.getyPosition()] = buildPieceToken(piece);
		}

		return squares;
	}

	private String[][] buildLayoutWithEmptySqyares()
	{
		String[][] squares = new String[8][8];
		for (String[] row : squares)
		{
			Arrays.fill(row, "  ");
		}
		return squares;
	}

	private String buildPieceToken(ChessPiece piece)
	{
		return getColorToken(piece) + getPieceToken(piece);
	}

	private String getPieceToken(ChessPiece piece)
	{
		if (piece instanceof KingPiece)
		{
			return "K";
		}
		if (piece instanceof QueenPiece)
		{
			return "Q";
		}
		if (piece instanceof RookPiece)
		{
			return "R";
		}
		if (piece instanceof KnightPiece)
		{
			return "N";
		}
		if (piece instanceof BishopPiece)
		{
			return "B";
		}
		// Must be a pawn
		return "P";
	}

	private String getColorToken(ChessPiece piece)
	{
		if (piece.getColor().equals(ChessPieceColor.WHITE))
		{
			return "W";
		}
		return "B";
	}
}
