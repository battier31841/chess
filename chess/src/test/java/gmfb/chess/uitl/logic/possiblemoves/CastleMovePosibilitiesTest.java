package gmfb.chess.uitl.logic.possiblemoves;

import static org.fest.assertions.Assertions.assertThat;
import gmfb.chess.core.Position;

import org.junit.Test;

public class CastleMovePosibilitiesTest
{
   @Test
   public void shouldGetCastlePosssbilities()
   {
      assertThat(CastleMovePosibilities.getCastleMovePosibility(new Position(4, 0), new Position(0, 0))).isNotNull();
      assertThat(CastleMovePosibilities.getCastleMovePosibility(new Position(4, 0), new Position(7, 0))).isNotNull();
      assertThat(CastleMovePosibilities.getCastleMovePosibility(new Position(4, 7), new Position(0, 7))).isNotNull();
      assertThat(CastleMovePosibilities.getCastleMovePosibility(new Position(4, 7), new Position(7, 7))).isNotNull();
   }
}
