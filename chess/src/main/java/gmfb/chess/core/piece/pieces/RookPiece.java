package gmfb.chess.core.piece.pieces;

import gmfb.chess.core.Position;
import gmfb.chess.core.piece.AbstractChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;

public final class RookPiece extends AbstractChessPiece
{
   public RookPiece(Position currentPosition, ChessPieceColor color)
   {
      super(currentPosition, color);
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof RookPiece)
      {
         return super.equals(obj);
      }
      return false;
   }
}
