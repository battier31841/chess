package gmfb.chess.core.piece;

import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.move.Move;
import gmfb.chess.uitl.logic.possiblemoves.PossibleMoveGenerator;
import gmfb.chess.uitl.logic.possiblemoves.PossibleMoveGeneratorFactory;

import java.util.Set;

public abstract class AbstractChessPiece implements ChessPiece
{
   protected Position currentPosition;
   protected ChessPieceColor color;
   protected PossibleMoveGenerator moveGenerator;

   protected AbstractChessPiece(Position currentPosition, ChessPieceColor color)
   {
      this.currentPosition = currentPosition;
      this.color = color;
      this.moveGenerator = PossibleMoveGeneratorFactory.getPossibleMoveGenerator(this, color);
   }

   public Position getCurrentPostion()
   {
      return currentPosition;
   }

   public ChessPiece updatePosition(Position currentPosition)
   {
      this.currentPosition = currentPosition;
      return this;
   }

   public ChessPieceColor getColor()
   {
      return color;
   }

   public Set<Move> getPossibleMoves(ChessBoard chessBoard)
   {
      return moveGenerator.genrateMoves(chessBoard);
   }

   public Set<Move> getPossibleKillingMoves(ChessBoard chessBoard)
   {
      return moveGenerator.genrateMoves(chessBoard);
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof AbstractChessPiece)
      {
         ChessPiece that = (AbstractChessPiece) obj;
         return ((this.getCurrentPostion().equals(that.getCurrentPostion())) && (this.getColor().equals(that.getColor())));
      }
      return false;
   }

   public void setMoveGenerator(PossibleMoveGenerator moveGenerator)
   {
      this.moveGenerator = moveGenerator;
   }
}
