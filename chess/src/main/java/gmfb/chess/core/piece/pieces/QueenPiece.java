package gmfb.chess.core.piece.pieces;

import gmfb.chess.core.Position;
import gmfb.chess.core.piece.AbstractChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.uitl.logic.possiblemoves.PossibleMoveGeneratorFactory;

public final class QueenPiece extends AbstractChessPiece
{
   public QueenPiece(Position currentPosition, ChessPieceColor color)
   {
      super(currentPosition, color);
      moveGenerator = PossibleMoveGeneratorFactory.getPossibleMoveGenerator(this, color);
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof QueenPiece)
      {
         return super.equals(obj);
      }
      return false;
   }
}
