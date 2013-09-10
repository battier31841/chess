package gmfb.chess.core.piece;

import gmfb.chess.core.Position;
import gmfb.chess.core.piece.pieces.BishopPiece;
import gmfb.chess.core.piece.pieces.KingPiece;
import gmfb.chess.core.piece.pieces.KnightPiece;
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.core.piece.pieces.QueenPiece;
import gmfb.chess.core.piece.pieces.RookPiece;

import java.util.HashSet;
import java.util.Set;

public class ChessPieceSet
{
   private static Set<ChessPieceSetEntry> chessPieceSet = new HashSet<ChessPieceSetEntry>();

   static
   {
      chessPieceSet.add(new ChessPieceSetEntry(new RookPiece(new Position(0, 0), ChessPieceColor.WHITE), ChessPieceKey.WHITE_KING_ROOK));
      chessPieceSet.add(new ChessPieceSetEntry(new KnightPiece(new Position(1, 0), ChessPieceColor.WHITE), ChessPieceKey.WHITE_KING_KNIGHT));
      chessPieceSet.add(new ChessPieceSetEntry(new BishopPiece(new Position(2, 0), ChessPieceColor.WHITE), ChessPieceKey.WHITE_KING_BISHOP));
      chessPieceSet.add(new ChessPieceSetEntry(new QueenPiece(new Position(3, 0), ChessPieceColor.WHITE), ChessPieceKey.WHITE_QUEEN));
      chessPieceSet.add(new ChessPieceSetEntry(new KingPiece(new Position(4, 0), ChessPieceColor.WHITE), ChessPieceKey.WHITE_KING));
      chessPieceSet.add(new ChessPieceSetEntry(new BishopPiece(new Position(5, 0), ChessPieceColor.WHITE), ChessPieceKey.WHITE_QUEEN_BISHOP));
      chessPieceSet.add(new ChessPieceSetEntry(new KnightPiece(new Position(6, 0), ChessPieceColor.WHITE), ChessPieceKey.WHITE_QUEEN_KNIGHT));
      chessPieceSet.add(new ChessPieceSetEntry(new RookPiece(new Position(7, 0), ChessPieceColor.WHITE), ChessPieceKey.WHITE_QUEEN_ROOK));

      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(0, 1), ChessPieceColor.WHITE), ChessPieceKey.WHITE_KING_ROOK_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(1, 1), ChessPieceColor.WHITE), ChessPieceKey.WHITE_KING_KNIGHT_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(2, 1), ChessPieceColor.WHITE), ChessPieceKey.WHITE_KING_BISHOP_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(3, 1), ChessPieceColor.WHITE), ChessPieceKey.WHITE_KING_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(4, 1), ChessPieceColor.WHITE), ChessPieceKey.WHITE_QUEEN_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(5, 1), ChessPieceColor.WHITE), ChessPieceKey.WHITE_QUEEN_BISHOP_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(6, 1), ChessPieceColor.WHITE), ChessPieceKey.WHITE_QUEEN_KNIGHT_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(7, 1), ChessPieceColor.WHITE), ChessPieceKey.WHITE_QUEEN_ROOK_PAWN));

      chessPieceSet.add(new ChessPieceSetEntry(new RookPiece(new Position(0, 7), ChessPieceColor.BLACK), ChessPieceKey.BLACK_KING_ROOK));
      chessPieceSet.add(new ChessPieceSetEntry(new KnightPiece(new Position(1, 7), ChessPieceColor.BLACK), ChessPieceKey.BLACK_KING_KNIGHT));
      chessPieceSet.add(new ChessPieceSetEntry(new BishopPiece(new Position(2, 7), ChessPieceColor.BLACK), ChessPieceKey.BLACK_KING_BISHOP));
      chessPieceSet.add(new ChessPieceSetEntry(new QueenPiece(new Position(3, 7), ChessPieceColor.BLACK), ChessPieceKey.BLACK_QUEEN));
      chessPieceSet.add(new ChessPieceSetEntry(new KingPiece(new Position(4, 7), ChessPieceColor.BLACK), ChessPieceKey.BLACK_KING));
      chessPieceSet.add(new ChessPieceSetEntry(new BishopPiece(new Position(5, 7), ChessPieceColor.BLACK), ChessPieceKey.BLACK_QUEEN_BISHOP));
      chessPieceSet.add(new ChessPieceSetEntry(new KnightPiece(new Position(6, 7), ChessPieceColor.BLACK), ChessPieceKey.BLACK_QUEEN_KNIGHT));
      chessPieceSet.add(new ChessPieceSetEntry(new RookPiece(new Position(7, 7), ChessPieceColor.BLACK), ChessPieceKey.BLACK_QUEEN_ROOK));

      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(0, 6), ChessPieceColor.BLACK), ChessPieceKey.BLACK_KING_ROOK_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(1, 6), ChessPieceColor.BLACK), ChessPieceKey.BLACK_KING_KNIGHT_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(2, 6), ChessPieceColor.BLACK), ChessPieceKey.BLACK_KING_BISHOP_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(3, 6), ChessPieceColor.BLACK), ChessPieceKey.BLACK_KING_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(4, 6), ChessPieceColor.BLACK), ChessPieceKey.BLACK_QUEEN_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(5, 6), ChessPieceColor.BLACK), ChessPieceKey.BLACK_QUEEN_BISHOP_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(6, 6), ChessPieceColor.BLACK), ChessPieceKey.BLACK_QUEEN_KNIGHT_PAWN));
      chessPieceSet.add(new ChessPieceSetEntry(new PawnPiece(new Position(7, 6), ChessPieceColor.BLACK), ChessPieceKey.BLACK_QUEEN_ROOK_PAWN));
   }

   public static Set<ChessPieceSetEntry> getChessSet()
   {
      return chessPieceSet;
   }
}
