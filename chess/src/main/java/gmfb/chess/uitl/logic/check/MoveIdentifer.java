package gmfb.chess.uitl.logic.check;

import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.uitl.logic.EnemyColorRetriever;

import java.util.HashSet;
import java.util.Set;

public class MoveIdentifer
{
   public Set<KillingMove> getAttackingMoves(ChessPiece chessPiece, ChessBoard chessBoard)
   {
      Set<KillingMove> attackingMoves = new HashSet<KillingMove>();
      for (Move move : getEnemyMoves(chessPiece.getColor(), chessBoard))
      {
         if (move.getTo()
               .equals(chessPiece.getCurrentPostion()))
         {
            attackingMoves.add((KillingMove) move);
         }
      }
      return attackingMoves;
   }

   public Set<Move> getMovesToPosition(Position position, ChessPieceColor color, ChessBoard chessBoard)
   {
      Set<Move> moves = new HashSet<Move>();
      for (Move move : chessBoard.getMovesForColor(color))
      {
         if (position.equals(move.getTo()))
         {
            moves.add(move);
         }
      }
      return moves;
   }

   private Set<Move> getEnemyMoves(ChessPieceColor color, ChessBoard chessBoard)
   {
      return chessBoard.getMovesForColor(EnemyColorRetriever.getEnemyColor(color));
   }
}
