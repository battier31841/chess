package gmfb.chess.uitl.logic.possiblemoves;

import gmfb.chess.core.Position;
import gmfb.chess.core.move.EnPassentMove;
import gmfb.chess.core.move.Move;
import gmfb.chess.core.piece.ChessPiece;
import gmfb.chess.core.piece.pieces.PawnPiece;

public class EnPassentMoveEvaluator
{
   ChessPiece movingPiece;
   ChessPiece deadPiece;
   ChessPiece lastMovepiece;

   public boolean evaluateMovePosibility(EnPassentMove enPassentMove)
   {
      movingPiece = enPassentMove.getPiece();
      deadPiece = enPassentMove.getDeadPiece();
      lastMovepiece = enPassentMove.getLastMove()
            .getPiece();

      return ensureDifferentColor() && ensureBothArePawns() && validateLastMove(enPassentMove.getLastMove()) && validatPositions();
   }

   private boolean validateLastMove(Move lastMove)
   {
      Position pieceCurrentPosition = lastMovepiece.getCurrentPostion();
      Position deadPieceCurrentPostion = lastMove.getTo();
      int pieceCurrentPositionX = pieceCurrentPosition.getxPosition();
      int pieceCurrentPositionY = pieceCurrentPosition.getyPosition();
      int deadPieceCurrentPositionX = deadPieceCurrentPostion.getxPosition();
      int deadPieceCurrentPositionY = deadPieceCurrentPostion.getyPosition();

      if ((pieceCurrentPositionX != deadPieceCurrentPositionX) || (Math.abs(pieceCurrentPositionY - deadPieceCurrentPositionY) != 2))
      {
         return false;
      }
      return true;
   }

   private boolean validatPositions()
   {

      Position pieceCurrentPosition = movingPiece.getCurrentPostion();
      Position deadPieceCurrentPostion = deadPiece.getCurrentPostion();
      int pieceCurrentPositionX = pieceCurrentPosition.getxPosition();
      int pieceCurrentPositionY = pieceCurrentPosition.getyPosition();
      int deadPieceCurrentPositionX = deadPieceCurrentPostion.getxPosition();
      int deadPieceCurrentPositionY = deadPieceCurrentPostion.getyPosition();

      if ((Math.abs(pieceCurrentPositionX - deadPieceCurrentPositionX) != 1) || (pieceCurrentPositionY != deadPieceCurrentPositionY))
      {
         return false;
      }
      return true;
   }

   private boolean ensureBothArePawns()
   {
      if ((!(movingPiece instanceof PawnPiece) || (!(deadPiece instanceof PawnPiece) || (!(lastMovepiece instanceof PawnPiece)))))
      {
         return false;
      }
      return true;
   }

   private boolean ensureDifferentColor()
   {
      if (movingPiece.getColor()
            .equals(deadPiece.getColor()))
      {
         return false;
      }
      return true;
   }
}
