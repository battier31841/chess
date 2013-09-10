package gmfb.chess.uitl.validation;

import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceKey;
import gmfb.chess.uitl.exception.IllegalMoveException;
import gmfb.chess.uitl.exception.InvalidCastleMoveException;
import gmfb.chess.uitl.exception.InvalidEnPassentMoveException;
import gmfb.chess.uitl.exception.InvalidKillingMoveException;
import gmfb.chess.uitl.exception.InvalidMoveException;
import gmfb.chess.uitl.exception.InvalidPawnPromotionMoveException;
import gmfb.chess.uitl.exception.PositionOutOfBoundsException;

import java.util.Map;
import java.util.Set;

public class ValidMoveValidator
{
   private MoveValidator moveValidator = new MoveValidator();

   public void validateMoveValidity(ChessBoard chessBoard, Move move) throws PositionOutOfBoundsException, IllegalMoveException,
         InvalidMoveException, InvalidKillingMoveException, InvalidPawnPromotionMoveException, InvalidEnPassentMoveException,
         InvalidCastleMoveException
   {
      moveValidator.validate(move);
      validateValidity(chessBoard, move);
   }

   private void validateValidity(ChessBoard chessBoard, Move move) throws IllegalMoveException
   {
      Map<ChessPieceKey, ChessPiece> currentPieces = chessBoard.getPieces();
      Set<ChessPieceKey> currentPiecesKeys = currentPieces.keySet();

      for (ChessPieceKey key : currentPiecesKeys)
      {
         if (!currentPieces.get(key)
               .getPossibleMoves(chessBoard)
               .contains(move))
         {
            throw new IllegalMoveException();
         }
      }
   }
}
