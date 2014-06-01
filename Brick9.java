import java.awt.image.BufferedImage;


/*
 *       _
 *      |_|
 * 
 */


public class Brick9 extends Brick
{

    private int dropHeight;
    
    public Brick9(int gameAreaStartX, int gameAreaStartY, int brickSize, int brickIndex, BufferedImage brickImage)
    {
        super(brickSize, brickIndex, brickImage);
        
        row = 0;
        column = 8;
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
