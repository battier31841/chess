package gmfb.chess.uitl.logic.handlemoves;

import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.Move;

public interface MoveHandler
{
   public void handleMove(ChessBoard chessBoard, final Move move);
}
