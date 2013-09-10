package gmfb.chess.uitl.logic.possiblemoves;

import gmfb.chess.core.Position;

import java.util.Set;

public class CastleMovePosibilitiesValue
{
   private Set<Position> kingMovePosiitons;
   private Set<Position> positionsBetweenPieces;
   private Position kingTo;
   private Position rookTo;

   public CastleMovePosibilitiesValue(Set<Position> kingMovePosiitons, Set<Position> positionsBetweenPieces, Position kingTo, Position rookTo)
   {
      this.kingMovePosiitons = kingMovePosiitons;
      this.positionsBetweenPieces = positionsBetweenPieces;
      this.kingTo = kingTo;
      this.rookTo = rookTo;
   }

   public Set<Position> getKingMovePositions()
   {
      return kingMovePosiitons;
   }

   public Set<Position> getPositionsBetweenPieces()
   {
      return positionsBetweenPieces;
   }

   public Position getKingTo()
   {
      return kingTo;
   }

   public Position getRookTo()
   {
      return rookTo;
   }

}