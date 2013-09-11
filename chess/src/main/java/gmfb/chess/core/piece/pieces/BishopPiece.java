package gmfb.chess.core.piece.pieces;

import gmfb.chess.core.Position;
import gmfb.chess.core.piece.AbstractChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;

public final class BishopPiece extends AbstractChessPiece
{
   private BishopPiece()
   {
   }

   public BishopPiece(Position currentPosition, ChessPieceColor color)
   {
      super(currentPosition, color);
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof BishopPiece)
      {
         return super.equals(obj);
      }
      return false;
   }

   @Override
   protected AbstractChessPiece instantiatePiece()
   {
      return new BishopPiece();
   }
}
