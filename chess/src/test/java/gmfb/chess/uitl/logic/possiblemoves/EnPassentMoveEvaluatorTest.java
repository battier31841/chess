package gmfb.chess.uitl.logic.possiblemoves;

import static org.fest.assertions.Assertions.assertThat;
import gmfb.chess.core.Position;
import gmfb.chess.core.move.EnPassentMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.core.piece.pieces.QueenPiece;
import gmfb.chess.uitl.exception.InvalidEnPassentMoveException;

import org.junit.Test;

public class EnPassentMoveEvaluatorTest
{
	private static final Position BEFORE_MOVE = new Position(2, 2);
	private static final Position AFTER_MOVE = new Position(1, 1);
	private static final Position DEAD_PIECE_POSITION = new Position(1, 2);
	private static final Position DEAD_PIECE_POSITION_LAST_MOVE = new Position(1, 0);
	private static final PawnPiece PAWN_PIECE = new PawnPiece(BEFORE_MOVE, ChessPieceColor.BLACK);
	private static final PawnPiece DEAD_PAWN_PIECE = new PawnPiece(DEAD_PIECE_POSITION,
	    ChessPieceColor.WHITE);
	private static final PawnPiece DEAD_PAWN_PIECE_LAST_MOVE = new PawnPiece(
	    DEAD_PIECE_POSITION_LAST_MOVE, ChessPieceColor.WHITE);
	private static final Move LAST_MOVE = new Move(DEAD_PAWN_PIECE_LAST_MOVE, DEAD_PIECE_POSITION);
	private EnPassentMove enPassentMove = new EnPassentMove(PAWN_PIECE, AFTER_MOVE, DEAD_PAWN_PIECE,
	    LAST_MOVE);

	private EnPassentMoveEvaluator enPassentMoveEvaluator = new EnPassentMoveEvaluator();

	@Test
	public void shouldValidateEnPassentMove() throws InvalidEnPassentMoveException
	{
		assertThat(enPassentMoveEvaluator.evaluateMovePosibility(enPassentMove)).isTrue();
	}

	@Test
	public void shouldNotValidateEnPassentMoveWrongLastMove() throws InvalidEnPassentMoveException
	{
		enPassentMove = new EnPassentMove(PAWN_PIECE, AFTER_MOVE, DEAD_PAWN_PIECE, new Move(
		    new PawnPiece(new Position(0, 0), ChessPieceColor.WHITE), new Position(0, 1)));
		enPassentMoveEvaluator.evaluateMovePosibility(enPassentMove);
	}

	@Test
	public void shouldNotValidateEnPassentMoveWrongPositions() throws InvalidEnPassentMoveException
	{
		enPassentMove = new EnPassentMove(PAWN_PIECE, AFTER_MOVE, new PawnPiece(new Position(6, 6),
		    ChessPieceColor.WHITE), LAST_MOVE);
		assertThat(enPassentMoveEvaluator.evaluateMovePosibility(enPassentMove)).isFalse();
	}

	@Test
	public void shouldNotValidateEnPassentMoveNotPawn() throws InvalidEnPassentMoveException
	{
		enPassentMove = new EnPassentMove(PAWN_PIECE, AFTER_MOVE, DEAD_PAWN_PIECE, new Move(
		    new QueenPiece(new Position(0, 0), ChessPieceColor.WHITE), new Position(0, 1)));
		assertThat(enPassentMoveEvaluator.evaluateMovePosibility(enPassentMove)).isFalse();
	}

	@Test
	public void shouldNotValidateEnPassentMoveWrongColor() throws InvalidEnPassentMoveException
	{
		enPassentMove = new EnPassentMove(PAWN_PIECE, AFTER_MOVE, new PawnPiece(DEAD_PIECE_POSITION,
		    ChessPieceColor.BLACK), LAST_MOVE);
		assertThat(enPassentMoveEvaluator.evaluateMovePosibility(enPassentMove)).isFalse();
	}
}
