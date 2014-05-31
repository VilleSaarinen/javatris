import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class BrickGenerator implements BrickGeneratorGraphicsInterface
{

	public static final int DIFFERENT_BRICKS_COUNT = 1;
	public static final int maxBricksInBlocks = 5;
	
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
	private ImageHandler images;

	//The following boolean values tell the graphicsmodule if the arrays have changed
	//These values should only be set "false" when returned to the graphics module
	private boolean currentChanged;
	private boolean nextChanged;
	private boolean arrayChanged;
	private Statistics stats;
	
	public BrickGenerator(int gameAreaWidth, int gameAreaHeight, int rows, int columns, int gameAreaStartX, 
			int gameAreaStartY, Statistics stats)
	{
		this.gameAreaWidth = gameAreaWidth;
		this.gameAreaHeight = gameAreaHeight;
		this.rows = rows;
		this.columns = columns;
		
		this.gameAreaXStart = gameAreaStartX;
		this.gameAreaYStart = gameAreaStartY;
		
		bricks = new Brick[rows][columns];
		
		brickSize = gameAreaHeight/rows;
		
		currentCreatedAndMovable = false;
		
		rand = new Random();
		
		currentChanged = true;
		nextChanged = true;
		arrayChanged = true;
		
		this.stats = stats;
		
		images = new ImageHandler(brickSize);
		
	}
	
	private Brick[] createBrick()
	{
		BufferedImage image = null;
		Brick[] block = null;
		int blockType;

		switch(Math.abs(rand.nextInt()) % 6)
		{
		    case 0: image = images.getImageRef(0);
		        break;
		    case 1: image = images.getImageRef(1);
		        break;
		    case 2: image = images.getImageRef(2);
		        break;
		    case 3: image = images.getImageRef(3);
		        break;
		    case 4: image = images.getImageRef(4);
		        break;
		    case 5: image = images.getImageRef(5);
		        break;
	  	}
		
		
		switch((blockType = Math.abs(rand.nextInt()) % DIFFERENT_BRICKS_COUNT))
		{
		case 0: 
			block = new Brick[Brick.getBrickCount(blockType)];
	        for(int i = 0; i < Brick.getBrickCount(blockType); i++)
	        {
	            block[i] = new Brick1(gameAreaWidth/rows*6 + gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
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
	                    bricks[currentBlock[i].getRowIndex()-1][currentBlock[i].getColumnIndex()-1] = currentBlock[i];
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
		
		lock.release();
		
		dropCurrent(1);
		
	}

	
	private void dropCurrent(int drop)
	{
		
		if(!currentCreatedAndMovable)
			return;
		
		try 
		{
			lock.acquire();
		} 
		catch (InterruptedException e)
		{
			return;
		}
		
		for(int h = 0; h < drop; h++)
		{
		
			for(int i = 0; i < currentBlock.length; i++)
			{
				if(!isAbleToMove(currentBlock[i], currentBlock[i].getRowIndex()+1, currentBlock[i].getColumnIndex()))
				{
					stats.dropBrick(h);
					currentCreatedAndMovable = false;
					lock.release();
					return;
				}

			}
			
			for(int i = 0; i < currentBlock.length; i++)
			{
				currentBlock[i].dropBrick(1);
			}
			
			currentChanged = true;
		}
		
		lock.release();
		
	}
	
	
	private void moveSideways(int move)
	{
		
		int unit; 
		boolean left;
		
		if(move >= 0)
		{
			unit = 1;
			left = false;
		}
		else
		{
			unit = -1;
			left = true;
		}
		
		
		if(!currentCreatedAndMovable)
			return;
		
		try 
		{
			lock.acquire();
		} 
		catch (InterruptedException e)
		{
			return;
		}		
		
		for(int h = 0; h <= Math.abs(move); h++)
		{
			for(int i = 0; i < currentBlock.length; i++)
			{
				if(!isAbleToMove(currentBlock[i], currentBlock[i].getRowIndex(), currentBlock[i].getColumnIndex() + unit))
				{
					lock.release();
					return;
				}
			}			
			
			for(int i = 0; i < currentBlock.length; i++)
			{
				if(left)
					currentBlock[i].moveLeft(1);
				else
					currentBlock[i].moveRight(1);
			}
			
		}
		
		lock.release();
	}
	
	
	private boolean isAbleToMove(Brick brick, int rowToMove, int columnToMove)
	{
		
	    if(rowToMove < 0 || columnToMove < 0 || rowToMove >= rows || columnToMove >= columns)
	        return false;

	    if(bricks[rowToMove][columnToMove] == null)
	         return true;
	    
	    return false;		
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
