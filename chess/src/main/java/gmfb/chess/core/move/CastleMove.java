package gmfb.chess.core.move;

import gmfb.chess.core.Position;
import gmfb.chess.core.board.ChessBoard;
import gmfb.chess.core.piece.ChessPiece;

public class CastleMove extends Move
{
   private Move rookMove;
   private ChessBoard chessBoard;

   public CastleMove(ChessPiece piece, Position to, Move rookMove, ChessBoard chessBoard)
   {
      super(piece, to);
      this.rookMove = rookMove;
      this.chessBoard = chessBoard;
   }

   public Move getRookMove()
   {
      return rookMove;
   }

   public ChessBoard getChessBoard()
   {
      return chessBoard;
   }
}
