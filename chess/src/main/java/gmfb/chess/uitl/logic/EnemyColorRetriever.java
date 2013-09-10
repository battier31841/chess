package gmfb.chess.uitl.logic;

import gmfb.chess.core.piece.ChessPieceColor;

public class EnemyColorRetriever
{
   public static ChessPieceColor getEnemyColor(ChessPieceColor color)
   {
      if (color.equals(ChessPieceColor.WHITE))
      {
         return ChessPieceColor.BLACK;
      }
      return ChessPieceColor.WHITE;
   }
}
