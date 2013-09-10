package gmfb.chess.uitl.logic.possiblemoves;

import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.Move;

import java.util.Set;

public interface PossibleMoveGenerator
{
	public Set<Move> genrateMoves(ChessBoard chessBoard);
}
