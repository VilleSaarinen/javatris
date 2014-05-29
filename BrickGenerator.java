
public class BrickGenerator 
{

	private int width;
	private int height;
	private int rows;
	private int columns;
	private Brick[][] bricks;
	
	public BrickGenerator(int width, int height, int rows, int columns)
	{
		this.width = width;
		this.height = height;
		this.rows = rows;
		this.columns = columns;
		
		bricks = new Brick[rows][columns];
		
	}
	
	
}
