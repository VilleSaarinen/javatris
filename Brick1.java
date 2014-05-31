import java.awt.image.BufferedImage;

/*
 *   _ _
 *  |_|_|_
 *    |_|_|
 * 
 */


public class Brick1 extends Brick
{

	public Brick1(int gameAreaStartX, int gameAreaStartY, int brickSize, int brickIndex, BufferedImage brickImage)
	{
		super(brickSize, brickIndex, brickImage);
		
		switch(brickIndex)
		{
		case 1:	  
	        this.row = 0;
	        this.column = 7;
			this.x = gameAreaStartX + this.column*brickSize;
	        this.y = gameAreaStartY + this.row*brickSize;
			break;
		case 2:	 
	        row = 0;
	        column = 8;
			this.x = gameAreaStartX + this.column*brickSize;
	        this.y = gameAreaStartY + this.row*brickSize;
			break;
		case 3:	 
	        row= 1;
	        column = 8;
			this.x = gameAreaStartX + this.column*brickSize;
	        this.y = gameAreaStartY + this.row*brickSize;
			break;
		case 4:
	        row = 1;
	        column = 9;
			this.x = gameAreaStartX + this.column*brickSize;
	        this.y = gameAreaStartY + this.row*brickSize;
			break;
		}
		
	    previousX = x;
	    previousY = y;  
	
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
	            x -= size;
	            y += size;
	            break;
	        case 1:
	            column += 1;
	            row += 1;
	            x += size;
	            y += size;
	            break;
	        case 2:
	            column += 1;
	            row -= 1;
	            x += size;
	            y -= size;
	            break;
	        case 3:
	            column -= 1;
	            row -= 1;
	            x -= size;
	            y -= size;
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
	            x += size;
	            y += size;
	            break;
	        case 1:
	            column += 1;
	            row -= 1;
	            x += size;
	            y -= size;
	            break;
	        case 2:
	            column -= 1;
	            row -= 1;
	            x -= size;
	            y -= size;
	            break;
	        case 3:
	            column -= 1;
	            row += 1;
	            x -= size;
	            y += size;
	            break;
		    }

	        break;

		case 4:
		    switch(position)
		    {
		        case 0:
		            column += 2;
		            x += size*2;
		            break;
		        case 1:
		            row -= 2;
		            y -= size*2;
		            break;
		        case 2:
		            column -= 2;
		            x -= size*2;
		            break;
		        case 3:
		            row += 2;
		            y += size*2;
		            break;
		    }

		    break;
	    }
	    
		return false;  //TODO: is the return value needed?
	}

}
