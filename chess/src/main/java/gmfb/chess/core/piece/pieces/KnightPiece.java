package gmfb.chess.core.piece.pieces;

import gmfb.chess.core.Position;
import gmfb.chess.core.piece.AbstractChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;

public final class KnightPiece extends AbstractChessPiece
{
   private KnightPiece()
   {
   }

   public KnightPiece(Position currentPosition, ChessPieceColor color)
   {
      super(currentPosition, color);
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

   @Override
   protected AbstractChessPiece instantiatePiece()
   {
      return new KnightPiece();
   }
}
