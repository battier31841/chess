package gmfb.chess.uitl.validation;

import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.uitl.exception.InvalidKillingMoveException;

public class KillingMoveValidator implements Validator<KillingMove>
{
   private ChessPiece piece;
   private ChessPiece deadPiece;

   public void validate(KillingMove killingMove) throws InvalidKillingMoveException
   {
      piece = killingMove.getPiece();
      deadPiece = killingMove.getDeadPiece();

      ensureDifferentColor();
      ensureCorrectPositions(killingMove);
   }

   private void ensureCorrectPositions(KillingMove killingMove) throws InvalidKillingMoveException
   {
      if (!deadPiece.getCurrentPostion()
            .equals(killingMove.getTo()))
      {
         throw new InvalidKillingMoveException();
      }
   }

   private void ensureDifferentColor() throws InvalidKillingMoveException
   {
      if (piece.getColor()
            .equals(deadPiece.getColor()))
      {
         throw new InvalidKillingMoveException();
      }
   }
}
