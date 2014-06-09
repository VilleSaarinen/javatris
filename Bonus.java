import java.util.Random;
import java.awt.Point;

/*
 * 
 * Base class for bonus patterns
 * 
 */

public abstract class Bonus
{
    private final static int numberOfBonuses = 3; //TODO: config file? 
    private Brick[] bonusShape;
    
    
    public abstract Point checkBonus(Brick[][] gameBricks);
    
    public Bonus createBonus()
    {
        Bonus bonus = null;
        Random rand = new Random();
        
        switch(rand.nextInt()%numberOfBonuses)
        {
            case 0:
                break;
            
            case 1:
                break;
                
            case 2:
                break;
        }

        
        
        return bonus;
    }
    
    public Bonus()
    {
        
    }
    
    
    public Brick[] getBonusShape()
    {
        return bonusShape;
    }
    
}
