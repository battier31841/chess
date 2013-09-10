package gmfb.chess.uitl.logic.check;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.fest.assertions.Assertions.assertThat;
import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoardImpl;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.ChessPieceKey;

import java.util.Collections;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;

public class BoardEvaluatorImplTest
{
   private final BoardEvaluatorImpl boardEvaluator = new BoardEvaluatorImpl();
   private ChessBoardImpl chessBoard = EasyMock.createMockBuilder(ChessBoardImpl.class)
         .withConstructor()
         .addMockedMethod("getMovablePositions", ChessPieceColor.class)
         .createMock();

   @Before
   public void setUp()
   {
      chessBoard.buildNewGame();
   }

   @Test
   public void shouldBeInCheck()
   {
      expect(chessBoard.getMovablePositions(ChessPieceColor.BLACK)).andReturn(Sets.newHashSet(chessBoard.getPiece(ChessPieceKey.WHITE_KING)
            .getCurrentPostion()));

      replay(chessBoard);
      assertThat(boardEvaluator.isInCheck(chessBoard, ChessPieceColor.WHITE)).isTrue();
      verify(chessBoard);
   }

   @Test
   public void shouldNotBeInCheck()
   {
      expect(chessBoard.getMovablePositions(ChessPieceColor.BLACK)).andReturn(Collections.<Position> emptySet());

      replay(chessBoard);
      assertThat(boardEvaluator.isInCheck(chessBoard, ChessPieceColor.WHITE)).isFalse();
      verify(chessBoard);
   }
}
