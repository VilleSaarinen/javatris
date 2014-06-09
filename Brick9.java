import java.awt.image.BufferedImage;


/*
 *  This is a special type of brick that can
 *  be used to destroy other bricks by dropping it.
 *       _
 *      |_|
 * 
 */


public class Brick9 extends GameAreaBrick
{

    private int dropHeight;
    
    public Brick9(int gameAreaStartX, int gameAreaStartY, int brickSize, int brickIndex, BufferedImage brickImage)
    {
        super(brickSize, brickIndex, brickImage);
        
        row = 0;
        column = 10;
        this.relativeRow = 0;
        this.relativeColumn = 0;

        this.x = gameAreaStartX + this.column*brickSize;
        this.y = gameAreaStartY + this.row*brickSize;  
        
        dropHeight = 0;
           
    }


    public void rotate(boolean clockWise)
    {
        //nothing has to be done
    }

    
    public void setDropHeight(int drop)
    {
        this.dropHeight = drop;
    }
    
}
