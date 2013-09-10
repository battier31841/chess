package gmfb.chess.core;

public final class Position
{
   private int xPosition;
   private int yPosition;

   public Position(int xPosition, int yPosition)
   {
      super();
      this.xPosition = xPosition;
      this.yPosition = yPosition;
   }

   public int getxPosition()
   {
      return xPosition;
   }

   public int getyPosition()
   {
      return yPosition;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj instanceof Position)
      {
         return (this.hashCode() == ((Position) obj).hashCode());
      }
      return false;
   }

   @Override
   public int hashCode()
   {
      return (xPosition * 10) + yPosition;
   }

   @Override
   public String toString()
   {
      return "(" + xPosition + "," + yPosition + ")  :  " + hashCode();
   }
}
