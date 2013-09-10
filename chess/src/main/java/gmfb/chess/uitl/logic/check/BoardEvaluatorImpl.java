package gmfb.chess.uitl.logic.check;

import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.ChessPieceKey;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.BiMap;

public class BoardEvaluatorImpl implements BoardEvaluator
{
   public boolean isInCheck(ChessBoard chessBoard, ChessPieceColor color)
   {
      Position currentKingPosition = getKingPiece(chessBoard, color).getCurrentPostion();
      Set<Move> allPossibleMovesForOtherColor = getAllPossibleMovesForOtherColor(chessBoard, color);
      for (Move move : allPossibleMovesForOtherColor)
      {
         if (move.getTo()
               .equals(currentKingPosition))
         {
            return true;
         }
      }
      return false;
   }

   // TODO
   public boolean isInCheckMate(ChessBoard chessBoard, ChessPieceColor color)
   {
      return false;
   }

   private Set<Move> getAllPossibleMovesForOtherColor(ChessBoard chessBoard, ChessPieceColor color)
   {
      if (color.equals(ChessPieceColor.WHITE))
      {
         return getAllpossibleMovesForColor(chessBoard, ChessPieceColor.WHITE);
      }
      return getAllpossibleMovesForColor(chessBoard, ChessPieceColor.BLACK);
   }

   private Set<Move> getAllpossibleMovesForColor(ChessBoard chessBoard, ChessPieceColor color)
   {
      Set<Move> moves = new HashSet<Move>();
      Set<ChessPieceKey> allKeysForColor = ChessPieceKey.getAllKeysForColor(color);
      BiMap<ChessPieceKey, ChessPiece> chesspieces = chessBoard.getPieces();
      for (ChessPieceKey key : allKeysForColor)
      {
         if (chesspieces.containsKey(key))
         {
            Set<Move> allMovesForKeyPiece = chesspieces.get(key)
                  .getPossibleMoves(chessBoard);
            for (Move move : allMovesForKeyPiece)
            {
               moves.add(move);
            }
         }
      }
      return moves;
   }

   private ChessPiece getKingPiece(ChessBoard chessBoard, ChessPieceColor color)
   {
      return chessBoard.getPieces()
            .get(ChessPieceKey.getKingKey(color));
   }
}
