package gmfb.chess.uitl.logic.check;

import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.ChessPieceKey;
import gmfb.chess.uitl.logic.EnemyColorRetriever;

import java.util.Set;

public class BoardEvaluatorImpl implements BoardEvaluator
{
   public boolean isInCheck(ChessBoard chessBoard, ChessPieceColor color)
   {
      Position currentKingPosition = getKingPiece(chessBoard, color).getCurrentPostion();
      Set<Position> movablePositionsForEnemy = chessBoard.getAttackablePositions(EnemyColorRetriever.getEnemyColor(color));
      return movablePositionsForEnemy.contains(currentKingPosition);
   }

   // TODO
   public boolean isInCheckMate(ChessBoard chessBoard, ChessPieceColor color)
   {
      if (isInCheck(chessBoard, color))
      {
      }
      return false;
   }

   private ChessPiece getKingPiece(ChessBoard chessBoard, ChessPieceColor color)
   {
      return chessBoard.getPieces()
            .get(ChessPieceKey.getKingKey(color));
   }
}
