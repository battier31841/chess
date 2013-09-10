package gmfb.chess.uitl.logic;

import gmfb.chess.core.move.EnPassentMove;
import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.move.PawnPromotionMove;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.pieces.BishopPiece;
import gmfb.chess.core.piece.pieces.KingPiece;
import gmfb.chess.core.piece.pieces.KnightPiece;
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.core.piece.pieces.QueenPiece;
import gmfb.chess.core.piece.pieces.RookPiece;

public class LastMoveSnapShotBuilder
{
	public Move buildSnapShoot(Move move)
	{
		if (move instanceof EnPassentMove)
		{
			return new EnPassentMove(copyPieceValues(((EnPassentMove) move).getPiece()),
			    ((EnPassentMove) move).getTo(), copyPieceValues(((EnPassentMove) move).getDeadPiece()),
			    ((EnPassentMove) move).getLastMove());
		} else if (move instanceof KillingMove)
		{
			return new KillingMove(copyPieceValues(((KillingMove) move).getPiece()),
			    ((KillingMove) move).getTo(), copyPieceValues(((KillingMove) move).getDeadPiece()));
		}
		if (move instanceof PawnPromotionMove)
		{
			return new PawnPromotionMove(copyPieceValues(((PawnPromotionMove) move).getPiece()),
			    ((PawnPromotionMove) move).getTo(), copyPieceValues(((PawnPromotionMove) move).getNewPiece()));
		}
		return new Move(copyPieceValues(move.getPiece()), move.getTo());
	}

	private ChessPiece copyPieceValues(ChessPiece chessPiece)
	{
		if (chessPiece instanceof PawnPiece)
		{
			return new PawnPiece(chessPiece.getCurrentPostion(), chessPiece.getColor());
		}
		if (chessPiece instanceof RookPiece)
		{
			return new RookPiece(chessPiece.getCurrentPostion(), chessPiece.getColor());
		}
		if (chessPiece instanceof KnightPiece)
		{
			return new KnightPiece(chessPiece.getCurrentPostion(), chessPiece.getColor());
		}
		if (chessPiece instanceof BishopPiece)
		{
			return new BishopPiece(chessPiece.getCurrentPostion(), chessPiece.getColor());
		}
		if (chessPiece instanceof QueenPiece)
		{
			return new QueenPiece(chessPiece.getCurrentPostion(), chessPiece.getColor());
		}
		// must be KingPiece
		return new KingPiece(chessPiece.getCurrentPostion(), chessPiece.getColor());
	}
}
