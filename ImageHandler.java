import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;

//This class saves different images of bricks

public class ImageHandler
{
	private BufferedImage[] standardBricks;
	
	public ImageHandler(int size)
	{
		Graphics g;
		
		standardBricks = new BufferedImage[6];
		size = size-2;
		
		standardBricks[0] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB); 	
		g = standardBricks[0].getGraphics();
		g.setColor(new Color(50, 0, 50));
		g.fillRect(0, 0, size, size);
		
		standardBricks[1] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB); 	
		g = standardBricks[1].getGraphics();
		g.setColor(new Color(50, 0, 200));
		g.fillRect(0, 0, size, size);
		
		standardBricks[2] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB); 	
		g = standardBricks[2].getGraphics();
		g.setColor(new Color(0, 150, 50));
		g.fillRect(0, 0, size, size);
		
		standardBricks[3] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB); 	
		g = standardBricks[3].getGraphics();
		g.setColor(new Color(0, 0, 50));
		g.fillRect(0, 0, size, size);
		
		standardBricks[4] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB); 	
		g = standardBricks[4].getGraphics();
		g.setColor(new Color(255, 255, 50));
		g.fillRect(0, 0, size, size);
		
		standardBricks[5] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB); 	
		g = standardBricks[5].getGraphics();
		g.setColor(new Color(200, 0, 0));
		g.fillRect(0, 0, size, size);
		
	}
	
	
	public BufferedImage getImageRef(int index)
	{
		return standardBricks[index];
	}
	
	
}
