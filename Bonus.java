import java.util.Random;
import java.awt.Point;

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
    
    public abstract Point checkBonus(GameAreaBrick[][] gameBricks);
    
    public Bonus createBonus()
    {
        Bonus bonus = null;
        Random rand = new Random();
        
        switch(rand.nextInt()%numberOfBonuses)
        {
            case 0:
                break;
        }

        
        
        return bonus;
    }
    
    public Bonus(BrickGenerator generator)
    {
        this.brickGenerator = generator;
    }
    
    
    public Brick[] getBonusShape()
    {
        return bonusShape;
    }
    
}
