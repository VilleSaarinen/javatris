import java.awt.Point;
import java.awt.image.BufferedImage;

/*
 * 
 *      _
 *    _|_|_
 *   |_|_|_|
 *     |_|
 * 
 * 
 */



public class Brick8 extends GameAreaBrick
{

    public Brick8(int gameAreaStartX, int gameAreaStartY, int brickSize, int brickIndex, BufferedImage brickImage)
    {
        super(brickSize, brickIndex, brickImage);
        
        switch(brickIndex)
        {
        case 1:      
            this.row = 0;
            this.column = 10;
            this.relativeRow = 0;
            this.relativeColumn = 1;
            break;
        case 2:     
            row = 1;
            column = 9;
            this.relativeRow = 1;
            this.relativeColumn = 0;
            break;
        case 3:     
            row = 1;
            column = 10;
            this.relativeRow = 1;
            this.relativeColumn = 1;
            break;
        case 4:
            row = 1;
            column = 11;
            this.relativeRow = 1;
            this.relativeColumn = 2;
            break;
        case 5:
            row = 2;
            column = 10;
            this.relativeRow = 2;
            this.relativeColumn = 1;
            
        }

        this.x = gameAreaStartX + this.column*brickSize;
        this.y = gameAreaStartY + this.row*brickSize;  
        

    }


    @Override
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
                       row -= 1;
                       x -= size;
                       y -= size;
                       break;
                   case 1:
                       column -= 1;
                       row += 1;
                       x -= size;
                       y += size;
                       break;
                   case 2:
                       column += 1;
                       row += 1;
                       x += size;
                       y += size;
                       break;
                   case 3:
                       column += 1;
                       row -= 1;
                       x += size;
                       y -= size;
                       break;
               }
               break;

            case 2:
               switch(position)
               {
                   case 0:
                       column -= 1;
                       row += 1;
                       x -= size;
                       y += size;
                       break;
                   case 1:
                       column += 1;
                       row += 1;
                       x += size;
                       y += size;
                       break;
                   case 2:
                       column += 1;
                       row -= 1;
                       x += size;
                       y -= size;
                       break;
                   case 3:
                       column -= 1;
                       row -= 1;
                       x -= size;
                       y -= size;
                       break;
               }
               break;

            case 3:
               break;

            case 4:
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

            case 5:
               switch(position)
               {
                   case 0:
                       column += 1;
                       row += 1;
                       x += size;
                       y += size;
                       break;
                   case 1:
                       column += 1;
                       row -= 1;
                       x += size;
                       y -= size;
                       break;
                   case 2:
                       column -= 1;
                       row -= 1;
                       x -= size;
                       y -= size;
                       break;
                   case 3:
                       column -= 1;
                       row += 1;
                       x -= size;
                       y += size;
                       break;
               }

               break;
        }

    }

}
