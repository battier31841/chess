package gmfb.chess.core.board;

import gmfb.chess.core.Position;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.ChessPieceKey;
import gmfb.chess.core.piece.ChessPieceSet;
import gmfb.chess.core.piece.ChessPieceSetEntry;
import gmfb.chess.core.printer.BoardPrinter;
import gmfb.chess.uitl.exception.IllegalMoveException;
import gmfb.chess.uitl.exception.InvalidCastleMoveException;
import gmfb.chess.uitl.exception.InvalidEnPassentMoveException;
import gmfb.chess.uitl.exception.InvalidKillingMoveException;
import gmfb.chess.uitl.exception.InvalidMoveException;
import gmfb.chess.uitl.exception.InvalidPawnPromotionMoveException;
import gmfb.chess.uitl.exception.PositionOutOfBoundsException;
import gmfb.chess.uitl.logic.LastMoveSnapShotBuilder;
import gmfb.chess.uitl.logic.check.BoardEvaluator;
import gmfb.chess.uitl.logic.check.BoardEvaluatorImpl;
import gmfb.chess.uitl.logic.handlemoves.MoveHandler;
import gmfb.chess.uitl.logic.handlemoves.MoveHandlerImpl;
import gmfb.chess.uitl.validation.ValidMoveValidator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ChessBoardImpl implements ChessBoard
{
   private BiMap<ChessPieceKey, ChessPiece> pieces = HashBiMap.create();
   private Map<ChessPieceKey, Boolean> hasMoved = new HashMap<ChessPieceKey, Boolean>();
   private MoveHandler moveHandler = new MoveHandlerImpl();
   private ValidMoveValidator validMoveValidator = new ValidMoveValidator();
   private BoardEvaluator boardEvaluator = new BoardEvaluatorImpl();
   private LastMoveSnapShotBuilder lastMoveSnapShotBuilder = new LastMoveSnapShotBuilder();

   private Move lastMove;

   public BiMap<ChessPieceKey, ChessPiece> getPieces()
   {
      return pieces;
   }

   public ChessBoard buildNewGame()
   {
      return buildNewChessGame();
   }

   public ChessPiece getPiece(ChessPieceKey key)
   {
      return pieces.get(key);
   }

   public ChessPieceKey getKey(ChessPiece piece)
   {
      return pieces.inverse()
            .get(piece);
   }

   public boolean hasPieceMoved(ChessPieceKey key)
   {
      return hasMoved.get(key);
   }

   public ChessPiece getPieceByPosition(Position position)
   {
      for (ChessPiece piece : pieces.inverse()
            .keySet())
      {
         if (piece.getCurrentPostion()
               .equals(position))
         {
            return piece;
         }
      }

      return null;
   }

   public void handleMove(Move move) throws PositionOutOfBoundsException, IllegalMoveException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException
   {
      validMoveValidator.validateMoveValidity(this, move);
      preformMoveAndUpdateMetaData(move);
   }

   public Move getlastMove()
   {
      return lastMove;
   }

   public void printCurrentBoard(BoardPrinter boardPrinter)
   {
      boardPrinter.printBoard(this, System.out);
   }

   public boolean isInCheck(ChessPieceColor color)
   {
      return boardEvaluator.isInCheck(this, color);
   }

   public boolean isInCheckMate(ChessPieceColor color)
   {
      return boardEvaluator.isInCheckMate(this, color);
   }

   public Set<Move> getMovesForColor(ChessPieceColor color)
   {
      Set<Move> moves = new HashSet<Move>();
      for (ChessPiece chessPiece : pieces.inverse()
            .keySet())
      {
         if (chessPiece.getColor()
               .equals(color))
         {
            Set<Move> possibleKillingMoves = chessPiece.getPossibleKillingMoves(this);
            for (Move move : possibleKillingMoves)
            {
               moves.add(move);
            }
         }
      }
      return moves;
   }

   public Set<Position> getAttackablePositions(ChessPieceColor color)
   {
      Set<Position> positions = new HashSet<Position>();
      Set<Move> possibleKillingMoves = getMovesForColor(color);
      for (Move move : possibleKillingMoves)
      {
         positions.add(move.getTo());
      }
      return positions;
   }

   private ChessBoardImpl buildNewChessGame()
   {
      for (ChessPieceSetEntry piece : ChessPieceSet.getChessSet())
      {
         this.pieces.put(piece.getKey(), piece.getChessPiece());
         hasMoved.put(piece.getKey(), false);
      }
      return this;
   }

   private void preformMoveAndUpdateMetaData(Move move)
   {
      updateHasMoved(move.getPiece());
      moveHandler.handleMove(this, move);
      lastMove = lastMoveSnapShotBuilder.buildSnapShoot(move);
   }

   private void updateHasMoved(ChessPiece chessPiece)
   {
      hasMoved.put(getKey(chessPiece), true);
   }

   public void setMoveHandler(MoveHandler moveHandler)
   {
      this.moveHandler = moveHandler;
   }

   public void setValidMoveValidator(ValidMoveValidator validMoveValidator)
   {
      this.validMoveValidator = validMoveValidator;
   }

   public void setBoardEvaluator(BoardEvaluator boardEvaluator)
   {
      this.boardEvaluator = boardEvaluator;
   }
}
