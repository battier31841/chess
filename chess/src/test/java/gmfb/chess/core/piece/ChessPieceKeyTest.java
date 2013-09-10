package gmfb.chess.core.piece;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class ChessPieceKeyTest
{
	@Test
	public void shouldGetAllWhiteKeys()
	{
		assertThat(ChessPieceKey.getAllKeysForColor(ChessPieceColor.WHITE).size()).isEqualTo(16);
	}

	@Test
	public void shouldGetAllBlackKeys()
	{
		assertThat(ChessPieceKey.getAllKeysForColor(ChessPieceColor.BLACK).size()).isEqualTo(16);
	}

	@Test
	public void shouldGetAllWhiteKing()
	{
		assertThat(ChessPieceKey.getKingKey(ChessPieceColor.WHITE)).isEqualTo(ChessPieceKey.WHITE_KING);
	}

	@Test
	public void shouldGetAllBlackKing()
	{
		assertThat(ChessPieceKey.getKingKey(ChessPieceColor.BLACK)).isEqualTo(ChessPieceKey.BLACK_KING);
	}
}
