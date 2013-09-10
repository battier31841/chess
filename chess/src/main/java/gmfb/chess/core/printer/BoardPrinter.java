package gmfb.chess.core.printer;

import gmfb.chess.core.board.ChessBoard;

import java.io.PrintStream;

public interface BoardPrinter
{
	public void printBoard(ChessBoard chessBoard, PrintStream printStream);
}
