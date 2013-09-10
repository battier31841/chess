package gmfb.chess.core.board;

import gmfb.chess.core.printer.BoardPrinterImpl;

import org.junit.Test;

public class BoardPrinterImplTest
{
	private BoardPrinterImpl boardPrinter = new BoardPrinterImpl();

	@Test
	public void shouldPrintNewChessBoard()
	{
		boardPrinter.printBoard(new ChessBoardImpl().buildNewGame(), System.out);
	}
}
