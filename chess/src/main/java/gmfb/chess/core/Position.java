package gmfb.chess.core;

public final class Position
{
   public static final int POSITION_MIN = 0;
   public static final int POSITION_MAX = 7;

   private int xPosition;
   private int yPosition;

   public Position(int xPosition, int yPosition)
   {
      super();
      this.xPosition = xPosition;
      this.yPosition = yPosition;
   }

   public static boolean isValid(Position position)
   {
      return position.getxPosition() >= POSITION_MIN && position.getxPosition() <= POSITION_MAX && position.getyPosition() >= POSITION_MIN
            && position.getyPosition() <= POSITION_MAX;
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
