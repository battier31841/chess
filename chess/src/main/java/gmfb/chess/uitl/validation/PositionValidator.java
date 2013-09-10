package gmfb.chess.uitl.validation;

import gmfb.chess.core.Position;
import gmfb.chess.uitl.exception.PositionOutOfBoundsException;

public class PositionValidator implements Validator<Position>
{
   public void validate(Position position) throws PositionOutOfBoundsException
   {
      if (!Position.isValid(position))
      {
         throw new PositionOutOfBoundsException();
      }
   }
}