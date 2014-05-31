import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.Semaphore;
import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;


public class GraphicsEngine extends Canvas implements Runnable, GraphicsInterface
{
	/**
	 * TODO: find out what the hell is this
	 */
	private static final long serialVersionUID = 1L;  
	
	private Frame frame;
	private int windowWidth;
	private int windowHeight;
	private int gameAreaWidth;
	private int gameAreaHeight;
	private int gameAreaXStart;
	private int gameAreaYStart;
	private GradientPaint bg;
	private int red1, green1, blue1, red2, green2, blue2;
	private boolean r1, r2, g1, g2, b1, b2;
	private Graphics2D g;
	private Graphics2D bgGraphics;
	private BufferedImage buffer;
	BufferedImage bgBuffer;
	private long updateTick;
	private long previousTick;
	private long nextTick;
	private Thread thread;
	private BrickGeneratorGraphicsInterface brickGenerator;
	private Semaphore lock;
	private Brick[][] bricks;
	private Brick[] current;
	private Brick[] next;
	private int bgCounter;
	private Color lineColor;
	private Point previousBrickCoordinates;
	
	
	private class CloseWindow extends WindowAdapter
	{		 
		    public void windowClosing(WindowEvent we)
		    {
		    	System.exit(0);
		    }	 
	}
	
	public GraphicsEngine(UserInput ui, int windowWidth, int windowHeight, int gameAreaWidth, int gameAreaHeight,
			int gameAreaXStart, int gameAreaYStart) 
	{
		super();
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.gameAreaWidth = gameAreaWidth;
		this.gameAreaHeight = gameAreaHeight; 
		this.gameAreaXStart = gameAreaXStart;
		this.gameAreaYStart = gameAreaYStart; 
		
		frame = new Frame();
        frame.setSize(windowWidth, windowHeight);
        frame.setVisible(true);
        this.setSize(windowWidth, windowHeight);
        frame.add(this);
        frame.addWindowListener(new CloseWindow());
        
        //TODO: add other listeners as well
        this.addKeyListener(ui);
        
        //initial values for the gradient background
        red1 = 166;
        green1 = 254;
        blue1 = 200;
        red2 =  254;
        green2 = 254;
        blue2 = 254;
        
        r1 = r2 = g1 = g2 = b1 = b2 = true;
        
        buffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D)buffer.getGraphics();
        
        bgBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
        
        createBackground(); 
        updateTick = 50;
        previousTick = System.currentTimeMillis();  //TODO: necessary?
        nextTick = System.currentTimeMillis();
        
        thread = new Thread(this);
        lock = new Semaphore(1);
        
        bgCounter = 0;
        
        previousBrickCoordinates = new Point();
        
        lineColor = new Color(10, 10, 10);
		
	}
	
	
	private void createBackground()
	{

		if (red1 >220)
			r1 = false;
		if (red1< 130)
			r1 = true;
		if (red2 >254)
			r2 = false;
		if (red2< 222)
			r2 = true;
		if (green1 >254)
			g1 = false;
		if (green1< 190)
			g1 = true;
		if (green2 >254)
			g2 = false;
		if (green2< 90)
			g2 = true;  
		if (blue1 >210)
			b1 = false;
		if (blue1< 170)
			b1 = true;
		if (blue2 >254)
			b2 = false;
		if (blue2< 120)
			b2 = true; 
		
  
	    if (r1) red1++;	        
        else red1 = red1-1;
	    
	    if (r2) red2++;	        
        else red2 = red2-1;
	    
	    if (g1) green1++;	        
        else green1 = green1-1;
	    
	    if (g2)green2++;
        else green2 = green2-1;
	    
	    if (b1)blue1++;	        
        else blue1 = blue1-1;
	    
	    if (b2) blue2++; 
        else blue2 = blue2-1;
                              
	    bg = new GradientPaint(0, this.getHeight(), new Color(red1, green1, blue1), this.getWidth(), 0, new Color(red2, green2, blue2));
	    
	    bgGraphics = (Graphics2D)bgBuffer.getGraphics();
	    bgGraphics.setPaint(bg);
	    bgGraphics.fillRect(gameAreaXStart, gameAreaYStart,  gameAreaWidth, gameAreaHeight);
	    
	}
	
	
	public void paint(Graphics graphics)
	{	
		
		Brick[][] tempGameArea;
		Brick[] tempCurrent;
		Brick[] tempNext;
		boolean bgUpdated = false;
		
		
		if(((bgCounter++)%8) == 0)
		{	
			createBackground(); 
			g.setPaint(bg);		
	    	g.fillRect(0, 0,  windowWidth, windowHeight);  //TODO: paint only small area if the background is not changed
	    	drawGameAreaLimits(g);
	    	
	    	bgUpdated = true;
		}
		
		try
		{
			lock.acquire();
		} 
		catch (InterruptedException e)
		{
			return;
		}

		
		tempGameArea = brickGenerator.getGameAreaBricks();
		tempCurrent = brickGenerator.getCurrentBrick();
		tempNext = brickGenerator.getNextBrick();
		
		
		if(tempGameArea != null || bgUpdated)
		{
			if(tempGameArea != null)
				bricks = tempGameArea;
			
			for(Brick[] row : bricks)
			{
				for(Brick brick : row)
				{
					if(brick != null)
					{
						g.drawImage(brick.getImage(), brick.getX(), brick.getY(), this);
					}
				}			
			}
		}
		
		if(tempCurrent != null || bgUpdated)
		{	
			if(tempCurrent != null)
				current = tempCurrent;
			
	    	
			if(!bgUpdated)
			{
				for(int i = 0; i < current.length; i++)
		    	{
		    		while((previousBrickCoordinates = current[i].getPreviousPoint()) != null)
		    		{
		    			g.drawImage(bgBuffer.getSubimage(previousBrickCoordinates.x, previousBrickCoordinates.y, 
		    					current[i].getSize(), current[i].getSize()), previousBrickCoordinates.x, previousBrickCoordinates.y, this);
		    		}
		    	}
			}
		
			for(Brick brick : current)
			{
				g.drawImage(brick.getImage(), brick.getX(), brick.getY(), this);
			}
		}
		
		if(tempNext != null || bgUpdated )
		{	
			if(tempNext != null)
				next = tempNext;
			
			for(Brick brick : next)
			{
				g.drawImage(brick.getImage(), brick.getX(), brick.getY(), this);
			}
		}
		
		
		lock.release();
		
    	graphics.drawImage(buffer, 0, 0, windowWidth, windowHeight, this);
    	graphics.dispose();
    		
	}
	
	
	public void update(Graphics g)
	{  
		paint(g);
	}

	public void start()
	{
		thread.start();
	}
	
	
	public void run() 
	{
		
		long sleepTime;
		
		while(true)
		{
		
			sleepTime = updateTick - (nextTick - previousTick);

			try 
			{
				Thread.sleep(sleepTime);
			}catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			previousTick = System.currentTimeMillis();
			repaint();
			nextTick = System.currentTimeMillis();
			
		
		}
		
	}
	
	
	public void addBrickGenerator(BrickGeneratorGraphicsInterface gi)
	{
		brickGenerator = gi;
		
		if(lock == null)
			lock = new Semaphore(1);
		
		brickGenerator.registerGraphicsObject(this, lock);
	}
	
	
	private void drawGameAreaLimits(Graphics g)
	{
		g.setColor(lineColor);
		g.drawLine(gameAreaXStart, gameAreaYStart, gameAreaXStart + gameAreaWidth, gameAreaYStart);
		g.drawLine(gameAreaXStart, gameAreaYStart, gameAreaXStart, gameAreaYStart + gameAreaHeight);
		g.drawLine(gameAreaXStart + gameAreaWidth, gameAreaYStart, gameAreaXStart + gameAreaWidth, gameAreaYStart + gameAreaHeight);
		g.drawLine(gameAreaXStart, gameAreaYStart + gameAreaHeight, gameAreaXStart + gameAreaWidth, gameAreaYStart + gameAreaHeight);
	}
	

	public void animateRowDeletion(int row)
	{
		// TODO: add row deletion animation
		
	}

}
