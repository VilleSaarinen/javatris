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
	protected int column;
	protected int previousX;
	protected int previousY;
	protected Color color;
	protected BrickType type;
	
	public Brick()
	{
		
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
