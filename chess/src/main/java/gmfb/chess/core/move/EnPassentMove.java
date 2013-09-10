package gmfb.chess.core.move;

import gmfb.chess.core.Position;
import gmfb.chess.core.piece.ChessPiece;

public class EnPassentMove extends KillingMove
{
	private Move lastMove;

	public EnPassentMove(ChessPiece piece, Position to, ChessPiece deadPiece, Move lastMove)
	{
		super(piece, to, deadPiece);
		this.lastMove = lastMove;
	}

	public Move getLastMove()
	{
		return lastMove;
	}
}
