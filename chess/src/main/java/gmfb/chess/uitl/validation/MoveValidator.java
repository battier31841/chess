package gmfb.chess.uitl.validation;

import gmfb.chess.core.move.CastleMove;
import gmfb.chess.core.move.EnPassentMove;
import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.move.PawnPromotionMove;
import gmfb.chess.uitl.exception.InvalidCastleMoveException;
import gmfb.chess.uitl.exception.InvalidEnPassentMoveException;
import gmfb.chess.uitl.exception.InvalidKillingMoveException;
import gmfb.chess.uitl.exception.InvalidMoveException;
import gmfb.chess.uitl.exception.InvalidPawnPromotionMoveException;
import gmfb.chess.uitl.exception.PositionOutOfBoundsException;

public class MoveValidator implements Validator<Move>
{
   private PositionValidator positionValidator = new PositionValidator();
   private KillingMoveValidator killingMoveValidator = new KillingMoveValidator();
   private PawnPromotionMoveValidator pawnPromotionMoveValidator = new PawnPromotionMoveValidator();
   private EnPassentMoveValidator enPassentMoveValidator = new EnPassentMoveValidator();
   private CastleMoveValidator castleMoveValidator = new CastleMoveValidator();

   public void validate(Move move) throws PositionOutOfBoundsException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      positionValidator.validate(move.getTo());
      if (move instanceof EnPassentMove)
      {
         enPassentMoveValidator.validate((EnPassentMove) move);
      }
      else if (move instanceof KillingMove)
      {
         killingMoveValidator.validate((KillingMove) move);
      }
      if (move instanceof PawnPromotionMove)
      {
         pawnPromotionMoveValidator.validate((PawnPromotionMove) move);
      }
      if (move instanceof CastleMove)
      {
         castleMoveValidator.validate((CastleMove) move);
      }
      // regular move
      validatePositionsOfMove(move);
   }

   private void validatePositionsOfMove(Move move) throws InvalidMoveException
   {
      validateChangingPositions(move);
   }

   private void validateChangingPositions(Move move) throws InvalidMoveException
   {
      if (move.getPiece()
            .getCurrentPostion()
            .equals(move.getTo()))
      {
         throw new InvalidMoveException();
      }
   }
}
