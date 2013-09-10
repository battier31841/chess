package gmfb.chess.uitl.validation;

import gmfb.chess.core.Position;
import gmfb.chess.core.move.PawnPromotionMove;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.core.piece.pieces.QueenPiece;
import gmfb.chess.uitl.exception.InvalidPawnPromotionMoveException;

import org.junit.Test;

public class PawnPromotionValidatorTest
{
	private static final ChessPieceColor WHITE = ChessPieceColor.WHITE;
	private static final Position POSITION = new Position(0, 0);
	private static final Position NEW_POSITION = new Position(0, 7);
	private static final Position WRONG_NEW_POSITION = new Position(0, 6);
	private static final ChessPiece WHITE_PIECE = new PawnPiece(POSITION, WHITE);
	private static final ChessPiece QUEEN_PIECE = new QueenPiece(NEW_POSITION, WHITE);
	private static final ChessPiece BAD_POSITION_QUEEN_PIECE = new QueenPiece(WRONG_NEW_POSITION,
	    WHITE);
	private static final ChessPiece BAD_QUEEN_PIECE = new QueenPiece(POSITION, WHITE);
	private static final ChessPiece BLACK_QUEEN_PIECE = new QueenPiece(NEW_POSITION,
	    ChessPieceColor.BLACK);

	private PawnPromotionMoveValidator pawnPromotionValidator = new PawnPromotionMoveValidator();

	@Test
	public void shouldValidatePawnPromotionMove() throws InvalidPawnPromotionMoveException
	{
		pawnPromotionValidator.validate(new PawnPromotionMove(WHITE_PIECE, NEW_POSITION, QUEEN_PIECE));
	}

	@Test(expected = InvalidPawnPromotionMoveException.class)
	public void shouldNotValidatePawnPromotionMoveBadPosition() throws InvalidPawnPromotionMoveException
	{
		pawnPromotionValidator.validate(new PawnPromotionMove(WHITE_PIECE, NEW_POSITION, BAD_QUEEN_PIECE));
	}

	@Test(expected = InvalidPawnPromotionMoveException.class)
	public void shouldNotValidatePawnPromotionMoveWrongColor() throws InvalidPawnPromotionMoveException
	{
		pawnPromotionValidator
		    .validate(new PawnPromotionMove(WHITE_PIECE, NEW_POSITION, BLACK_QUEEN_PIECE));
	}

	@Test(expected = InvalidPawnPromotionMoveException.class)
	public void shouldNotValidatePawnPromotionNotPawn() throws InvalidPawnPromotionMoveException
	{
		pawnPromotionValidator
		    .validate(new PawnPromotionMove(QUEEN_PIECE, NEW_POSITION, BLACK_QUEEN_PIECE));
	}

	@Test(expected = InvalidPawnPromotionMoveException.class)
	public void shouldNotValidatePawnPromotionWrongRow() throws InvalidPawnPromotionMoveException
	{
		pawnPromotionValidator.validate(new PawnPromotionMove(WHITE_PIECE, WRONG_NEW_POSITION,
		    BAD_POSITION_QUEEN_PIECE));
	}
}
