package gmfb.chess.uitl.validation;

import gmfb.chess.core.Position;
import gmfb.chess.core.move.EnPassentMove;
import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.move.PawnPromotionMove;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.core.piece.pieces.QueenPiece;
import gmfb.chess.uitl.exception.InvalidCastleMoveException;
import gmfb.chess.uitl.exception.InvalidEnPassentMoveException;
import gmfb.chess.uitl.exception.InvalidKillingMoveException;
import gmfb.chess.uitl.exception.InvalidMoveException;
import gmfb.chess.uitl.exception.InvalidPawnPromotionMoveException;
import gmfb.chess.uitl.exception.PositionOutOfBoundsException;

import org.junit.Test;

public class MoveValidatorTest
{
   private static final ChessPieceColor WHITE = ChessPieceColor.WHITE;
   private static final Position VALID_POSITION = new Position(0, 0);
   private static final Position OTHER_VALID_POSITION = new Position(0, 7);
   private static final Position INVALID_POSITION = new Position(-1, 0);
   private static final ChessPiece CHESS_PIECE = new PawnPiece(VALID_POSITION, WHITE);
   private static final ChessPiece WHITE_PIECE = new PawnPiece(VALID_POSITION, WHITE);
   private static final ChessPiece QUEEN_PIECE = new QueenPiece(OTHER_VALID_POSITION, WHITE);
   private static final ChessPiece BLACK_PIECE = new PawnPiece(OTHER_VALID_POSITION, ChessPieceColor.BLACK);

   private static final Position BEFORE_MOVE = new Position(2, 2);
   private static final Position AFTER_MOVE = new Position(1, 1);
   private static final Position DEAD_PIECE_POSITION = new Position(1, 2);
   private static final Position DEAD_PIECE_POSITION_LAST_MOVE = new Position(1, 0);
   private static final PawnPiece PAWN_PIECE = new PawnPiece(BEFORE_MOVE, ChessPieceColor.BLACK);
   private static final PawnPiece DEAD_PAWN_PIECE = new PawnPiece(DEAD_PIECE_POSITION, ChessPieceColor.WHITE);
   private static final PawnPiece DEAD_PAWN_PIECE_LAST_MOVE = new PawnPiece(DEAD_PIECE_POSITION_LAST_MOVE, ChessPieceColor.WHITE);
   private static final Move LAST_MOVE = new Move(DEAD_PAWN_PIECE_LAST_MOVE, DEAD_PIECE_POSITION);

   private final MoveValidator moveValidator = new MoveValidator();

   @Test
   public void shouldValidateMove() throws PositionOutOfBoundsException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      moveValidator.validate(new Move(CHESS_PIECE, OTHER_VALID_POSITION));
   }

   @Test(expected = InvalidMoveException.class)
   public void shouldNotValidateMoveingToSameLocation() throws PositionOutOfBoundsException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      moveValidator.validate(new Move(CHESS_PIECE, VALID_POSITION));
   }

   @Test(expected = PositionOutOfBoundsException.class)
   public void shouldNotValidateMoveBadFrom() throws PositionOutOfBoundsException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      moveValidator.validate(new Move(CHESS_PIECE, INVALID_POSITION));
   }

   @Test
   public void shouldValidatePawnPromotion() throws PositionOutOfBoundsException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      moveValidator.validate(new PawnPromotionMove(WHITE_PIECE, OTHER_VALID_POSITION, QUEEN_PIECE));
   }

   @Test
   public void shouldValidateKillingMove() throws InvalidKillingMoveException, PositionOutOfBoundsException, InvalidMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      moveValidator.validate(new KillingMove(WHITE_PIECE, OTHER_VALID_POSITION, BLACK_PIECE));
   }

   @Test
   public void shouldValidateEnPassentMove() throws InvalidKillingMoveException, PositionOutOfBoundsException, InvalidMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      moveValidator.validate(new EnPassentMove(PAWN_PIECE, AFTER_MOVE, DEAD_PAWN_PIECE, LAST_MOVE));
   }
}
