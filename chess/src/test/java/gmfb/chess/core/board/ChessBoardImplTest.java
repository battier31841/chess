package gmfb.chess.core.board;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;
import gmfb.chess.core.Position;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.ChessPieceKey;
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.core.printer.BoardPrinterImpl;
import gmfb.chess.uitl.exception.IllegalMoveException;
import gmfb.chess.uitl.exception.InvalidCastleMoveException;
import gmfb.chess.uitl.exception.InvalidEnPassentMoveException;
import gmfb.chess.uitl.exception.InvalidKillingMoveException;
import gmfb.chess.uitl.exception.InvalidMoveException;
import gmfb.chess.uitl.exception.InvalidPawnPromotionMoveException;
import gmfb.chess.uitl.exception.PositionOutOfBoundsException;
import gmfb.chess.uitl.logic.check.BoardEvaluator;
import gmfb.chess.uitl.logic.check.BoardEvaluatorImpl;
import gmfb.chess.uitl.logic.handlemoves.MoveHandlerImpl;
import gmfb.chess.uitl.validation.ValidMoveValidator;

import java.util.Map;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ChessBoardImplTest
{
   private static final Position POSITION = new Position(0, 0);
   private static final Position NEW_POSITION = new Position(0, 1);
   private static final ChessPiece CHESS_PIECE = new PawnPiece(POSITION, ChessPieceColor.WHITE);
   private static final Move MOVE = new Move(CHESS_PIECE, NEW_POSITION);

   private ChessBoardImpl chessBoard = new ChessBoardImpl();
   private IMocksControl mocksControl = createControl();
   private BoardEvaluator boardEvaluator = mocksControl.createMock(BoardEvaluatorImpl.class);

   @Before
   public void setUp()
   {
      chessBoard.setMoveHandler(new MoveHandlerImpl());
      chessBoard.setValidMoveValidator(new ValidMoveValidator());
      chessBoard.setBoardEvaluator(boardEvaluator);
      chessBoard.buildNewGame();
   }

   @Test
   public void shouldBuildNewGame()
   {
      Map<ChessPieceKey, ChessPiece> pieces = chessBoard.buildNewGame()
            .getPieces();

      for (ChessPieceKey key : ChessPieceKey.values())
      {
         assertThat(pieces.get(key)).isNotNull();
      }
      assertThat(pieces.size()).isEqualTo(32);
   }

   @Test
   public void shouldHandleMove() throws PositionOutOfBoundsException, IllegalMoveException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      new ChessBoardImpl().handleMove(MOVE);
   }

   @Test
   public void shouldPrintBoard()
   {
      chessBoard.printCurrentBoard(new BoardPrinterImpl());
   }

   @Test
   public void shouldBeInCheck()
   {
      ChessPieceColor white = ChessPieceColor.WHITE;
      expect(boardEvaluator.isInCheck(chessBoard, white)).andReturn(true);

      mocksControl.replay();
      assertThat(chessBoard.isInCheck(white)).isTrue();
      mocksControl.verify();
   }

   @Test
   public void shouldNotBeInCheck()
   {
      ChessPieceColor white = ChessPieceColor.WHITE;
      expect(boardEvaluator.isInCheck(chessBoard, white)).andReturn(false);

      mocksControl.replay();
      assertThat(chessBoard.isInCheck(white)).isFalse();
      mocksControl.verify();
   }

   @Test
   public void shouldBeInCheckMate()
   {
      ChessPieceColor white = ChessPieceColor.WHITE;
      expect(boardEvaluator.isInCheckMate(chessBoard, white)).andReturn(true);

      mocksControl.replay();
      assertThat(chessBoard.isInCheckMate(white)).isTrue();
      mocksControl.verify();
   }

   @Test
   public void shouldNotBeInCheckMate()
   {
      ChessPieceColor white = ChessPieceColor.WHITE;
      expect(boardEvaluator.isInCheckMate(chessBoard, white)).andReturn(false);

      mocksControl.replay();
      assertThat(chessBoard.isInCheckMate(white)).isFalse();
      mocksControl.verify();
   }

   @Test
   @Ignore
   // must have moving ability
         public
         void shouldUpdateHasMoved() throws PositionOutOfBoundsException, IllegalMoveException, InvalidMoveException, InvalidKillingMoveException,
               InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      ChessBoardImpl chessBoard = new ChessBoardImpl();
      chessBoard.buildNewGame();
      chessBoard.setMoveHandler(new MoveHandlerImpl());
      ChessPieceKey key = ChessPieceKey.WHITE_KING_BISHOP_PAWN;
      assertThat(chessBoard.hasPieceMoved(key)).isFalse();
      chessBoard.handleMove(new Move(chessBoard.getPiece(key), new Position(3, 2)));
      assertThat(chessBoard.hasPieceMoved(key)).isTrue();
   }

   @Test
   @Ignore
   // must have moving ability
         public
         void shoudGetlastMove() throws PositionOutOfBoundsException, IllegalMoveException, InvalidMoveException, InvalidKillingMoveException,
               InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      ChessBoardImpl chessBoard = new ChessBoardImpl();
      chessBoard.buildNewGame();
      chessBoard.setMoveHandler(new MoveHandlerImpl());
      ChessPieceKey key = ChessPieceKey.WHITE_KING_BISHOP_PAWN;
      assertThat(chessBoard.hasPieceMoved(key)).isFalse();
      Move move = new Move(chessBoard.getPiece(key), new Position(3, 2));
      chessBoard.handleMove(move);
      assertThat(chessBoard.getlastMove()).isEqualTo(move);

   }
}
