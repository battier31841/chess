package gmfb.chess.uitl.logic.possiblemoves;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.isNull;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.fest.assertions.Assertions.assertThat;
import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoardImpl;
import gmfb.chess.core.move.CastleMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.ChessPieceKey;
import gmfb.chess.core.piece.pieces.KingPiece;
import gmfb.chess.core.piece.pieces.RookPiece;

import java.util.Collections;

import org.easymock.EasyMock;
import org.junit.Test;

public class CastleMoveEvaluatorTest
{
   private CastleMoveEvaluator castleMoveEvaluator = new CastleMoveEvaluator();
   private ChessBoardImpl chessBoard = EasyMock.createMockBuilder(ChessBoardImpl.class)
         .withConstructor()
         .addMockedMethod("hasPieceMoved", ChessPieceKey.class)
         .addMockedMethod("getMovablePositions", ChessPieceColor.class)
         .createMock();

   @Test
   public void shouldEvaluateMovePosibilityTrue()
   {
      expect(chessBoard.hasPieceMoved(isNull(ChessPieceKey.class))).andReturn(false)
            .times(2);
      expect(chessBoard.getMovablePositions(isA(ChessPieceColor.class))).andReturn(Collections.<Position> emptySet())
            .anyTimes();

      CastleMove castleMove =
            new CastleMove(new KingPiece(new Position(4, 0), ChessPieceColor.WHITE), new Position(2, 0), new Move(new RookPiece(new Position(0, 0),
                  ChessPieceColor.WHITE), new Position(3, 0)), chessBoard);

      replay(chessBoard);
      assertThat(castleMoveEvaluator.evaluateMovePosibility(castleMove)).isTrue();
      verify(chessBoard);
   }
}
