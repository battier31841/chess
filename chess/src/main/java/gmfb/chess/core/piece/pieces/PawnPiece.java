package gmfb.chess.core.piece.pieces;

import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.AbstractChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;

import java.util.HashSet;
import java.util.Set;

public final class PawnPiece extends AbstractChessPiece
{
   public PawnPiece(Position currentPosition, ChessPieceColor color)
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
         if (move.getTo()
               .getyPosition() != this.currentPosition.getyPosition())
            newPossibleKillingMoves.add(move);
      }
      return newPossibleKillingMoves;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof KnightPiece)
      {
         return super.equals(obj);
      }
      return false;
   }
}
