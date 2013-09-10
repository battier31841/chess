package gmfb.chess.core.piece.pieces;

import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.CastleMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.AbstractChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;

import java.util.HashSet;
import java.util.Set;

public final class KingPiece extends AbstractChessPiece
{
   public KingPiece(Position currentPosition, ChessPieceColor color)
   {
      super(currentPosition, color);
   }

   @Override
   public Set<Move> getPossibleKillingMoves(ChessBoard chessBoard)
   {
      Set<Move> possibleKillingMoves = super.getPossibleKillingMoves(chessBoard);
      Set<Move> newPossibleKillingMoves = new HashSet<Move>();
      for (Move move : possibleKillingMoves)
      {
         if (!(move instanceof CastleMove))
         {
            newPossibleKillingMoves.add(move);
         }
      }
      return newPossibleKillingMoves;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof KingPiece)
      {
         return super.equals(obj);
      }
      return false;
   }
}
