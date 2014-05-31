import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;


//This class saves different images of bricks

public class ImageHandler
{
	public final int infoBackgroundWidth = 180;
	public final int infoBackgroundHeight = 170;
	
	private BufferedImage[] standardBricks;
	private BufferedImage infoBackground;
	private Color infoBackgroundColor;
	
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
		
		infoBackgroundColor = new Color(50, 50, 50);
		
		infoBackground = new BufferedImage(infoBackgroundWidth, infoBackgroundHeight, BufferedImage.TYPE_INT_RGB);
		g = infoBackground.getGraphics();
		g.setColor(infoBackgroundColor);
		g.fillRect(0, 0, infoBackgroundWidth, infoBackgroundHeight);
	}
	
	
	public BufferedImage getImageRef(int index)
	{
		return standardBricks[index];
	}
	
	
	public BufferedImage getInfoBackground()
	{
		return infoBackground;
	}
	
	
}
