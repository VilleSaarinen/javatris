import java.awt.image.BufferedImage;
import java.util.Stack;
import java.awt.Point;

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
	protected Stack<Point> previousPoints;
	protected BufferedImage image;
	protected BrickType type;
	protected int position;    //there are 4 possible positions for each brick while rotated
	
	protected static int[] brickCount = {4};    //number of bricks in each different set of bricks
	
	
	public abstract boolean rotate(boolean clockWise);

	
	public Brick(int brickSize, int brickIndex, BufferedImage brickImage)
	{
		size = brickSize;
		index = brickIndex;
		image = brickImage;	
		
		previousPoints = new Stack<Point>();
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
	
	public Point getPreviousPoint()
	{
		
		if(previousPoints.empty())	
			return null;
		else
			return previousPoints.pop();
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
		previousPoints.push(new Point(x,y));
	    previousColumn = column;
	    previousRow = row;
	    y += size*drop;
	    row += drop;
	}
	
    public void moveLeft(int move)
    {
    	previousPoints.push(new Point(x,y));
	    previousColumn = column;
	    previousRow = row;
	    x -= size*move;
	    column -= move;
    }
    
    public void moveRight(int move)
    {
    	previousPoints.push(new Point(x,y));
	    previousColumn = column;
	    previousRow = row;
	    x += size*move;
	    column += move;	
    }
	
}
