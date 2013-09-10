package gmfb.chess.uitl.logic.possiblemoves;

import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.uitl.logic.EnemyColorRetriever;

import java.util.HashSet;
import java.util.Set;

public class PossibleMoveHelper
{
   public boolean isVaidEmptyPosition(Position position, ChessBoard chessBoard)
   {
      if (Position.isValid(position))
      {
         if (chessBoard.getPieceByPosition(position) == null)
         {
            return true;
         }
      }
      return false;
   }

   public boolean isValidEnemyPosition(Position position, ChessPieceColor color, ChessBoard chessBoard)
   {
      return isValidPositionOfColor(position, EnemyColorRetriever.getEnemyColor(color), chessBoard);
   }

   public Set<Position> getMovablePath(Position start, int dX, int dY, ChessPieceColor color, ChessBoard chessBoard)
   {
      Set<Position> path = new HashSet<Position>();
      for (int i = 1;; i++)
      {
         Position currentPosition = getNewCurrentPosition(start, dX, dY, i);
         if (!Position.isValid(currentPosition) || isValidPositionOfColor(currentPosition, color, chessBoard))
         {
            break;
         }
         path.add(currentPosition);
         if (isValidEnemyPosition(currentPosition, color, chessBoard))
         {
            break;
         }
      }
      return path;
   }

   private Position getNewCurrentPosition(Position start, int dX, int dY, int index)
   {
      return new Position(start.getxPosition() + (index * dX), start.getyPosition() + (index * dY));
   }

   private boolean isValidPositionOfColor(Position position, ChessPieceColor color, ChessBoard chessBoard)
   {
      if (Position.isValid(position))
      {
         ChessPiece chessPiece = chessBoard.getPieceByPosition(position);
         if (chessPiece != null)
         {
            return chessPiece.getColor()
                  .equals(color);
         }
      }
      return false;
   }
}
