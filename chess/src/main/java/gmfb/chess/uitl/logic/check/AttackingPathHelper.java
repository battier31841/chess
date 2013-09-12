package gmfb.chess.uitl.logic.check;

import gmfb.chess.core.Position;

import java.util.HashSet;
import java.util.Set;

public class AttackingPathHelper
{
   public Set<Position> buildPath(Position attackiePosition, Position attackerPosition)
   {
      validatePositions(attackiePosition, attackerPosition);

      int attackieX = attackiePosition.getxPosition();
      int attackerX = attackerPosition.getxPosition();
      int attackieY = attackiePosition.getyPosition();
      int attackerY = attackerPosition.getyPosition();

      int dX = Integer.signum(attackieX - attackerX);
      int dY = Integer.signum(attackieY - attackerY);

      Set<Position> path = new HashSet<Position>();

      int i = 1;
      while (true)
      {
         Position tempPosition = new Position(attackerX + i * dX, attackerY + i * dY);
         if (attackiePosition.equals(tempPosition))
         {
            break;
         }
         path.add(tempPosition);
         i++;
      }

      return path;
   }

   private void validatePositions(Position attackiePosition, Position attackerPosition)
   {
      int attackieX = attackiePosition.getxPosition();
      int attackerX = attackerPosition.getxPosition();
      int attackieY = attackiePosition.getyPosition();
      int attackerY = attackerPosition.getyPosition();

      if (!((attackieX - attackerX == 0) || (attackieY - attackerY == 0) || ((attackieX - attackerX == attackieY - attackerY))
            || (attackieX + attackerY == attackerX + attackieY) || (attackieX + attackerX == attackerY + attackieY)))
      {
         throw new IllegalStateException();
      }
   }
}
