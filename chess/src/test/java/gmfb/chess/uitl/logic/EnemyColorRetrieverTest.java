package gmfb.chess.uitl.logic;

import static org.fest.assertions.Assertions.assertThat;
import gmfb.chess.core.piece.ChessPieceColor;

import org.junit.Test;

public class EnemyColorRetrieverTest
{
   @Test
   public void shouldGetWhite()
   {
      assertThat(EnemyColorRetriever.getEnemyColor(ChessPieceColor.BLACK)).isEqualTo(ChessPieceColor.WHITE);
   }

   @Test
   public void shouldGetBlack()
   {
      assertThat(EnemyColorRetriever.getEnemyColor(ChessPieceColor.WHITE)).isEqualTo(ChessPieceColor.BLACK);
   }
}
