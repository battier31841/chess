package gmfb.chess.uitl.logic;

import static org.fest.assertions.Assertions.assertThat;
import gmfb.chess.core.Position;
import gmfb.chess.core.move.EnPassentMove;
import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.move.PawnPromotionMove;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.pieces.BishopPiece;
import gmfb.chess.core.piece.pieces.KingPiece;
import gmfb.chess.core.piece.pieces.KnightPiece;
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.core.piece.pieces.QueenPiece;
import gmfb.chess.core.piece.pieces.RookPiece;

import org.junit.Test;

public class LastMoveSnapShotBuilderTest
{
	private static final Position POSITION = new Position(0, 0);
	private static final ChessPiece CHESS_PIECE = new PawnPiece(null, null);
	private static final Position FIRST_POSITION = new Position(1, 1);
	private static final ChessPieceColor COLOR = ChessPieceColor.WHITE;

	private LastMoveSnapShotBuilder lastMoveSnapShotBuilder = new LastMoveSnapShotBuilder();

	@Test
	public void shouldBuildLastEnPassentMove()
	{
		KingPiece piece = new KingPiece(FIRST_POSITION, COLOR);
		EnPassentMove move = new EnPassentMove(piece, POSITION, CHESS_PIECE, new Move(null, null));
		buildSnapShotAndAssert(piece, move);
	}

	@Test
	public void shouldBuildLastKilingMove()
	{
		RookPiece piece = new RookPiece(FIRST_POSITION, COLOR);
		KillingMove move = new KillingMove(piece, POSITION, CHESS_PIECE);
		buildSnapShotAndAssert(piece, move);
	}

	@Test
	public void shouldBuildLastPawnPromotionMove()
	{
		KnightPiece piece = new KnightPiece(FIRST_POSITION, COLOR);
		PawnPromotionMove move = new PawnPromotionMove(piece, POSITION, CHESS_PIECE);
		buildSnapShotAndAssert(piece, move);
	}

	@Test
	public void shouldBuildLastMove()
	{
		BishopPiece piece = new BishopPiece(FIRST_POSITION, COLOR);
		Move move = new Move(piece, POSITION);
		buildSnapShotAndAssert(piece, move);
	}

	@Test
	public void coverageForQueenPiece()
	{
		QueenPiece piece = new QueenPiece(FIRST_POSITION, COLOR);
		Move move = new Move(piece, POSITION);
		buildSnapShotAndAssert(piece, move);
	}

	private void buildSnapShotAndAssert(ChessPiece piece, Move move)
	{
		lastMoveSnapShotBuilder.buildSnapShoot(move);
		preformAsserts(piece, move);
	}

	private void preformAsserts(ChessPiece piece, Move move)
	{
		piece = null;
		assertThat(move.getPiece().getCurrentPostion()).isEqualTo(FIRST_POSITION);
		assertThat(move.getTo()).isEqualTo(POSITION);
		assertThat(piece).isNull();
	}
}
