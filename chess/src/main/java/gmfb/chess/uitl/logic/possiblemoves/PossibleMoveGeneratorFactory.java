package gmfb.chess.uitl.logic.possiblemoves;

import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.pieces.BishopPiece;
import gmfb.chess.core.piece.pieces.KnightPiece;
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.core.piece.pieces.QueenPiece;
import gmfb.chess.core.piece.pieces.RookPiece;

public class PossibleMoveGeneratorFactory
{
	public static PossibleMoveGenerator getPossibleMoveGenerator(ChessPiece chessPiece,
	    ChessPieceColor color)
	{
		if (chessPiece instanceof PawnPiece)
		{
			return new PawnPiecePossibleMoveGenerator(color);
		}
		if (chessPiece instanceof RookPiece)
		{
			return new RookPiecePossibleMoveGenerator(color);
		}
		if (chessPiece instanceof KnightPiece)
		{
			return new KnightPiecePossibleMoveGenerator(color);
		}
		if (chessPiece instanceof BishopPiece)
		{
			return new BishopPiecePossibleMoveGenerator(color);
		}
		if (chessPiece instanceof QueenPiece)
		{
			return new QueenPiecePossibleMoveGenerator(color);
		}
		// must be KingPiece
		return new KingPiecePossibleMoveGenerator(color);
	}
}
