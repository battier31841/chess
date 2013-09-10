package gmfb.chess.uitl.validation;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoardImpl;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.AbstractChessPiece;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.ChessPieceKey;
import gmfb.chess.core.piece.pieces.RookPiece;
import gmfb.chess.uitl.exception.IllegalMoveException;
import gmfb.chess.uitl.exception.InvalidCastleMoveException;
import gmfb.chess.uitl.exception.InvalidEnPassentMoveException;
import gmfb.chess.uitl.exception.InvalidKillingMoveException;
import gmfb.chess.uitl.exception.InvalidMoveException;
import gmfb.chess.uitl.exception.InvalidPawnPromotionMoveException;
import gmfb.chess.uitl.exception.PositionOutOfBoundsException;

import java.util.Set;

import org.easymock.IMocksControl;
import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Sets;

public class ValidMoveValidatorTest
{
   private static final ChessPieceColor PIECE_COLOR = ChessPieceColor.WHITE;
   private static final Position CURRENT_POSITION = new Position(1, 2);
   private static final Position NEW_POSITION = new Position(1, 3);
   private static final Position BAD_POSITION = new Position(1, 4);
   private static final ChessPiece WHITE_ROOK_PAWN = new RookPiece(CURRENT_POSITION, PIECE_COLOR);
   private static final Move VALID_MOVE = new Move(WHITE_ROOK_PAWN, NEW_POSITION);
   private static final Set<Move> VALID_MOVES_LIST = Sets.newHashSet(VALID_MOVE);
   private static final Move INVALID_MOVE = new Move(WHITE_ROOK_PAWN, BAD_POSITION);
   private static final Set<Move> INVALID_MOVES_LIST = Sets.newHashSet(INVALID_MOVE);

   private final ValidMoveValidator validMoveValidator = new ValidMoveValidator();
   private final IMocksControl mocksControl = createControl();
   private AbstractChessPiece chessPiece = mocksControl.createMock(AbstractChessPiece.class);
   private ChessBoardImpl chessBoard = mocksControl.createMock(ChessBoardImpl.class);

   @Test
   public void shouldvalidateMove() throws PositionOutOfBoundsException, IllegalMoveException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      expect(chessBoard.getPieces()).andReturn(buildCurrentPieces(chessPiece));
      expect(chessPiece.getPossibleMoves(chessBoard)).andReturn(VALID_MOVES_LIST);

      mocksControl.replay();
      validMoveValidator.validateMoveValidity(chessBoard, VALID_MOVE);
      mocksControl.verify();
   }

   @Test(expected = IllegalMoveException.class)
   public void shouldNotValidateMove() throws PositionOutOfBoundsException, IllegalMoveException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      expect(chessBoard.getPieces()).andReturn(buildCurrentPieces(chessPiece));
      expect(chessPiece.getPossibleMoves(chessBoard)).andReturn(INVALID_MOVES_LIST);

      mocksControl.replay();
      validMoveValidator.validateMoveValidity(chessBoard, VALID_MOVE);
      mocksControl.verify();
   }

   private BiMap<ChessPieceKey, ChessPiece> buildCurrentPieces(AbstractChessPiece chessPiece)
   {
      BiMap<ChessPieceKey, ChessPiece> pieces = HashBiMap.create();
      pieces.put(ChessPieceKey.WHITE_KING_ROOK_PAWN, chessPiece);
      return pieces;
   }
}
