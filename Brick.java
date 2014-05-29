import java.awt.Color;


public abstract class Brick 
{

	public static final int DIFFERENT_BRICKS_COUNT = 1;
	enum BrickType{normal, smash, bonus, lightning, dynamite, downrow};
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int row;
	protected int index;
	protected int column;
	protected int previousX;
	protected int previousY;
	protected Color color;
	protected BrickType type;
	
	public Brick(int brickX, int brickY, int brickWidth, int brickHeight, int brickIndex, Color brickColor, BrickType brickType)
	{
		x = brickX;
		y = brickY;
		width = brickWidth;
		height = brickHeight;
		index = brickIndex;
		color = brickColor;
		type = brickType;
		
	}
	
	public abstract boolean rotate(boolean clockWise);
	public abstract int getBrickCount();
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
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
	
	public Color getColor()
	{
		return color;
	}
	
	public BrickType getBrickType()
	{
		return type;
	}
    
	public void dropBrick(int drop)
	{
		
	}
	
    public void moveLeft(int move)
    {
    	
    }
    
    void moveRight(int move)
    {
    	
    	
    }
	
	
}
