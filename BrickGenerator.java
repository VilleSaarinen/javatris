import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.Vector;

public class BrickGenerator implements BrickGeneratorGraphicsInterface
{

    public static final int DIFFERENT_BRICKS_COUNT = 14;
    public static final int maxBricksInBlocks = 5;
    
    private int gameAreaWidth;   
    private int gameAreaHeight;
    private int gameAreaXStart;
    private int gameAreaYStart;
    private int brickSize;
    private int rows;
    private int columns;
    private GameAreaBrick[][] bricks;       //contains all the bricks the game grid
    private GameAreaBrick[] currentBlock;   //contains the current movable set of bricks, i.e. a block 
    private GameAreaBrick[] nextBlock;        //contains the block that comes next
    private boolean currentCreatedAndMovable; //if there's a block that can be moved, this is true
    private Random rand;
    private GraphicsInterface graphicsModule;
    private Semaphore lock;   //to make sure array Bricks[][] isn't modified while drawn by GraphicsInterface
    private ImageHandler images;
    Bonus bonus;
    private int maxDrop;

    //The following boolean values tell the graphicsmodule if the arrays have changed
    //These values should only be set "false" when returned to the graphics module
    private boolean currentChanged;
    private boolean nextChanged;
    private boolean arrayChanged;
    private boolean bonusChanged;
    private Vector<Integer> rowsToDelete;
    private Statistics stats;
    
    public BrickGenerator(int gameAreaWidth, int gameAreaHeight, int rows, int columns, int gameAreaStartX, 
            int gameAreaStartY, int brickSize, Statistics stats, ImageHandler image)
    {
        this.gameAreaWidth = gameAreaWidth;
        this.gameAreaHeight = gameAreaHeight;
        this.rows = rows;
        this.columns = columns;
        
        this.gameAreaXStart = gameAreaStartX;
        this.gameAreaYStart = gameAreaStartY;
        
        bricks = new GameAreaBrick[rows][columns];
        
        this.brickSize = brickSize;
        
        currentCreatedAndMovable = false;
        
        rand = new Random();
        
        currentChanged = true;
        nextChanged = true;
        arrayChanged = true;
        bonusChanged = true;
        rowsToDelete = new Vector<Integer>();
        
        this.stats = stats;
        
        this.images = image;
        
    }
    
    
    public int getBrickSize()
    {
        return brickSize;
    }
    
    
    public BufferedImage getNewBrickImage(boolean isBonus)
    {
        BufferedImage image = null;
        int modulo;
        
        //if bonus is created, let's try to get more bricks of that color! 
        if(!isBonus)
            modulo = 9;
        else
            modulo = 8;
        
        switch(Math.abs(rand.nextInt()) % modulo)
        {    
            case 0: image = images.getBrickImageRef(0);
                break;
            case 1: image = images.getBrickImageRef(1);
                break;
            case 2: image = images.getBrickImageRef(2);
                break;
            case 3: image = images.getBrickImageRef(3);
                break;
            case 4: image = images.getBrickImageRef(4);
                break;
            case 5: image = images.getBrickImageRef(5);
                break;
            case 6: image = images.getBrickImageRef(6);
                break;
            case 7: image = images.getBrickImageRef(7);
                break;
            case 8: image = bonus.getBonusColor();
                break;
          }
        
        return image;
    }
    
    private GameAreaBrick[] createBrick()
    {
        BufferedImage image = null;
        GameAreaBrick[] block = null;
        int blockType;

        image = getNewBrickImage(false);
        
        
        switch((blockType = Math.abs(rand.nextInt()) % DIFFERENT_BRICKS_COUNT))
        {
        case 0: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick1(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
            
        case 1: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick2(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
        
        case 2: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick3(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
            
        case 3: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick4(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
            
        case 4: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick5(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
            
        case 5: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick6(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
            
        case 6: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick7(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
      
        case 7: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick8(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
            
        case 8: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick9(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
            
        case 9: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick10(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
            
        case 10: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick11(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
            
        case 11: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick12(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
            
        case 12: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick13(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
            
        case 13: 
            block = new GameAreaBrick[GameAreaBrick.getBrickCount(blockType)];
            for(int i = 0; i < GameAreaBrick.getBrickCount(blockType); i++)
            {
                block[i] = new Brick14(gameAreaXStart, gameAreaYStart, brickSize , i+1, image);
            }
            break;
            
        }
        
        currentCreatedAndMovable = true;
        
        return block;
    }
    
    
    
    public boolean updateBricks(boolean first)
    {

        boolean brickCreated = false;
        
        try
        {
            lock.acquire();
        }
        catch (InterruptedException e) {
            return true;
        }
        
        if(!currentCreatedAndMovable)
        {
            if(!first)
            {
                for(int i = 0; i < currentBlock.length; i++)
                {
                    if(currentBlock[i] != null)
                    {
                        bricks[currentBlock[i].getRowIndex()][currentBlock[i].getColumnIndex()] = currentBlock[i];
                    }
                 }
                
                while(checkBonuses())
                {
                    bonus = Bonus.createBonus(brickSize, this.getNewBrickImage(true));
                    bonusChanged = true;
                    
                }
                
                if(checkRows())
                {
                    deleteRows();
                }
                
                arrayChanged = true;
                currentBlock = nextBlock;     
                
                for(int i = 0; i < currentBlock.length; i++)
                {
                    if(!isAbleToMove(currentBlock[i], currentBlock[i].getRowIndex()+1, currentBlock[i].getColumnIndex()))
                    {
                        lock.release();
                        return false;
                    }
                }
            }
            else
            {      
                bonus = Bonus.createBonus(brickSize, this.getNewBrickImage(true)); 
                currentBlock = createBrick(); 
            }
                
            nextBlock = createBrick();
            
            currentChanged = true;
            nextChanged = true;     
            brickCreated = true;
            
        }
        
        lock.release();

        if(!brickCreated)
            dropCurrent(1);
        
        maxDrop = calculateMaxDrop();
        
        return true;
        
    }

    
    private void dropRowsAboveThisRow(int row)
    {
        for(int i = row; i >= 0; i--)
        {
            for(int h = 0; h < columns; h++)
            {
                if(bricks[i][h] != null)
                {
                    bricks[i][h].dropBrick(1);
                    bricks[i+1][h] = bricks[i][h];
                    bricks[i][h] = null;
                }
            }
        }
        
    }

    
    private int calculateMaxDrop()
    {
        int maxDrop = rows;
        int drop;
        
        for(int i = 0; i < currentBlock.length; i++)
        {   
            for(drop = 0; isAbleToMove(currentBlock[i], currentBlock[i].getRowIndex()+drop+1, currentBlock[i].getColumnIndex()); drop++)  
                ; //do nothing, just calculate the drop

            if(drop < maxDrop)
                maxDrop = drop;
            
        }
        
        return maxDrop;
    }
    
    public void dropCurrent(int drop)
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
    
    
    private boolean checkRows()
    {
        
        boolean deleteThisRow;
        boolean retval = false;
        
        for(int i = 0; i < rows; i++)
        {
            deleteThisRow = false;
            
            for(int h = 0; h < columns; h++)
            {
                if(bricks[i][h] == null)
                    break;
                
                if(h == columns-1)
                    deleteThisRow = true;   
            }
            
            if(deleteThisRow)
            {
                retval = true;
                rowsToDelete.add(i);
            }
        }
        
        return retval;
        
    }
    
    
    public boolean checkBonuses()
    {
        Vector<Point> points;
        
        if((points = bonus.checkBonus(bricks)) == null)
            return false;
        
        for(Point point : points)
        {
            bricks[point.y][point.x] = null;
            System.out.println("Bonus!");
        }
        
        return true;
    }
    
    
    public void deleteRows()
    {  
        
        for(Iterator<Integer> it = rowsToDelete.iterator(); it.hasNext();)
        {
            int i = it.next();
            
            graphicsModule.animateRowDeletion(rowsToDelete);
            
            for(int h = 0; h < columns; h++)
            {
                bricks[i][h] = null;
            }
            
            dropRowsAboveThisRow(i);
            it.remove();
        }
        
    }
    
    
    public void moveSideways(int move)
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
        
        for(int h = 0; h < Math.abs(move); h++)
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
            currentChanged = true;
        }
        
        lock.release();
    }
    
    
    public void rotate(boolean clockWise)
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
        
        for(int i = 0; i < currentBlock.length; i++)
            currentBlock[i].rotate(clockWise);
        
        for(int i = 0; i < currentBlock.length; i++)
        {
            if(!isAbleToMove(currentBlock[i], currentBlock[i].getRowIndex(), currentBlock[i].getColumnIndex()))
            {
                for(int h = 0; h < currentBlock.length; h++)
                {
                    currentBlock[h].unrotate();
                }
                lock.release();
                return;
            }
        }
        
        currentChanged = true;
        lock.release();

    }
    
    
    
    private boolean isAbleToMove(GameAreaBrick brick, int rowToMove, int columnToMove)
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

    
    public GameAreaBrick[][] getGameAreaBricks()
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


    public GameAreaBrick[] getCurrentBrick() {
        
        if(currentChanged)
        {
            currentChanged = false;
            return currentBlock;
        }
        else
            return null;
    }


    public GameAreaBrick[] getNextBrick()
    {
        if(nextChanged)
        {
            nextChanged = false;
            return nextBlock;
        }
        else
            return null;
    }
    
    
    public Brick[] getBonus()
    {
        if(bonusChanged)
        {
            bonusChanged = false;
            return bonus.getBonusShape();
        }
        else 
            return null;
    }
    
    
    public int getMaxDrop()
    {
        return maxDrop;
    }
    

    public ImageHandler getImageHandler()
    {
        return images;
    }
}
