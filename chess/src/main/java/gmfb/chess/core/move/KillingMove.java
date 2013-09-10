package gmfb.chess.core.move;

import gmfb.chess.core.Position;
import gmfb.chess.core.piece.ChessPiece;

public class KillingMove extends Move
{
	private ChessPiece deadPiece;

	public KillingMove(ChessPiece piece, Position to, ChessPiece deadPiece)
	{
		super(piece, to);
		this.deadPiece = deadPiece;
	}

	public ChessPiece getDeadPiece()
	{
		return deadPiece;
	}
}
