import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Vector;



public class SquareBonus extends Bonus
{

    public SquareBonus(int brickSize, BufferedImage image)
    {
        
        bonusShape = new Brick[8];
        
        bonusShape[0] = new Brick(brickSize, image, 0, 0, 0, 0, 0, 0);
        bonusShape[1] = new Brick(brickSize, image, 1, 0, 1, 0, 1*brickSize, 0);
        bonusShape[2] = new Brick(brickSize, image, 2, 0, 2, 0, 2*brickSize, 0);
        bonusShape[3] = new Brick(brickSize, image, 0, 1, 0, 1, 0, 1*brickSize);
        bonusShape[4] = new Brick(brickSize, image, 2, 1, 2, 1, 2*brickSize, 1*brickSize);
        bonusShape[5] = new Brick(brickSize, image, 0, 2, 0, 2, 0, 2*brickSize);
        bonusShape[6] = new Brick(brickSize, image, 1, 2, 1, 2, 1*brickSize, 2*brickSize);
        bonusShape[7] = new Brick(brickSize, image, 2, 2, 2, 2, 2*brickSize, 2*brickSize);
        
      //  int brickSize, BufferedImage brickImage, int row, int column, int relativeRow, int relativeColumn, int y, int x)
        
    }
    
    public Vector<Point> checkBonus(GameAreaBrick[][] gameBricks)
    {
        
        for(int row = 0; row < gameBricks.length; row++)
        {
            for(int column = 0; column < gameBricks[0].length; column++)
            {
                if(gameBricks[row][column] != null)
                {
                    
                    for(int index = 0; index < bonusShape.length; index++)
                    {                   
                        if(column + bonusShape[index].relativeColumn < gameBricks[0].length && row + bonusShape[index].relativeRow < gameBricks.length
                                && gameBricks[row + bonusShape[index].relativeRow][column + bonusShape[index].relativeColumn] != null
                                && gameBricks[row + bonusShape[index].relativeRow][column + bonusShape[index].relativeColumn].getImage() 
                                == bonusShape[index].getImage())
                        {
                            if(index == bonusShape.length-1)
                                return getShapeCoordinates(column, row);
                        }
                        else
                        {
                            break;
                        }
                    }
                }
            }
        }
        
        
        return null;
    }
    
    private Vector<Point> getShapeCoordinates(int startX, int startY)
    {
        Vector<Point> retval = new Vector<Point>();
        
        for(int i = 0; i < bonusShape.length; i++)
        {
            retval.add(new Point(bonusShape[i].relativeColumn + startX, bonusShape[i].relativeRow + startY));
        }
        
        return retval;
        
    }

}
