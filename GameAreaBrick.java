import java.awt.image.BufferedImage;
import java.util.Stack;
import java.awt.Point;

/*
 * 
 * Base class for bricks that can be rotated (i.e. game area bricks).
 * 
 */

public abstract class GameAreaBrick extends Brick
{

    enum BrickType{normal, smash, bonus, lightning, dynamite, downrow};
    
    protected int index;
    protected int previousRow;
    protected int previousColumn;
    protected Stack<Point> previousPoints;   //this is for the graphics module to hide previous positions on the screen
    protected int previousX;
    protected int previousY;
    protected BrickType type;
    protected int position;    //there are 4 possible positions for each brick while rotated
    protected GameAreaBrick ghostBrick;
   
    //number of bricks in each different set of bricks from 1 to 12
    protected static int[] brickCount = {4, 4, 4, 4, 4, 2, 4, 5, 1, 5, 4, 3, 5, 5};    
    
    
    public abstract void rotate(boolean clockWise);   //TODO: default is currently anti-clockwise, is the parameter needed?
    //TODO: when brick is drawn, all the previous points from the stack should be cleared
    
    public GameAreaBrick(int brickSize, int brickIndex, BufferedImage brickImage)
    {
        
        super(brickSize, brickImage);
        
        index = brickIndex; 
        
        previousX = 0;
        previousY = 0;
        
        previousPoints = new Stack<Point>();
        
        position = 0;
    }
    
    
    //this is dirty, but the number of bricks in a block must be known before an instance is created
    public static int getBrickCount(int type)
    {
        return brickCount[type];
    }
    
    
    protected void setPreviousPosition()
    {
        position++;
        position %= 4;
        previousX = x;
        previousY = y;
        
        previousColumn = column;
        previousRow = row;
        previousPoints.push(new Point(x,y));
    }
    
    
    
    public Point getPreviousPoint()
    {
        
        if(previousPoints.empty())    
            return null;
        else
            return previousPoints.pop();
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
    
    public void unrotate()
    {    
        x = previousX;
        y = previousY;
        column = previousColumn;
        row = previousRow;
        position--;        
        previousPoints.pop();
    }
    
}
