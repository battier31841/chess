package gmfb.chess.uitl.logic.possiblemoves;

import gmfb.chess.core.Position;

public class CastleMovePosibilitiesKey
{
   private Position kingPosition;
   private Position rookPosition;

   public CastleMovePosibilitiesKey(Position kingPosition, Position rookPosition)
   {
      this.kingPosition = kingPosition;
      this.rookPosition = rookPosition;
   }

   public Position getKingPosition()
   {
      return kingPosition;
   }

   public Position getRookPosition()
   {
      return rookPosition;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof CastleMovePosibilitiesKey)
      {
         CastleMovePosibilitiesKey that = (CastleMovePosibilitiesKey) obj;
         return (this.kingPosition.equals(that.getKingPosition())) && (this.rookPosition.equals(that.getRookPosition()));
      }
      return false;
   }

   @Override
   public int hashCode()
   {
      return (kingPosition.hashCode() * 100) + rookPosition.hashCode();
   }
}
