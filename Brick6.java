import java.awt.Point;
import java.awt.image.BufferedImage;

/*
 * 
 *      _ _
 *     |_|_|
 * 
 * 
 */

public class Brick6 extends Brick
{

    public Brick6(int gameAreaStartX, int gameAreaStartY, int brickSize, int brickIndex, BufferedImage brickImage)
    {
        super(brickSize, brickIndex, brickImage);
        
        switch(brickIndex)
        {
        case 1:      
            this.row = 0;
            this.column = 9;
            this.relativeRow = 0;
            this.relativeColumn = 0;
            break;
        case 2:     
            row = 0;
            column = 10;
            this.relativeRow = 0;
            this.relativeColumn = 1;
            break;
        }
        
        this.x = gameAreaStartX + this.column*brickSize;
        this.y = gameAreaStartY + this.row*brickSize;
        
    }


    public void rotate(boolean clockWise)
    {
        setPreviousPosition();

        switch(index)
        {
        
            case 1:
                break;
            
            case 2:
                switch(position)
                {
                case 0:
                    column += 1;
                    row -= 1;
                    x += size;
                    y -= size;
                    break;
                case 1:
                    column -= 1;
                    row -= 1;
                    x -= size;
                    y -= size;
                    break;
                case 2:
                    column -= 1;
                    row += 1;
                    x -= size;
                    y += size;
                    break;
                case 3:
                    column += 1;
                    row += 1;
                    x += size;
                    y += size;
                    break;
                }
    
                break;

        }

    }

}
