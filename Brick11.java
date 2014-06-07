import java.awt.Point;
import java.awt.image.BufferedImage;

/*
 * 
 *      _ _ _
 *     |_|_|_|
 *       |_|
 * 
 * 
 */


public class Brick11 extends Brick
{

    public Brick11(int gameAreaStartX, int gameAreaStartY, int brickSize, int brickIndex, BufferedImage brickImage)
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
        case 3:     
            row = 0;
            column = 11;
            this.relativeRow = 0;
            this.relativeColumn = 2;
            break;
        case 4:
            row = 1;
            column = 10;
            this.relativeRow = 1;
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
            switch(position)
            {
            case 0:
                column -= 2;
                x -= size*2;
                break;
            case 1:
                row += 2;
                y += size*2;
                break;
            case 2:
                column += 2;
                x += size*2;
                break;
            case 3:
                row -= 2;
                y -= size*2;
                break;    
            }
        
            break;

        
        case 2:
            switch(position)
            {
            case 0:
                row -= 1;
                column -= 1;
                y -= size;
                x -= size;
                break;
            case 1:
                column -= 1;
                row += 1;
                x -= size;
                y += size;
                break;
            case 2:
                row += 1;
                column += 1;
                y += size;
                x += size;
                break;
            case 3:
                column += 1;
                row -= 1;
                x += size;
                y -= size;
                break;
            }
        
            break;
            
        case 3:
            switch(position)
            {
                case 0:
                    row -= 2;
                    y -= size*2;
                    break;
                case 1:
                    column -= 2;
                    x -= size*2;
                    break;
                case 2:
                    row += 2;
                    y += size*2;
                    break;
                case 3:
                    column += 2;
                    x += size*2;
                    break;
            }
            
                break;
        
        case 4:
            break;
        }

    }

}
