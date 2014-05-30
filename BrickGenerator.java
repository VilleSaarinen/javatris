import java.awt.Color;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class BrickGenerator implements BrickGeneratorGraphicsInterface
{

	public static final int DIFFERENT_BRICKS_COUNT = 1;
	
	private int gameAreaWidth;   
	private int gameAreaHeight;
	private int gameAreaXStart;
	private int gameAreaYStart;
	private int brickSize;
	private int rows;
	private int columns;
	private Brick[][] bricks;   	//contains all the bricks the game grid
	private Brick[] currentBlock;   //contains the current movable set of bricks, i.e. a block 
	private Brick[] nextBlock;		//contains the block that comes next
	private boolean currentCreatedAndMovable; //if there's a block that can be moved, this is true
	private Random rand;
	private GraphicsInterface graphicsModule;
	private Semaphore lock;   //to make sure array Bricks[][] isn't modified while drawn by GraphicsInterface
	
	//The following boolean values tell the graphicsmodule if the arrays have changed
	//These values should only be set "false" when returned to the graphics module
	private boolean currentChanged;
	private boolean nextChanged;
	private boolean arrayChanged;
	
	public BrickGenerator(int gameAreaWidth, int gameAreaHeight, int rows, int columns)
	{
		this.gameAreaWidth = gameAreaWidth;
		this.gameAreaHeight = gameAreaHeight;
		this.rows = rows;
		this.columns = columns;
		
		this.gameAreaXStart = 20;
		this.gameAreaYStart = 20; //TODO: as a parameter?
		
		bricks = new Brick[rows][columns];
		
		brickSize = gameAreaWidth/rows;
		
		currentCreatedAndMovable = false;
		
		rand = new Random();
		
		currentChanged = true;
		nextChanged = true;
		arrayChanged = true;
		
	}
	
	private Brick[] createBrick()
	{
		Color color = null;
		Brick[] block = null;
		int blockType;

		switch(Math.abs(rand.nextInt()) % 6)
		{
		    case 0: color = new Color(50, 0, 50);
		        break;
		    case 1: color = new Color(50, 0, 200);
		        break;
		    case 2: color = new Color(0, 153, 50);
		        break;
		    case 3: color = new Color(0, 0, 50);
		        break;
		    case 4: color = new Color(255, 255, 50);
		        break;
		    case 5: color = new Color(200, 0, 0);
		        break;
	  	}
		
		
		switch((blockType = Math.abs(rand.nextInt()) % DIFFERENT_BRICKS_COUNT))
		{
		case 0: 
			block = new Brick[Brick.getBrickCount(blockType)];
	        for(int i = 0; i < Brick.getBrickCount(blockType); i++)
	        {
	            block[i] = new Brick1(gameAreaWidth/rows*6 + gameAreaXStart, gameAreaYStart, brickSize , i+1, color);
	        }
			
			break;
		}
		
		currentCreatedAndMovable = true;
		
		return block;
	}
	
	
	
	public void updateBricks(boolean first)
	{

		try
		{
			lock.acquire();
		}
		catch (InterruptedException e) {
			return;
		}
		
		if(!currentCreatedAndMovable)
		{
			if(!first)
			{
	            for(int i = 0; i < currentBlock.length; i++)
	            {
	                if(currentBlock[i] != null)
	                {
	                    bricks[currentBlock[i].getRowIndex()-1][currentBlock[i].getColumnIndex()-1] = currentBlock[i].copyBrick();
	                }
	             }
	            
	            arrayChanged = true;
	            currentBlock = nextBlock;
	            
			}
			else
			{
	            currentBlock = createBrick();				
			}
				
			nextBlock = createBrick();
			
			currentChanged = true;
			nextChanged = true;
			
			
		}

		//dropCurrent here

		
		lock.release();
		
	}


	public int getRowCount()
	{
		return rows;
	}


	public int getColumnCount()
	{
		return columns;
	}

	
	public Brick[][] getGameAreaBricks()
	{		
		if(arrayChanged)
		{
			arrayChanged = false;
			return bricks;
		}
		else
			return null;
	}


	public void registerGraphicsObject(GraphicsInterface gi, Semaphore lock)
	{
		graphicsModule = gi;
		this.lock = lock;
	}


	public Brick[] getCurrentBrick() {
		
		if(currentChanged)
		{
			currentChanged = false;
			return currentBlock;
		}
		else
			return null;
	}


	public Brick[] getNextBrick()
	{
		if(nextChanged)
		{
			nextChanged = false;
			return nextBlock;
		}
		else
			return null;
	}
	
	
}
