package gmfb.chess.uitl.validation;

import gmfb.chess.core.Position;
import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.uitl.exception.InvalidKillingMoveException;

import org.junit.Test;

public class KillingMoveValidatorTest
{
	private static final Position POSITION = new Position(0, 0);
	private static final Position NEW_POSITION = new Position(0, 1);
	private static final ChessPiece WHITE_PIECE = new PawnPiece(POSITION, ChessPieceColor.WHITE);
	private static final ChessPiece BAD_WHITE_PIECE = new PawnPiece(NEW_POSITION,
	    ChessPieceColor.WHITE);
	private static final ChessPiece BLACK_PIECE = new PawnPiece(NEW_POSITION, ChessPieceColor.BLACK);
	private static final ChessPiece BAD_BLACK_PIECE = new PawnPiece(POSITION, ChessPieceColor.BLACK);

	private KillingMoveValidator killingMoveValidator = new KillingMoveValidator();

	@Test
	public void shouldValidateKillingMove() throws InvalidKillingMoveException
	{
		killingMoveValidator.validate(new KillingMove(WHITE_PIECE, NEW_POSITION, BLACK_PIECE));
	}

	@Test(expected = InvalidKillingMoveException.class)
	public void shouldNotValidateKillingMoveWrongColor() throws InvalidKillingMoveException
	{
		killingMoveValidator.validate(new KillingMove(WHITE_PIECE, NEW_POSITION, BAD_WHITE_PIECE));
	}

	@Test(expected = InvalidKillingMoveException.class)
	public void shouldNotValidateKillingMoveWrongPosition() throws InvalidKillingMoveException
	{
		killingMoveValidator.validate(new KillingMove(WHITE_PIECE, NEW_POSITION, BAD_BLACK_PIECE));
	}
}
