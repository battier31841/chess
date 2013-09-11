package gmfb.chess.uitl.logic.check;

import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.uitl.logic.EnemyColorRetriever;

import java.util.HashSet;
import java.util.Set;

public class AttackingMoveIdentifer
{
   public Set<KillingMove> getAttackinhgMoves(ChessPiece chessPiece, ChessBoard chessBoard)
   {
      Set<KillingMove> attackingMoves = new HashSet<KillingMove>();
      Set<Move> enemyMoves = getEnemyMoves(chessPiece.getColor(), chessBoard);
      for (Move move : enemyMoves)
      {
         if ((move instanceof KillingMove) && move.getTo()
               .equals(chessPiece.getCurrentPostion()))
         {
            attackingMoves.add((KillingMove) move);
         }
      }
      return attackingMoves;
   }

   private Set<Move> getEnemyMoves(ChessPieceColor color, ChessBoard chessBoard)
   {
      return chessBoard.getMovesForColor(EnemyColorRetriever.getEnemyColor(color));
   }
}
