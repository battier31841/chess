package gmfb.chess.core.board;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;
import static org.fest.assertions.Assertions.assertThat;
import gmfb.chess.core.Position;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.ChessPieceKey;
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.core.piece.pieces.RookPiece;
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
import gmfb.chess.uitl.logic.handlemoves.MoveHandler;
import gmfb.chess.uitl.validation.ValidMoveValidator;

import java.util.Map;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

public class ChessBoardImplTest
{
   private static final Position POSITION = new Position(0, 0);
   private static final Position NEW_POSITION = new Position(0, 1);
   private static final ChessPiece CHESS_PIECE = new PawnPiece(POSITION, ChessPieceColor.WHITE);
   private static final Move MOVE = new Move(CHESS_PIECE, NEW_POSITION);

   private final ChessBoardImpl chessBoard = new ChessBoardImpl();
   private final IMocksControl mocksControl = createControl();
   private final BoardEvaluator boardEvaluator = mocksControl.createMock(BoardEvaluatorImpl.class);
   private final ValidMoveValidator validMoveValidator = mocksControl.createMock(ValidMoveValidator.class);
   private final MoveHandler moveHandler = mocksControl.createMock(MoveHandler.class);

   @Before
   public void setUp()
   {
      chessBoard.setMoveHandler(moveHandler);
      chessBoard.setValidMoveValidator(validMoveValidator);
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
   public void shouldGetLastMove() throws PositionOutOfBoundsException, IllegalMoveException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      validMoveValidator.validateMoveValidity(isA(ChessBoard.class), isA(Move.class));
      expectLastCall();
      moveHandler.handleMove(isA(ChessBoard.class), isA(Move.class));
      expectLastCall();

      mocksControl.replay();
      chessBoard.handleMove(new Move(CHESS_PIECE, POSITION));
      Move lastMove = chessBoard.getlastMove();
      mocksControl.verify();

      assertThat(lastMove.getTo()).isEqualTo(POSITION);
      assertThat(lastMove.getPiece()
            .getColor()).isEqualTo(CHESS_PIECE.getColor());
      assertThat(lastMove.getPiece()
            .getCurrentPostion()).isEqualTo(CHESS_PIECE.getCurrentPostion());
   }

   @Test
   public void shouldGetMoviablePositons()
   {
      chessBoard.getMovablePositions(ChessPieceColor.WHITE);
   }

   @Test
   public void shouldGetPieceByPosition()
   {
      Position whiteKingRookPosition = new Position(0, 0);
      ChessPiece whiteKingRookPiece = chessBoard.getPieceByPosition(whiteKingRookPosition);

      assertThat(whiteKingRookPiece.getClass()).isEqualTo(RookPiece.class);
      assertThat(whiteKingRookPiece.getColor()).isEqualTo(ChessPieceColor.WHITE);
      assertThat(whiteKingRookPiece.getCurrentPostion()).isEqualTo(whiteKingRookPosition);

      assertThat(chessBoard.getPieceByPosition(null)).isNull();
   }

   @Test
   public void shouldGetHasPieceMoved() throws PositionOutOfBoundsException, IllegalMoveException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      ChessPieceKey key = ChessPieceKey.BLACK_KING;
      assertThat(chessBoard.hasPieceMoved(key)).isFalse();

      validMoveValidator.validateMoveValidity(isA(ChessBoard.class), isA(Move.class));
      expectLastCall();
      moveHandler.handleMove(isA(ChessBoard.class), isA(Move.class));
      expectLastCall();

      mocksControl.replay();
      chessBoard.handleMove(new Move(chessBoard.getPiece(key), POSITION));
      mocksControl.verify();
      assertThat(chessBoard.hasPieceMoved(key)).isTrue();
   }
}
