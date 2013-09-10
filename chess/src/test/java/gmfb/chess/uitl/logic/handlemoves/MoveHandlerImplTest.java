package gmfb.chess.uitl.logic.handlemoves;

import static org.fest.assertions.Assertions.assertThat;
import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.board.ChessBoardImpl;
import gmfb.chess.core.move.CastleMove;
import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.move.PawnPromotionMove;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.ChessPieceKey;
import gmfb.chess.core.piece.pieces.QueenPiece;

import org.junit.Test;

public class MoveHandlerImplTest
{
   private static final ChessPieceKey CHESS_PIECE_KEY = ChessPieceKey.WHITE_KING_KNIGHT_PAWN;
   private static final Position REGULAR_MOVE_POSITION = new Position(0, 0);
   private static final ChessPieceKey CHESS_PIECE_KEY_TWO = ChessPieceKey.WHITE_KING;
   private static final Position REGULAR_MOVE_POSITION_TWO = new Position(5, 5);

   private final MoveHandlerImpl moveHandler = new MoveHandlerImpl();

   @Test
   public void shouldHandleRegularMove()
   {
      ChessBoard chessBoard = buildNewBoard();
      moveHandler.handleMove(chessBoard, buildRegularMove(chessBoard));
      assertThat(chessBoard.getPiece(CHESS_PIECE_KEY)
            .getCurrentPostion()).isEqualTo(REGULAR_MOVE_POSITION);
   }

   @Test
   public void shouldHandlePawnPromotionMove()
   {
      ChessBoard chessBoard = buildNewBoard();
      moveHandler.handleMove(chessBoard, buildPawnPromotionMove(chessBoard));
      assertThat(chessBoard.getPiece(CHESS_PIECE_KEY)
            .getClass()).isEqualTo(QueenPiece.class);
   }

   @Test
   public void shouldHandleKillingMove()
   {
      int numOfPiecesBeforeMove = 32;
      int numOfPiecesAfterMove = 31;
      ChessBoard chessBoard = buildNewBoard();

      assertThat(chessBoard.getPieces()
            .size()).isEqualTo(numOfPiecesBeforeMove);
      moveHandler.handleMove(chessBoard, buildKillingMove(chessBoard));
      assertThat(chessBoard.getPieces()
            .size()).isEqualTo(numOfPiecesAfterMove);
   }

   @Test
   public void shouldHandleCastleMove()
   {
      ChessBoard chessBoard = buildNewBoard();
      moveHandler.handleMove(chessBoard, buildCastleMove(chessBoard));
      assertThat(chessBoard.getPiece(CHESS_PIECE_KEY)
            .getCurrentPostion()).isEqualTo(REGULAR_MOVE_POSITION);
      assertThat(chessBoard.getPiece(CHESS_PIECE_KEY_TWO)
            .getCurrentPostion()).isEqualTo(REGULAR_MOVE_POSITION_TWO);
   }

   private Move buildCastleMove(ChessBoard chessBoard)
   {
      return new CastleMove(chessBoard.getPiece(CHESS_PIECE_KEY), REGULAR_MOVE_POSITION, new Move(chessBoard.getPiece(CHESS_PIECE_KEY_TWO),
            REGULAR_MOVE_POSITION_TWO), chessBoard);
   }

   private ChessBoard buildNewBoard()
   {
      return new ChessBoardImpl().buildNewGame();
   }

   private Move buildRegularMove(ChessBoard chessBoard)
   {
      return new Move(chessBoard.getPiece(CHESS_PIECE_KEY), REGULAR_MOVE_POSITION);
   }

   private Move buildKillingMove(ChessBoard chessBoard)
   {
      return new KillingMove(chessBoard.getPiece(CHESS_PIECE_KEY), REGULAR_MOVE_POSITION, chessBoard.getPiece(ChessPieceKey.BLACK_KING));
   }

   private Move buildPawnPromotionMove(ChessBoard chessBoard)
   {
      return new PawnPromotionMove(chessBoard.getPiece(CHESS_PIECE_KEY), REGULAR_MOVE_POSITION, new QueenPiece(REGULAR_MOVE_POSITION,
            ChessPieceColor.WHITE));
   }
}
