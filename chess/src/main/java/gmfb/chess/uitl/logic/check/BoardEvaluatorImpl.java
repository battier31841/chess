package gmfb.chess.uitl.logic.check;

import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.ChessPieceKey;
import gmfb.chess.core.piece.pieces.KnightPiece;
import gmfb.chess.uitl.logic.EnemyColorRetriever;

import java.util.Set;

public class BoardEvaluatorImpl implements BoardEvaluator
{
   private ChessBoard chessBoard;
   private ChessPieceColor color;
   private ChessPieceColor enemyColor;

   private MoveIdentifer moveIdentifer = new MoveIdentifer();
   private AttackingPathHelper attackingPathHelper = new AttackingPathHelper();

   public boolean isInCheck(final ChessBoard chessBoard, final ChessPieceColor color)
   {
      this.chessBoard = chessBoard;
      this.color = color;
      this.enemyColor = EnemyColorRetriever.getEnemyColor(color);

      Position currentKingPosition = getKingPiece().getCurrentPostion();
      Set<Position> movablePositionsForEnemy = chessBoard.getAttackablePositions(enemyColor);
      return movablePositionsForEnemy.contains(currentKingPosition);
   }

   public boolean isInCheckMate(final ChessBoard chessBoard, final ChessPieceColor color)
   {
      if (isInCheck(chessBoard, color))
      {
         return (canMove() == false) && (canKillAttacker() == false) && (canBlockAttacker() == false);
      }
      return false;
   }

   private boolean canBlockAttacker()
   {
      Set<KillingMove> attackingMoves = moveIdentifer.getAttackingMoves(getKingPiece(), chessBoard);
      if (attackingMoves.size() == 1 && (!(getAttacker(attackingMoves) instanceof KnightPiece)))
      {
         Set<Position> attackingPath =
               attackingPathHelper.buildPath(getKingPiece().getCurrentPostion(), getAttacker(attackingMoves).getCurrentPostion());
         for (Position pathPosition : attackingPath)
         {
            for (Move move : moveIdentifer.getMovesToPosition(pathPosition, color, chessBoard))
               if (willMoveRemoveCheck(move))
               {
                  return true;
               }
         }
      }
      return false;
   }

   private boolean canKillAttacker()
   {
      Set<KillingMove> attackingMoves = moveIdentifer.getAttackingMoves(getKingPiece(), chessBoard);
      if (attackingMoves.size() == 1)
      {
         for (KillingMove move : getAttackerKillingMoves(attackingMoves))
         {
            if (willMoveRemoveCheck(move))
            {
               return true;
            }
         }
      }
      return false;
   }

   private boolean willMoveRemoveCheck(Move move)
   {
      ChessBoard tempChessBoard = chessBoard.getClone();
      try
      {
         tempChessBoard.handleMove(move);
         if (!tempChessBoard.isInCheck(color))
         {
            return true;
         }
         return false;
      }
      catch (Exception e)
      {
         throw new IllegalStateException("An invalid move has been generated : " + e);
      }
   }

   private Set<KillingMove> getAttackerKillingMoves(Set<KillingMove> attackingMoves)
   {
      return moveIdentifer.getAttackingMoves(getAttacker(attackingMoves), chessBoard);
   }

   private ChessPiece getAttacker(Set<KillingMove> attackingMoves)
   {
      return attackingMoves.iterator()
            .next()
            .getPiece();
   }

   private boolean canMove()
   {
      return getKingPiece().getPossibleMoves(chessBoard)
            .size() != 0;
   }

   private ChessPiece getKingPiece()
   {
      return chessBoard.getPieces()
            .get(ChessPieceKey.getKingKey(color));
   }
}
