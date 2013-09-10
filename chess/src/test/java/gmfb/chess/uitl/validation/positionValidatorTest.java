package gmfb.chess.uitl.validation;

import gmfb.chess.core.Position;
import gmfb.chess.uitl.exception.PositionOutOfBoundsException;

import org.junit.Test;

public class positionValidatorTest
{
   private final PositionValidator positionValidator = new PositionValidator();

   @Test
   public void shouldValidatePosition() throws PositionOutOfBoundsException
   {
      for (int i = 0; i < 8; i++)
         for (int j = 0; j < 8; j++)
         {
            positionValidator.validate(new Position(i, j));
         }
   }

   @Test(expected = PositionOutOfBoundsException.class)
   public void shouldNotValidatePositionMinX() throws PositionOutOfBoundsException
   {
      positionValidator.validate(new Position(-1, 0));
   }

   @Test(expected = PositionOutOfBoundsException.class)
   public void shouldNotValidatePositionMinY() throws PositionOutOfBoundsException
   {
      positionValidator.validate(new Position(0, -1));
   }

   @Test(expected = PositionOutOfBoundsException.class)
   public void shouldNotValidatePositionMaxX() throws PositionOutOfBoundsException
   {
      positionValidator.validate(new Position(8, 0));
   }

   @Test(expected = PositionOutOfBoundsException.class)
   public void shouldNotValidatePositionMaxY() throws PositionOutOfBoundsException
   {
      positionValidator.validate(new Position(0, 8));
   }
}
