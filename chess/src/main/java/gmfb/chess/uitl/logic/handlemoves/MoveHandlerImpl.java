package gmfb.chess.uitl.logic.handlemoves;

import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.CastleMove;
import gmfb.chess.core.move.KillingMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.move.PawnPromotionMove;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.ChessPieceKey;

public class MoveHandlerImpl implements MoveHandler
{
   public void handleMove(ChessBoard chessBoard, Move move)
   {
      if (move instanceof KillingMove)
      {
         handleKilingMove(chessBoard, (KillingMove) move);
      }
      if (move instanceof PawnPromotionMove)
      {
         handlePawnPromptionMove(chessBoard, (PawnPromotionMove) move);
      }
      if (move instanceof CastleMove)
      {
         handleCastleMove(chessBoard, (CastleMove) move);
      }
      handleRegularMove(chessBoard, move);
   }

   private void handleCastleMove(ChessBoard chessBoard, CastleMove move)
   {
      handleRegularMove(chessBoard, move);
      handleRegularMove(chessBoard, move.getRookMove());
   }

   private void handleRegularMove(ChessBoard chessBoard, Move move)
   {
      ChessPiece movingPiece = move.getPiece();
      ChessPieceKey movingPieceKey = chessBoard.getKey(movingPiece);
      chessBoard.getPieces()
            .put(movingPieceKey, movingPiece.updatePosition(move.getTo()));
   }

   private void handlePawnPromptionMove(ChessBoard chessBoard, PawnPromotionMove move)
   {
      handleRegularMove(chessBoard, move);
      ChessPieceKey pawnPieceKey = chessBoard.getKey(move.getPiece());
      chessBoard.getPieces()
            .remove(pawnPieceKey);
      chessBoard.getPieces()
            .put(pawnPieceKey, move.getNewPiece());
   }

   private void handleKilingMove(ChessBoard chessBoard, KillingMove move)
   {
      chessBoard.getPieces()
            .remove(chessBoard.getKey(move.getDeadPiece()));
      handleRegularMove(chessBoard, move);
   }
}
