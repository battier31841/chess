package gmfb.chess.uitl.validation;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import gmfb.chess.core.move.EnPassentMove;
import gmfb.chess.uitl.exception.InvalidEnPassentMoveException;
import gmfb.chess.uitl.logic.possiblemoves.EnPassentMoveEvaluator;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

public class EnPassentMoveValidatorTest
{
	private EnPassentMoveValidator enPassentMoveValidator = new EnPassentMoveValidator();
	private IMocksControl mocksControl = createControl();
	private EnPassentMoveEvaluator enPassentMoveEvaluator = mocksControl
	    .createMock(EnPassentMoveEvaluator.class);

	@Before
	public void setUp()
	{
		enPassentMoveValidator.setEnPassentMoveEvaluator(enPassentMoveEvaluator);
	}

	@Test
	public void shouldValidateEnPassentMove() throws InvalidEnPassentMoveException
	{
		expect(enPassentMoveEvaluator.evaluateMovePosibility(isA(EnPassentMove.class))).andReturn(true);

		mocksControl.replay();
		enPassentMoveValidator.validate(new EnPassentMove(null, null, null, null));
		mocksControl.verify();
	}

	@Test(expected = InvalidEnPassentMoveException.class)
	public void shouldNotValidateEnPassentMove() throws InvalidEnPassentMoveException
	{
		expect(enPassentMoveEvaluator.evaluateMovePosibility(isA(EnPassentMove.class)))
		    .andReturn(false);

		mocksControl.replay();
		enPassentMoveValidator.validate(new EnPassentMove(null, null, null, null));
		mocksControl.verify();
	}
}
