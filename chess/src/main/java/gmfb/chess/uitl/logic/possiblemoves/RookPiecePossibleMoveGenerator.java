package gmfb.chess.uitl.logic.possiblemoves;

import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPieceColor;

import java.util.HashSet;
import java.util.Set;

public class RookPiecePossibleMoveGenerator implements PossibleMoveGenerator
{
	private ChessPieceColor color;

	public RookPiecePossibleMoveGenerator(ChessPieceColor color)
	{
		this.color = color;
	}

	public Set<Move> genrateMoves(ChessBoard chessBoard)
	{
		return new HashSet<Move>();
	}
}
