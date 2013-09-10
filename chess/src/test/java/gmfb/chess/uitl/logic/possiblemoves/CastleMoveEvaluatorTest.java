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
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.core.piece.pieces.RookPiece;

import java.util.Collections;

import org.easymock.EasyMock;
import org.junit.Test;

import com.google.common.collect.Sets;

public class CastleMoveEvaluatorTest
{
   private final CastleMoveEvaluator castleMoveEvaluator = new CastleMoveEvaluator();
   private final ChessBoardImpl chessBoard = EasyMock.createMockBuilder(ChessBoardImpl.class)
         .withConstructor()
         .addMockedMethod("hasPieceMoved", ChessPieceKey.class)
         .addMockedMethod("getMovablePositions", ChessPieceColor.class)
         .addMockedMethod("hasPieceMoved", ChessPieceKey.class)
         .addMockedMethod("getPieceByPosition", Position.class)
         .createMock();

   @Test
   public void shouldEvaluateMovePosibilityTrue()
   {
      expect(chessBoard.getPieceByPosition(isA(Position.class))).andReturn(null)
            .anyTimes();
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

   @Test
   public void shouldEvaluateMovePosibilityFalseWrongColor()
   {
      expect(chessBoard.getMovablePositions(isA(ChessPieceColor.class))).andReturn(Collections.<Position> emptySet())
            .anyTimes();

      CastleMove castleMove =
            new CastleMove(new KingPiece(new Position(4, 0), ChessPieceColor.BLACK), new Position(2, 0), new Move(new RookPiece(new Position(0, 0),
                  ChessPieceColor.WHITE), new Position(3, 0)), chessBoard);

      replay(chessBoard);
      assertThat(castleMoveEvaluator.evaluateMovePosibility(castleMove)).isFalse();
      verify(chessBoard);
   }

   @Test
   public void shouldEvaluateMovePosibilityFalseWrongKingPiece()
   {
      expect(chessBoard.getMovablePositions(isA(ChessPieceColor.class))).andReturn(Collections.<Position> emptySet())
            .anyTimes();

      CastleMove castleMove =
            new CastleMove(new PawnPiece(new Position(4, 0), ChessPieceColor.WHITE), new Position(2, 0), new Move(new RookPiece(new Position(0, 0),
                  ChessPieceColor.WHITE), new Position(3, 0)), chessBoard);

      replay(chessBoard);
      assertThat(castleMoveEvaluator.evaluateMovePosibility(castleMove)).isFalse();
      verify(chessBoard);
   }

   @Test
   public void shouldEvaluateMovePosibilityFalseWrongRookPiece()
   {
      expect(chessBoard.getMovablePositions(isA(ChessPieceColor.class))).andReturn(Collections.<Position> emptySet())
            .anyTimes();

      CastleMove castleMove =
            new CastleMove(new KingPiece(new Position(4, 0), ChessPieceColor.WHITE), new Position(2, 0), new Move(new PawnPiece(new Position(0, 0),
                  ChessPieceColor.WHITE), new Position(3, 0)), chessBoard);

      replay(chessBoard);
      assertThat(castleMoveEvaluator.evaluateMovePosibility(castleMove)).isFalse();
      verify(chessBoard);
   }

   @Test
   public void shouldEvaluateMovePosibilityFalseKingHasMoved()
   {
      expect(chessBoard.hasPieceMoved(isNull(ChessPieceKey.class))).andReturn(true);
      expect(chessBoard.getMovablePositions(isA(ChessPieceColor.class))).andReturn(Collections.<Position> emptySet())
            .anyTimes();

      CastleMove castleMove =
            new CastleMove(new KingPiece(new Position(4, 0), ChessPieceColor.WHITE), new Position(2, 0), new Move(new RookPiece(new Position(0, 0),
                  ChessPieceColor.WHITE), new Position(3, 0)), chessBoard);

      replay(chessBoard);
      assertThat(castleMoveEvaluator.evaluateMovePosibility(castleMove)).isFalse();
      verify(chessBoard);
   }

   @Test
   public void shouldEvaluateMovePosibilityFalseRookHasMoved()
   {
      expect(chessBoard.hasPieceMoved(isNull(ChessPieceKey.class))).andReturn(false);
      expect(chessBoard.hasPieceMoved(isNull(ChessPieceKey.class))).andReturn(true);
      expect(chessBoard.getMovablePositions(isA(ChessPieceColor.class))).andReturn(Collections.<Position> emptySet())
            .anyTimes();

      CastleMove castleMove =
            new CastleMove(new KingPiece(new Position(4, 0), ChessPieceColor.WHITE), new Position(2, 0), new Move(new RookPiece(new Position(0, 0),
                  ChessPieceColor.WHITE), new Position(3, 0)), chessBoard);

      replay(chessBoard);
      assertThat(castleMoveEvaluator.evaluateMovePosibility(castleMove)).isFalse();
      verify(chessBoard);
   }

   @Test
   public void shouldEvaluateMovePosibilityFalseNotSafe()
   {
      expect(chessBoard.hasPieceMoved(isNull(ChessPieceKey.class))).andReturn(false)
            .times(2);
      expect(chessBoard.getMovablePositions(isA(ChessPieceColor.class))).andReturn(Sets.newHashSet(new Position(3, 0)))
            .anyTimes();

      CastleMove castleMove =
            new CastleMove(new KingPiece(new Position(4, 0), ChessPieceColor.WHITE), new Position(2, 0), new Move(new RookPiece(new Position(0, 0),
                  ChessPieceColor.WHITE), new Position(3, 0)), chessBoard);

      replay(chessBoard);
      assertThat(castleMoveEvaluator.evaluateMovePosibility(castleMove)).isFalse();
      verify(chessBoard);
   }

   @Test
   public void shouldEvaluateMovePosibilityFalseWrongKingMove()
   {
      expect(chessBoard.hasPieceMoved(isNull(ChessPieceKey.class))).andReturn(false)
            .times(2);
      expect(chessBoard.getMovablePositions(isA(ChessPieceColor.class))).andReturn(Collections.<Position> emptySet())
            .anyTimes();

      CastleMove castleMove =
            new CastleMove(new KingPiece(new Position(4, 0), ChessPieceColor.WHITE), new Position(3, 0), new Move(new RookPiece(new Position(0, 0),
                  ChessPieceColor.WHITE), new Position(3, 0)), chessBoard);

      replay(chessBoard);
      assertThat(castleMoveEvaluator.evaluateMovePosibility(castleMove)).isFalse();
      verify(chessBoard);
   }

   @Test
   public void shouldEvaluateMovePosibilityFalseWrongRookMove()
   {
      expect(chessBoard.hasPieceMoved(isNull(ChessPieceKey.class))).andReturn(false)
            .times(2);
      expect(chessBoard.getMovablePositions(isA(ChessPieceColor.class))).andReturn(Collections.<Position> emptySet())
            .anyTimes();

      CastleMove castleMove =
            new CastleMove(new KingPiece(new Position(4, 0), ChessPieceColor.WHITE), new Position(2, 0), new Move(new RookPiece(new Position(0, 0),
                  ChessPieceColor.WHITE), new Position(3, 1)), chessBoard);

      replay(chessBoard);
      assertThat(castleMoveEvaluator.evaluateMovePosibility(castleMove)).isFalse();
      verify(chessBoard);
   }

   @Test
   public void shouldEvaluateMovePosibilityFalseNoPath()
   {
      expect(chessBoard.getPieceByPosition(isA(Position.class))).andReturn(new PawnPiece(null, null));
      expect(chessBoard.hasPieceMoved(isNull(ChessPieceKey.class))).andReturn(false)
            .times(2);
      expect(chessBoard.getMovablePositions(isA(ChessPieceColor.class))).andReturn(Collections.<Position> emptySet())
            .anyTimes();

      CastleMove castleMove =
            new CastleMove(new KingPiece(new Position(4, 0), ChessPieceColor.WHITE), new Position(2, 0), new Move(new RookPiece(new Position(0, 0),
                  ChessPieceColor.WHITE), new Position(3, 0)), chessBoard);

      replay(chessBoard);
      assertThat(castleMoveEvaluator.evaluateMovePosibility(castleMove)).isFalse();
      verify(chessBoard);
   }
}
