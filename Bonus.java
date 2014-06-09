import java.util.Random;
import java.util.Vector;
import java.awt.Point;
import java.awt.image.BufferedImage;

/*
 * 
 * Base class for bonus patterns
 * 
 */

public abstract class Bonus
{
    private final static int numberOfBonuses = 2; //TODO: config file? 
    protected Brick[] bonusShape;
    protected BrickGenerator brickGenerator;
    
    
    public static Bonus createBonus(int brickSize, BufferedImage image)
    {
        Bonus bonus = null;
        Random rand = new Random();
        
        switch(Math.abs(rand.nextInt())%numberOfBonuses)
        {
            case 0: bonus = new SquareBonus(brickSize, image);
                break;
            case 1: bonus = new PyramidBonus(brickSize, image);
                break;
        }

        
        
        return bonus;
    }
    
    
    public Brick[] getBonusShape()
    {
        return bonusShape;
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
                        if(column + bonusShape[index].relativeColumn < gameBricks[0].length
                                && row + bonusShape[index].relativeRow < gameBricks.length
                                && column + bonusShape[index].relativeColumn >= 0
                                && row + bonusShape[index].relativeRow >= 0               
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
