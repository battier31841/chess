package gmfb.chess.uitl.logic.possiblemoves;

import gmfb.chess.core.Position;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Sets;

public class CastleMovePosibilities
{
   private static Map<CastleMovePosibilitiesKey, CastleMovePosibilitiesValue> castleMovePosibilities =
         new HashMap<CastleMovePosibilitiesKey, CastleMovePosibilitiesValue>();
   static
   {
      castleMovePosibilities.put(
            new CastleMovePosibilitiesKey(new Position(4, 0), new Position(0, 0)),
            new CastleMovePosibilitiesValue(Sets.newHashSet(new Position(2, 0), new Position(3, 0), new Position(4, 0)), Sets.newHashSet(
                  new Position(1, 0), new Position(2, 0), new Position(3, 0)), new Position(2, 0), new Position(3, 0)));
      castleMovePosibilities.put(
            new CastleMovePosibilitiesKey(new Position(4, 0), new Position(7, 0)),
            new CastleMovePosibilitiesValue(Sets.newHashSet(new Position(4, 0), new Position(5, 0), new Position(6, 0)), Sets.newHashSet(
                  new Position(5, 0), new Position(6, 0)), new Position(6, 0), new Position(5, 0)));

      castleMovePosibilities.put(
            new CastleMovePosibilitiesKey(new Position(4, 7), new Position(0, 7)),
            new CastleMovePosibilitiesValue(Sets.newHashSet(new Position(2, 7), new Position(3, 7), new Position(4, 7)), Sets.newHashSet(
                  new Position(1, 7), new Position(2, 7), new Position(3, 7)), new Position(2, 7), new Position(3, 7)));
      castleMovePosibilities.put(
            new CastleMovePosibilitiesKey(new Position(4, 7), new Position(7, 7)),
            new CastleMovePosibilitiesValue(Sets.newHashSet(new Position(4, 7), new Position(5, 7), new Position(6, 7)), Sets.newHashSet(
                  new Position(5, 7), new Position(6, 7)), new Position(6, 7), new Position(5, 7)));
   }

   public static CastleMovePosibilitiesValue getCastleMovePosibility(Position kingPosition, Position rookPosition)
   {
      return castleMovePosibilities.get(new CastleMovePosibilitiesKey(kingPosition, rookPosition));
   }
}
