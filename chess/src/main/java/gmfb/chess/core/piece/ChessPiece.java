package gmfb.chess.core.piece;

import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.Move;

import java.util.Set;

public interface ChessPiece
{
	public ChessPieceColor getColor();

	public Position getCurrentPostion();

	public ChessPiece updatePosition(Position position);

	public Set<Move> getPossibleMoves(ChessBoard chessBoard);

	public Set<Move> getPossibleKillingMoves(ChessBoard chessBoard);
}
