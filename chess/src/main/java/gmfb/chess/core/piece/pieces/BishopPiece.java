package gmfb.chess.core.piece.pieces;

import gmfb.chess.core.Position;
import gmfb.chess.core.piece.AbstractChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;

public final class BishopPiece extends AbstractChessPiece
{
	public BishopPiece(Position currentPosition, ChessPieceColor color)
	{
		super(currentPosition, color);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof BishopPiece)
		{
			return false;
		}
		return super.equals(obj);
	}
}
