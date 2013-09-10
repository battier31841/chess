package gmfb.chess.uitl.validation;

import gmfb.chess.core.move.CastleMove;
import gmfb.chess.uitl.exception.InvalidCastleMoveException;
import gmfb.chess.uitl.logic.possiblemoves.CastleMoveEvaluator;

public class CastleMoveValidator implements Validator<CastleMove>
{
   private CastleMoveEvaluator castleMoveEvaluator = new CastleMoveEvaluator();

   public void validate(CastleMove castleMove) throws InvalidCastleMoveException
   {
      if (!castleMoveEvaluator.evaluateMovePosibility(castleMove))
      {
         throw new InvalidCastleMoveException();
      }
   }

   public void setCastleMoveEvaluator(CastleMoveEvaluator castleMoveEvaluator)
   {
      this.castleMoveEvaluator = castleMoveEvaluator;
   }
}
