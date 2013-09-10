package gmfb.chess.uitl.logic.check;

import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.piece.ChessPieceColor;

public interface BoardEvaluator
{
	public boolean isInCheck(ChessBoard chessBoard, ChessPieceColor color);

	public boolean isInCheckMate(ChessBoard chessBoard, ChessPieceColor color);
}
