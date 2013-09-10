package gmfb.chess.uitl.validation;

import gmfb.chess.core.move.EnPassentMove;
import gmfb.chess.uitl.exception.InvalidEnPassentMoveException;
import gmfb.chess.uitl.logic.possiblemoves.EnPassentMoveEvaluator;

public class EnPassentMoveValidator implements Validator<EnPassentMove>
{
	private EnPassentMoveEvaluator enPassentMoveEvaluator = new EnPassentMoveEvaluator();

	public void validate(EnPassentMove enPassentMove) throws InvalidEnPassentMoveException
	{
		if (!enPassentMoveEvaluator.evaluateMovePosibility(enPassentMove))
		{
			throw new InvalidEnPassentMoveException();
		}
	}

	public void setEnPassentMoveEvaluator(EnPassentMoveEvaluator enPassentMoveEvaluator)
	{
		this.enPassentMoveEvaluator = enPassentMoveEvaluator;
	}
}