import java.awt.image.BufferedImage;

/*
 * 
 * Base class for all bricks.
 * This is only a single brick, not for sets of bricks that
 * can be rotated in the game area.
 * 
 */

public class Brick
{
    protected int x;
    protected int y;
    protected int size;
    protected int row;
    protected int column; 
    protected int relativeRow;
    protected int relativeColumn;
    protected BufferedImage image;
    
    
    public Brick(int brickSize, BufferedImage brickImage)
    {
        this.size = brickSize;
        this.image = brickImage;
    }
    
    
    public Brick(int brickSize, BufferedImage brickImage, int row, int column, int relativeRow, int relativeColumn, int y, int x)
    {
        this.size = brickSize;
        this.image = brickImage;
        this.row = row;
        this.column = column;
        this.relativeRow = relativeRow;
        this.relativeColumn = relativeColumn;
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }

    public int getSize()
    {
        return size;
    }
    
    public int getRowIndex()
    {
        return row;
    }
    
    public int getColumnIndex()
    {
        return column;
    }
    
    public int getRelativeRowIndex()
    {
        return relativeRow;
    }
    
    public int getRelativeColumnIndex()
    {
        return relativeColumn;
    }
    
    public BufferedImage getImage()
    {
        return image;
    }
    
}
