package gmfb.chess.uitl.logic.possiblemoves;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.fest.assertions.Assertions.assertThat;
import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoardImpl;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.pieces.PawnPiece;

import org.easymock.EasyMock;
import org.junit.Test;

public class PossibleMoveHelperTest
{
   private static final Position POSITION = new Position(0, 0);
   private static final Position INVALID_POSITION = new Position(8, 8);
   private final PossibleMoveHelper possibleMoveHelper = new PossibleMoveHelper();
   private final ChessBoardImpl chessBoard = EasyMock.createMockBuilder(ChessBoardImpl.class)
         .withConstructor()
         .addMockedMethod("getPieceByPosition", Position.class)
         .createMock();

   @Test
   public void shouldGetValidEmptyPosition()
   {
      expect(chessBoard.getPieceByPosition(POSITION)).andReturn(null);

      replay(chessBoard);
      assertThat(possibleMoveHelper.isVaidEmptyPosition(POSITION, chessBoard)).isTrue();
      verify(chessBoard);
   }

   @Test
   public void shouldNotGetValidEmptyPosition()
   {
      expect(chessBoard.getPieceByPosition(POSITION)).andReturn(new PawnPiece(null, null));

      replay(chessBoard);
      assertThat(possibleMoveHelper.isVaidEmptyPosition(POSITION, chessBoard)).isFalse();
      verify(chessBoard);
   }

   @Test
   public void shouldNotGetInValidEmptyPosition()
   {
      assertThat(possibleMoveHelper.isVaidEmptyPosition(INVALID_POSITION, chessBoard)).isFalse();
   }

   @Test
   public void shouldGetValidEnemyPosition()
   {
      expect(chessBoard.getPieceByPosition(POSITION)).andReturn(new PawnPiece(POSITION, ChessPieceColor.BLACK));

      replay(chessBoard);
      assertThat(possibleMoveHelper.isValidEnemyPosition(POSITION, ChessPieceColor.WHITE, chessBoard)).isTrue();
      verify(chessBoard);
   }

   @Test
   public void shouldNotGetValidEnemyPositionWrongColor()
   {
      expect(chessBoard.getPieceByPosition(POSITION)).andReturn(new PawnPiece(POSITION, ChessPieceColor.WHITE));

      replay(chessBoard);
      assertThat(possibleMoveHelper.isValidEnemyPosition(POSITION, ChessPieceColor.WHITE, chessBoard)).isFalse();
      verify(chessBoard);
   }

   @Test
   public void shouldNotGetValidEnemyPositionNoPiece()
   {
      expect(chessBoard.getPieceByPosition(POSITION)).andReturn(null);

      replay(chessBoard);
      assertThat(possibleMoveHelper.isValidEnemyPosition(POSITION, ChessPieceColor.WHITE, chessBoard)).isFalse();
      verify(chessBoard);
   }

   @Test
   public void shouldNotGetInValidEnemyPosition()
   {
      assertThat(possibleMoveHelper.isValidEnemyPosition(INVALID_POSITION, ChessPieceColor.WHITE, chessBoard)).isFalse();
   }

}
