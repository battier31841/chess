package gmfb.chess.core.move;

import gmfb.chess.core.Position;
import gmfb.chess.core.piece.ChessPiece;

public class PawnPromotionMove extends Move
{
	private ChessPiece newPiece;

	public PawnPromotionMove(ChessPiece piece, Position to, ChessPiece newPiece)
	{
		super(piece, to);
		this.newPiece = newPiece;
	}

	public ChessPiece getNewPiece()
	{
		return newPiece;
	}
}
