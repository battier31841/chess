package gmfb.chess.uitl.validation;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import gmfb.chess.core.move.CastleMove;
import gmfb.chess.uitl.exception.InvalidCastleMoveException;
import gmfb.chess.uitl.logic.possiblemoves.CastleMoveEvaluator;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

public class CastleMoveValidatorTest
{
   private CastleMoveValidator castleMoveValidator = new CastleMoveValidator();
   private IMocksControl mocksControl = createControl();
   private CastleMoveEvaluator castleMoveEvaluator = mocksControl.createMock(CastleMoveEvaluator.class);

   @Before
   public void setUp()
   {
      castleMoveValidator.setCastleMoveEvaluator(castleMoveEvaluator);
   }

   @Test
   public void shouldValidateEnPassentMove() throws InvalidCastleMoveException
   {
      expect(castleMoveEvaluator.evaluateMovePosibility(isA(CastleMove.class))).andReturn(true);

      mocksControl.replay();
      castleMoveValidator.validate(new CastleMove(null, null, null, null));
      mocksControl.verify();
   }

   @Test(expected = InvalidCastleMoveException.class)
   public void shouldNotValidateEnPassentMove() throws InvalidCastleMoveException
   {
      expect(castleMoveEvaluator.evaluateMovePosibility(isA(CastleMove.class))).andReturn(false);

      mocksControl.replay();
      castleMoveValidator.validate(new CastleMove(null, null, null, null));
      mocksControl.verify();
   }
}
