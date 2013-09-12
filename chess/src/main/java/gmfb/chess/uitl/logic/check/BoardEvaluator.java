package gmfb.chess.uitl.logic.check;

import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.piece.ChessPieceColor;

public interface BoardEvaluator
{
   public boolean isInCheck(final ChessBoard chessBoard, final ChessPieceColor color);

   public boolean isInCheckMate(final ChessBoard chessBoard, final ChessPieceColor color);
}
