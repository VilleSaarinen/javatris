import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

/*
 * 
 * This is a special brick.
 * The shape is a mystery...
 * 
 */


public class Brick12 extends GameAreaBrick
{
    
    private static int i = 0;
    private static int[] otherBricksIndexes = new int[2];
    private static Random rand = new Random();
    
    public Brick12(int gameAreaStartX, int gameAreaStartY, int brickSize, int brickIndex, BufferedImage brickImage)
    {
        super(brickSize, brickIndex, brickImage);
        
        Brick12.setRandomPosition(this, true);

        this.x += gameAreaStartX;
        this.y += gameAreaStartY;  
        
    }



    public void rotate(boolean clockWise)
    {
        
        Brick12.setRandomPosition(this, false);
    }
    
    
    private static void setRandomPosition(Brick12 brick, boolean first)
    {
        
        int rowIncrement = 0;
        int columnIncrement = 0;
        
        if(!first)
            brick.setPreviousPosition();
        
        int place = Math.abs(rand.nextInt()%(9-i));
        
        for(int j = 0; j < 2; j++)
        {
            for(int h = 0; h < i; h++)
            {
                if(otherBricksIndexes[h] == place)
                    place++;
            }
        }
        
        if(i < 2)
            otherBricksIndexes[i] = place;
        
        switch(place)
        {
            case 0:
                rowIncrement = 0;
                columnIncrement = 0;
                break;
            case 1:
                rowIncrement = 0;
                columnIncrement = 1;
                break;
            case 2:
                rowIncrement = 0;
                columnIncrement = 2;
                break;
            case 3:
                rowIncrement = 1;
                columnIncrement = 0;
                break;
            case 4:
                rowIncrement = 1;
                columnIncrement = 1;
                break;
            case 5:
                rowIncrement = 1;
                columnIncrement = 2;
                break;
            case 6:
                rowIncrement = 2;
                columnIncrement = 0;
                break;
            case 7:
                rowIncrement = 2;
                columnIncrement = 1;
                break;
            case 8:
                rowIncrement = 2;
                columnIncrement = 2;
                break;   
        }
        
        if(first)
        {
            brick.row = rowIncrement;
            brick.column = columnIncrement+9;
            brick.y += rowIncrement*brick.size;
            brick.x += (columnIncrement+9)*brick.size;
        }
        else
        {
            if(brick.relativeRow <= rowIncrement)
            {
                brick.row += rowIncrement - brick.relativeRow;
                brick.y += (rowIncrement - brick.relativeRow)*brick.size;
            }
            else
            {
                brick.row -= brick.relativeRow - rowIncrement;
                brick.y -= (brick.relativeRow - rowIncrement)*brick.size;
            }
            
            if(brick.relativeColumn <= columnIncrement)
            {
                brick.column += columnIncrement - brick.relativeColumn;
                brick.x += (columnIncrement - brick.relativeColumn)*brick.size;
            }
            else
            {
                brick.column -= brick.relativeColumn - columnIncrement;
                brick.x -= (brick.relativeColumn - columnIncrement)*brick.size;
            }
        }

        brick.relativeRow = rowIncrement;
        brick.relativeColumn = columnIncrement;
        
        i = (i+1)%3;
    }
    
}
