import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


//This class saves different images of bricks

public class ImageHandler
{
    public final int infoBackgroundWidth = 180;
    public final int infoBackgroundHeight = 170;
    
    private int gameAreaWidth;
    private int gameAreaHeight;
    
    private BufferedImage[] standardBricks;
    private BufferedImage gameAreaBackground;
    private BufferedImage infoBackground;
    private Color infoBackgroundColor;
    
    public ImageHandler(int size, int gameAreaWidth, int gameAreaHeight)
    {

        
        this.gameAreaWidth = gameAreaWidth;
        this.gameAreaHeight = gameAreaHeight;
        
        standardBricks = new BufferedImage[8];
        size = size-2;
        
        //TODO: handle also Windows-type paths
        createBrickImages(size);
        createGameAreaBackground();
        createInfoImages();

    }
    
    
    private void createBrickImages(int size)
    {
        
        Graphics g;      

        try
        {
            standardBricks[0] = ImageIO.read(new File("src/images/brick1.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Image \"brick1.jpg\" not found!\n");
            standardBricks[0] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB); 
            g = standardBricks[0].getGraphics();
            g.setColor(new Color(50, 0, 50));
            g.fillRect(0, 0, size, size);
        }
        
        try
        {
            standardBricks[1] = ImageIO.read(new File("src/images/brick2.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Image \"brick2.jpg\" not found!\n");
            standardBricks[1] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);     
            g = standardBricks[1].getGraphics();
            g.setColor(new Color(50, 0, 200));
            g.fillRect(0, 0, size, size);
        }
        
        try
        {
            standardBricks[2] = ImageIO.read(new File("src/images/brick3.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Image \"brick3.jpg\" not found!\n");
            standardBricks[2] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);     
            g = standardBricks[2].getGraphics();
            g.setColor(new Color(0, 150, 50));
            g.fillRect(0, 0, size, size);
        }
        
        try
        {
            standardBricks[3] = ImageIO.read(new File("src/images/brick4.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Image \"brick4.jpg\" not found!\n");
            standardBricks[3] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);     
            g = standardBricks[3].getGraphics();
            g.setColor(new Color(0, 0, 50));
            g.fillRect(0, 0, size, size);
        }
        
        try
        {
            standardBricks[4] = ImageIO.read(new File("src/images/brick5.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Image \"brick5.jpg\" not found!\n");
            standardBricks[4] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);     
            g = standardBricks[4].getGraphics();
            g.setColor(new Color(255, 255, 50));
            g.fillRect(0, 0, size, size);
        }
        
        try
        {
            standardBricks[5] = ImageIO.read(new File("src/images/brick6.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Image \"brick6.jpg\" not found!\n");
            standardBricks[5] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);     
            g = standardBricks[5].getGraphics();
            g.setColor(new Color(200, 0, 0));
            g.fillRect(0, 0, size, size);
        }
        
        try
        {
            standardBricks[6] = ImageIO.read(new File("src/images/brick7.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Image \"brick7.jpg\" not found!\n");
            standardBricks[6] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);     
            g = standardBricks[6].getGraphics();
            g.setColor(new Color(250, 250, 250));
            g.fillRect(0, 0, size, size);
        }
        
        try
        {
            standardBricks[7] = ImageIO.read(new File("src/images/brick8.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Image \"brick8.jpg\" not found!\n");
            standardBricks[7] = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);     
            g = standardBricks[7].getGraphics();
            g.setColor(new Color(200, 0, 200));
            g.fillRect(0, 0, size, size);
        }
        
    }
    
    
    private void createGameAreaBackground()
    {
        try
        {
            gameAreaBackground = ImageIO.read(new File("src/images/gamearea_bg.jpg"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.err.println("Game area background image not found!\n");
            gameAreaBackground = new BufferedImage(gameAreaWidth + 2, gameAreaHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D gameAreaGraphics = (Graphics2D)gameAreaBackground.getGraphics();
            gameAreaGraphics.setColor(new Color(10,10,10));
            gameAreaGraphics.drawRect(0, 0, gameAreaWidth, gameAreaHeight);
            gameAreaGraphics.fillRect(0, 0, gameAreaWidth, gameAreaHeight);
        }  
    }
    
    
    private void createInfoImages()
    {
        Graphics g;
        
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
    
    public BufferedImage getGameAreaBackground()
    {
        return gameAreaBackground;
    }
    
}
