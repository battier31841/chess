package gmfb.chess.uitl.validation;

import gmfb.chess.core.move.PawnPromotionMove;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.uitl.exception.InvalidPawnPromotionMoveException;

public class PawnPromotionMoveValidator implements Validator<PawnPromotionMove>
{
   private static final int BLACK_FIRST_ROW = 7;
   private static final int WHITE_FIRST_ROW = 0;

   private ChessPiece pawnPiece;
   private ChessPiece newPiece;

   public void validate(PawnPromotionMove pawnPromotion) throws InvalidPawnPromotionMoveException
   {
      pawnPiece = pawnPromotion.getPiece();
      newPiece = pawnPromotion.getNewPiece();

      ensurePawn();
      ensureSameColor();
      ensureCorrectPositions(pawnPromotion);
      ensureCorrectRow(pawnPromotion);
   }

   private void ensureCorrectRow(PawnPromotionMove pawnPromotion) throws InvalidPawnPromotionMoveException
   {
      if (pawnPromotion.getTo()
            .getyPosition() != getProperRow(pawnPiece.getColor()))
      {
         throw new InvalidPawnPromotionMoveException();
      }
   }

   private void ensurePawn() throws InvalidPawnPromotionMoveException
   {
      if (!(pawnPiece instanceof PawnPiece))
      {
         throw new InvalidPawnPromotionMoveException();
      }
   }

   private void ensureCorrectPositions(PawnPromotionMove pawnPromotion) throws InvalidPawnPromotionMoveException
   {
      if (!pawnPromotion.getTo()
            .equals(newPiece.getCurrentPostion()))
      {
         throw new InvalidPawnPromotionMoveException();
      }
   }

   private void ensureSameColor() throws InvalidPawnPromotionMoveException
   {
      if (!pawnPiece.getColor()
            .equals(newPiece.getColor()))
      {
         throw new InvalidPawnPromotionMoveException();
      }
   }

   private int getProperRow(ChessPieceColor color)
   {
      if (color.equals(ChessPieceColor.WHITE))
      {
         return BLACK_FIRST_ROW;
      }
      return WHITE_FIRST_ROW;
   }
}
