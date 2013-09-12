package gmfb.chess.uitl.logic.check;

import static org.fest.assertions.Assertions.assertThat;
import gmfb.chess.core.Position;

import java.util.Set;

import org.junit.Test;

public class AttackingPathHelperTest
{
   private final AttackingPathHelper attackingPathHelper = new AttackingPathHelper();

   @Test
   public void shouldGetVerticalPathDown()
   {
      Set<Position> path = attackingPathHelper.buildPath(new Position(0, 0), new Position(0, 7));

      assertThat(path.size()).isEqualTo(6);
      for (int i = 1; i < 7; i++)
      {
         assertThat(path).contains(new Position(0, i));
      }
   }

   @Test
   public void shouldGetVerticalPathUp()
   {
      Set<Position> path = attackingPathHelper.buildPath(new Position(0, 7), new Position(0, 0));

      assertThat(path.size()).isEqualTo(6);
      for (int i = 1; i < 7; i++)
      {
         assertThat(path).contains(new Position(0, i));
      }
   }

   @Test
   public void shouldGetHorizontalPathLeft()
   {
      Set<Position> path = attackingPathHelper.buildPath(new Position(0, 0), new Position(7, 0));

      assertThat(path.size()).isEqualTo(6);
      for (int i = 1; i < 7; i++)
      {
         assertThat(path).contains(new Position(i, 0));
      }
   }

   @Test
   public void shouldGetHorizontalPathRight()
   {
      Set<Position> path = attackingPathHelper.buildPath(new Position(7, 0), new Position(0, 0));

      assertThat(path.size()).isEqualTo(6);
      for (int i = 1; i < 7; i++)
      {
         assertThat(path).contains(new Position(i, 0));
      }
   }

   @Test
   public void shouldGetDiagonalPathLeftDown()
   {
      Set<Position> path = attackingPathHelper.buildPath(new Position(0, 0), new Position(7, 7));

      assertThat(path.size()).isEqualTo(6);
      for (int i = 1; i < 7; i++)
      {
         assertThat(path).contains(new Position(i, i));
      }
   }

   @Test
   public void shouldGetDiagonalPatRightUp()
   {
      Set<Position> path = attackingPathHelper.buildPath(new Position(7, 7), new Position(0, 0));

      assertThat(path.size()).isEqualTo(6);
      for (int i = 1; i < 7; i++)
      {
         assertThat(path).contains(new Position(i, i));
      }
   }

   @Test
   public void shouldGetDiagonalPatLeftUp()
   {
      Set<Position> path = attackingPathHelper.buildPath(new Position(7, 0), new Position(0, 7));

      assertThat(path.size()).isEqualTo(6);
      for (int i = 1; i < 7; i++)
      {
         assertThat(path).contains(new Position(i, 7 - i));
      }
   }

   @Test
   public void shouldGetDiagonalPatDownRight()
   {
      Set<Position> path = attackingPathHelper.buildPath(new Position(0, 7), new Position(7, 0));

      assertThat(path.size()).isEqualTo(6);
      for (int i = 1; i < 7; i++)
      {
         assertThat(path).contains(new Position(i, 7 - i));
      }
   }
}
