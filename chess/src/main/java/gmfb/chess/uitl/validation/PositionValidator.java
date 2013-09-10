package gmfb.chess.uitl.validation;

import gmfb.chess.core.Position;
import gmfb.chess.uitl.exception.PositionOutOfBoundsException;

public class PositionValidator implements Validator<Position>
{
   public static final int MIN = 0;
   public static final int MAX = 7;

   public void validate(Position position) throws PositionOutOfBoundsException
   {
      checkAxisValue(position.getxPosition());
      checkAxisValue(position.getyPosition());
   }

   private void checkAxisValue(int axisValue) throws PositionOutOfBoundsException
   {
      if (axisValue < MIN || axisValue > MAX)
      {
         throw new PositionOutOfBoundsException();
      }
   }
}
