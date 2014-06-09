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
    private final static int numberOfBonuses = 1; //TODO: config file? 
    protected Brick[] bonusShape;
    protected BrickGenerator brickGenerator;
    
    public abstract Vector<Point> checkBonus(GameAreaBrick[][] gameBricks);
    
    public static Bonus createBonus(int brickSize, BufferedImage image)
    {
        Bonus bonus = null;
        Random rand = new Random();
        
        switch(rand.nextInt()%numberOfBonuses)
        {
            case 0: bonus = new SquareBonus(brickSize, image);
                break;
        }

        
        
        return bonus;
    }
    
    
    public Brick[] getBonusShape()
    {
        return bonusShape;
    }
    
}
