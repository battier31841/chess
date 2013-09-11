package gmfb.chess.core.board;

import gmfb.chess.core.Position;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceColor;
import gmfb.chess.core.piece.ChessPieceKey;
import gmfb.chess.core.printer.BoardPrinter;
import gmfb.chess.uitl.exception.IllegalMoveException;
import gmfb.chess.uitl.exception.InvalidCastleMoveException;
import gmfb.chess.uitl.exception.InvalidEnPassentMoveException;
import gmfb.chess.uitl.exception.InvalidKillingMoveException;
import gmfb.chess.uitl.exception.InvalidMoveException;
import gmfb.chess.uitl.exception.InvalidPawnPromotionMoveException;
import gmfb.chess.uitl.exception.PositionOutOfBoundsException;

import java.util.Set;

import com.google.common.collect.BiMap;

public interface ChessBoard
{
   public ChessBoard buildNewGame();

   public ChessBoard getClone();

   public BiMap<ChessPieceKey, ChessPiece> getPieces();

   public ChessPieceKey getKey(ChessPiece piece); //

   public ChessPiece getPiece(ChessPieceKey key); //

   public ChessPiece getPieceByPosition(Position position);

   public Set<Move> getMovesForColor(ChessPieceColor color);

   public Set<Position> getAttackablePositions(ChessPieceColor color);

   public boolean hasPieceMoved(ChessPieceKey key); //

   public void handleMove(Move move) throws PositionOutOfBoundsException, IllegalMoveException, InvalidMoveException, InvalidKillingMoveException,
         InvalidPawnPromotionMoveException, InvalidEnPassentMoveException, InvalidCastleMoveException;

   public Move getlastMove();//

   public void printCurrentBoard(BoardPrinter boardPrinter);

   public boolean isInCheck(ChessPieceColor color);

   public boolean isInCheckMate(ChessPieceColor color);
}
