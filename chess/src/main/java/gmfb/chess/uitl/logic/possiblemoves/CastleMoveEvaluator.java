package gmfb.chess.uitl.logic.possiblemoves;

import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.CastleMove;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.pieces.KingPiece;
import gmfb.chess.core.piece.pieces.RookPiece;
import gmfb.chess.uitl.logic.EnemyColorRetriever;

import java.util.Set;

public class CastleMoveEvaluator
{
   private ChessPiece kingPiece;
   private ChessPiece rookPiece;
   private ChessBoard chessBoard;
   private CastleMovePosibilitiesValue castleMoves;
   private EnemyColorRetriever enemyColorRetriever = new EnemyColorRetriever();

   public boolean evaluateMovePosibility(CastleMove castleMove)
   {
      kingPiece = castleMove.getPiece();
      rookPiece = castleMove.getRookMove()
            .getPiece();
      chessBoard = castleMove.getChessBoard();
      castleMoves = CastleMovePosibilities.getCastleMovePosibility(kingPiece.getCurrentPostion(), rookPiece.getCurrentPostion());

      return areMovingPiecesSameColors() && isCorrectPieces() && hasNotMovedYet() && isSafeMove() && areCorrectMoves(castleMove) && isClearPath();
   }

   private boolean areMovingPiecesSameColors()
   {
      if (!kingPiece.getColor()
            .equals(rookPiece.getColor()))
      {
         return false;
      }
      return true;
   }

   private boolean isCorrectPieces()
   {
      if ((kingPiece instanceof KingPiece) && (rookPiece instanceof RookPiece))
      {
         return true;
      }
      return false;
   }

   private boolean hasNotMovedYet()
   {
      if ((chessBoard.hasPieceMoved(chessBoard.getKey(kingPiece))) || (chessBoard.hasPieceMoved(chessBoard.getKey(rookPiece))))
      {
         return false;
      }
      return true;
   }

   private boolean isSafeMove()
   {
      Set<Position> kingMovePositions = castleMoves.getKingMovePositions();
      for (Position position : kingMovePositions)
      {
         if (chessBoard.getMovablePositions(enemyColorRetriever.getEnemyColor(kingPiece.getColor()))
               .contains(position))
         {
            return false;
         }
      }
      return true;
   }

   private boolean areCorrectMoves(CastleMove castleMove)
   {
      if (castleMoves != null)
      {
         if ((castleMoves.getKingTo().equals(castleMove.getTo())) && (castleMoves.getRookTo().equals(castleMove.getRookMove()
               .getTo())))
         {
            return true;
         }
      }
      return false;
   }

   private boolean isClearPath()
   {
      Set<Position> positionsBetweenPieces = castleMoves.getPositionsBetweenPieces();
      for (Position position : positionsBetweenPieces)
      {
         if (chessBoard.getPieceByPosition(position) != null)
         {
            return false;
         }
      }
      return true;
   }
}
