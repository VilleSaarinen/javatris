import java.awt.Point;
import java.awt.image.BufferedImage;

/*
 *         _ _
 *        |_|_|
 *        |_|_|    
 *         
 */


public class Brick5 extends Brick {

    public Brick5(int gameAreaStartX, int gameAreaStartY, int brickSize, int brickIndex, BufferedImage brickImage)
    {
        super(brickSize, brickIndex, brickImage);
        
        switch(brickIndex)
        {
        case 1:      
            this.row = 0;
            this.column = 8;
            this.relativeRow = 0;
            this.relativeColumn = 0;
            break;
        case 2:     
            row = 0;
            column = 9;
            this.relativeRow = 0;
            this.relativeColumn = 1;
            break;
        case 3:     
            row = 1;
            column = 8;
            this.relativeRow = 1;
            this.relativeColumn = 0;
            break;
        case 4:
            row = 1;
            column = 9;
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
                column -= 1;
                x -= size;
                break;
            case 1:
                row += 1;
                y += size;
                break;
            case 2:
                column += 1;
                x += size;
                break;
            case 3:
                row -= 1;
                y -= size;
                break;    
            }
        
            break;

        
        case 2:
            switch(position)
            {
            case 0:
                row -= 1;
                y -= size;
                break;
            case 1:
                column -= 1;
                x -= size;
                break;
            case 2:
                row += 1;
                y += size;
                break;
            case 3:
                column += 1;
                x += size;
                break;
            }
        
            break;
            
        case 3:
            switch(position)
            {
                case 0:
                    row += 1;
                    y += size;
                    break;
                case 1:
                    column += 1;
                    x += size;
                    break;
                case 2:
                    row -= 1;
                    y -= size;
                    break;
                case 3:
                    column -= 1;
                    x -= size;
                    break;
                }
            
                break;
        
        case 4:
            switch(position)
            {
                case 0:
                    column += 1;
                    x += size;
                    break;
                case 1:
                    row -= 1;
                    y -= size;
                    break;
                case 2:
                    column -= 1;
                    x -= size;
                    break;
                case 3:
                    row += 1;
                    y += size;
                    break;
                }
            
                break;
            }

    }

}
