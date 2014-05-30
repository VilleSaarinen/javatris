import java.awt.Color;

/*
 *   _ _
 *  |_|_|_
 *    |_|_|
 * 
 */


public class Brick1 extends Brick
{

	public Brick1(int brickX, int brickY, int brickWidth, int brickHeight, int brickIndex, Color brickColor, BrickType brickType)
	{
		super(brickX, brickY, brickWidth, brickHeight, brickIndex, brickColor, brickType);
		
		switch(brickIndex)
		{
		case 1:	  
			this.x = brickX;
	        this.y = brickY;
	        this.row = 1;
	        this.column = 7;
			break;
		case 2:	 
			this.x = brickX + brickWidth;
	        this.y = brickY;
	        row = 1;
	        column = 8;
			break;
		case 3:	 
			this.x = brickX + brickWidth;
	        this.y = brickY + brickHeight;
	        row= 2;
	        column = 8;
			break;
		case 4:
			this.x = brickX + brickWidth*2;
	        this.y = brickY + brickHeight;
	        row = 2;
	        column = 9;
			break;
		}
		
	    previousX = x;
	    previousY = y;  //TODO: are these really needed?
	

	}

	@Override
	public boolean rotate(boolean clockWise)
	{
		position++;
	    position %= 4;

	    previousX = x;
	    previousY = y;
	    previousColumn = column;
	    previousRow = row;

	    switch(index)
	    {
		case 1:
		    switch(position)
		    {
	        case 0:
	            column -= 1;
	            row += 1;
	            x -= width;
	            y += height;
	            break;
	        case 1:
	            column += 1;
	            row += 1;
	            x += width;
	            y += height;
	            break;
	        case 2:
	            column += 1;
	            row -= 1;
	            x += width;
	            y -= height;
	            break;
	        case 3:
	            column -= 1;
	            row -= 1;
	            x -= width;
	            y -= height;
	            break;
	        }

	        break;
	        
		case 2:
			break;

		case 3:
		    switch(position)
		    {
	        case 0:
	            column += 1;
	            row += 1;
	            x += width;
	            y += height;
	            break;
	        case 1:
	            column += 1;
	            row -= 1;
	            x += width;
	            y -= height;
	            break;
	        case 2:
	            column -= 1;
	            row -= 1;
	            x -= width;
	            y -= height;
	            break;
	        case 3:
	            column -= 1;
	            row += 1;
	            x -= width;
	            y += height;
	            break;
		    }

	        break;

		case 4:
		    switch(position)
		    {
		        case 0:
		            column += 2;
		            x += width*2;
		            break;
		        case 1:
		            row -= 2;
		            y -= height*2;
		            break;
		        case 2:
		            column -= 2;
		            x -= width*2;
		            break;
		        case 3:
		            row += 2;
		            y += height*2;
		            break;
		    }

		    break;
	    }
	    
		return false;  //TODO: is the return value needed?
	}

	@Override
	public Brick copyBrick()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
