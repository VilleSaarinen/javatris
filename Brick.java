import java.awt.image.BufferedImage;

public abstract class Brick
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
