import java.awt.image.BufferedImage;


public abstract class Brick 
{

	enum BrickType{normal, smash, bonus, lightning, dynamite, downrow};
	
	protected int x;
	protected int y;
	protected int size;
	protected int row;
	protected int index;
	protected int column;
	protected int previousRow;
	protected int previousColumn;
	protected int previousX;
	protected int previousY;
	protected BufferedImage image;
	protected BrickType type;
	protected int position;    //there are 4 possible positions for each brick while rotated
	
	protected static int[] brickCount = {4};    //number of bricks in each different set of bricks
	
	
	public abstract boolean rotate(boolean clockWise);

	
	public Brick(int brickX, int brickY, int brickSize, int brickIndex, BufferedImage brickImage)
	{
		x = brickX;
		y = brickY;
		size = brickSize;
		index = brickIndex;
		image = brickImage;
		
	}
	
	
	//this is dirty, but the number of bricks in a block must be known before an instance is created
	public static int getBrickCount(int type)
	{
		return brickCount[type];
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
	
	public int getPreviousX()
	{
		return previousX;
	}
	
	public int getPreviousY()
	{
		return previousY;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public BrickType getBrickType()
	{
		return type;
	}
    
	public void dropBrick(int drop)
	{
	    previousY = y;
	    previousX = x;
	    previousColumn = column;
	    previousRow = row;
	    y += size*drop;
	    row += drop;
	}
	
    public void moveLeft(int move)
    {
	    previousY = y;
	    previousX = x;
	    previousColumn = column;
	    previousRow = row;
	    x -= size*move;
	    column -= move;
    }
    
    public void moveRight(int move)
    {
	    previousY = y;
	    previousX = x;
	    previousColumn = column;
	    previousRow = row;
	    x += size*move;
	    column += move;	
    }
	
}
