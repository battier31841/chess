package gmfb.chess.uitl.validation;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;
import gmfb.chess.core.Position;
import gmfb.chess.core.move.CastleMove;
import gmfb.chess.core.move.EnPassentMove;
import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.move.PawnPromotionMove;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.pieces.PawnPiece;
import gmfb.chess.uitl.exception.InvalidCastleMoveException;
import gmfb.chess.uitl.exception.InvalidEnPassentMoveException;
import gmfb.chess.uitl.exception.InvalidKillingMoveException;
import gmfb.chess.uitl.exception.InvalidMoveException;
import gmfb.chess.uitl.exception.InvalidPawnPromotionMoveException;
import gmfb.chess.uitl.exception.PositionOutOfBoundsException;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

public class MoveValidatorTest
{
   private static final ChessPieceColor WHITE = ChessPieceColor.WHITE;
   private static final Position VALID_POSITION = new Position(0, 0);
   private static final Position OTHER_VALID_POSITION = new Position(0, 7);
   private static final Position INVALID_POSITION = new Position(-1, 0);
   private static final ChessPiece CHESS_PIECE = new PawnPiece(VALID_POSITION, WHITE);

   private final MoveValidator moveValidator = new MoveValidator();
   private final IMocksControl mocksControl = createControl();
   private final PositionValidator positionValidator = mocksControl.createMock(PositionValidator.class);
   private final KillingMoveValidator killingMoveValidator = mocksControl.createMock(KillingMoveValidator.class);
   private final PawnPromotionMoveValidator pawnPromotionMoveValidator = mocksControl.createMock(PawnPromotionMoveValidator.class);
   private final EnPassentMoveValidator enPassentMoveValidator = mocksControl.createMock(EnPassentMoveValidator.class);
   private final CastleMoveValidator castleMoveValidator = mocksControl.createMock(CastleMoveValidator.class);

   @Before
   public void setUp()
   {
      moveValidator.setCastleMoveValidator(castleMoveValidator);
      moveValidator.setEnPassentMoveValidator(enPassentMoveValidator);
      moveValidator.setKillingMoveValidator(killingMoveValidator);
      moveValidator.setPawnPromotionMoveValidator(pawnPromotionMoveValidator);
      moveValidator.setPositionValidator(positionValidator);
   }

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
      positionValidator.validate(isA(Position.class));
      expectLastCall().andThrow(new PositionOutOfBoundsException());

      mocksControl.replay();
      moveValidator.validate(new Move(CHESS_PIECE, INVALID_POSITION));
      mocksControl.verify();
   }

   @Test
   public void shouldValidatePawnPromotionMove() throws PositionOutOfBoundsException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      positionValidator.validate(isA(Position.class));
      expectLastCall();
      pawnPromotionMoveValidator.validate(isA(PawnPromotionMove.class));
      expectLastCall();

      mocksControl.replay();
      moveValidator.validate(new PawnPromotionMove(CHESS_PIECE, OTHER_VALID_POSITION, null));
      mocksControl.verify();
   }

   @Test
   public void shouldValidateKillingMove() throws InvalidKillingMoveException, PositionOutOfBoundsException, InvalidMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      positionValidator.validate(isA(Position.class));
      expectLastCall();
      killingMoveValidator.validate(isA(KillingMove.class));
      expectLastCall();

      mocksControl.replay();
      moveValidator.validate(new KillingMove(CHESS_PIECE, OTHER_VALID_POSITION, null));
      mocksControl.verify();
   }

   @Test
   public void shouldValidateEnPassentMove() throws InvalidKillingMoveException, PositionOutOfBoundsException, InvalidMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      positionValidator.validate(isA(Position.class));
      expectLastCall();
      enPassentMoveValidator.validate(isA(EnPassentMove.class));
      expectLastCall();

      mocksControl.replay();
      moveValidator.validate(new EnPassentMove(CHESS_PIECE, OTHER_VALID_POSITION, null, null));
      mocksControl.verify();
   }

   @Test
   public void shouldValidateCastleMove() throws PositionOutOfBoundsException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      positionValidator.validate(isA(Position.class));
      expectLastCall();
      castleMoveValidator.validate(isA(CastleMove.class));
      expectLastCall();

      mocksControl.replay();
      moveValidator.validate(new CastleMove(CHESS_PIECE, OTHER_VALID_POSITION, null, null));
      mocksControl.verify();
   }
}
