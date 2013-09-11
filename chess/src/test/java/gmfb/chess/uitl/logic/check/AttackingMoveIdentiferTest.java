package gmfb.chess.uitl.logic.check;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.fest.assertions.Assertions.assertThat;
import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoardImpl;
import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.pieces.PawnPiece;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.easymock.EasyMock;
import org.junit.Test;

public class AttackingMoveIdentiferTest
{
   private static final Position POSITION = new Position(0, 0);
   private static final ChessPiece CHESS_PIECE = new PawnPiece(POSITION, ChessPieceColor.WHITE);

   private final AttackingMoveIdentifer attackingMoveIdentifer = new AttackingMoveIdentifer();
   private final ChessBoardImpl chessBoard = EasyMock.createMockBuilder(ChessBoardImpl.class)
         .withConstructor()
         .addMockedMethod("getMovesForColor", ChessPieceColor.class)
         .createMock();

   @Test
   public void shouldGetAttackingMoves()
   {
      expect(chessBoard.getMovesForColor(isA(ChessPieceColor.class))).andReturn(buildKillingMoveSet());

      replay(chessBoard);
      Set<KillingMove> attackinhgMoves = attackingMoveIdentifer.getAttackinhgMoves(CHESS_PIECE, chessBoard);
      verify(chessBoard);

      assertThat(attackinhgMoves.size()).isEqualTo(1);
   }

   @Test
   public void shouldGetAttackingNoMoves()
   {
      expect(chessBoard.getMovesForColor(isA(ChessPieceColor.class))).andReturn(Collections.<Move> emptySet());

      replay(chessBoard);
      Set<KillingMove> attackinhgMoves = attackingMoveIdentifer.getAttackinhgMoves(CHESS_PIECE, chessBoard);
      verify(chessBoard);

      assertThat(attackinhgMoves.size()).isEqualTo(0);
   }

   private Set<Move> buildKillingMoveSet()
   {
      Set<Move> killingMoveSet = new HashSet<Move>();
      killingMoveSet.add(new KillingMove(CHESS_PIECE, POSITION, CHESS_PIECE));
      return killingMoveSet;
   }
}