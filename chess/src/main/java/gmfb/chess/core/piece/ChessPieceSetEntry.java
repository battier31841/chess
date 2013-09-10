package gmfb.chess.core.piece;

public class ChessPieceSetEntry
{
	private ChessPiece chessPiece;
	private ChessPieceKey key;

	public ChessPieceSetEntry(ChessPiece chessPiece, ChessPieceKey key)
	{
		this.chessPiece = chessPiece;
		this.key = key;
	}

	public ChessPiece getChessPiece()
	{
		return chessPiece;
	}

	public ChessPieceKey getKey()
	{
		return key;
	}
}
