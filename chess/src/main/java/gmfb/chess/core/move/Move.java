package gmfb.chess.core.move;

import gmfb.chess.core.Position;
import gmfb.chess.core.piece.ChessPiece;

public class Move
{
	private ChessPiece piece;
	private Position to;

	public Move(ChessPiece piece, Position to)
	{
		this.piece = piece;
		this.to = to;
	}

	public ChessPiece getPiece()
	{
		return piece;
	}

	public Position getTo()
	{
		return to;
	}
}
