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
import java.awt.Font;


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
    private BufferedImage gameAreaBackground;
    private Graphics2D gameAreaGraphics;
    private BufferedImage buffer;
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
    private ImageHandler images;
    private Font textFont;
    private Font numberFont;
    private Font pointsFont;
    private Color fontColor;
    private Statistics stats;

    
    
    private class CloseWindow extends WindowAdapter
    {         
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }     
    }
    
    public GraphicsEngine(UserInput ui, int windowWidth, int windowHeight, int gameAreaWidth, int gameAreaHeight,
            int gameAreaXStart, int gameAreaYStart, ImageHandler images, Statistics stats) 
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
        
        gameAreaBackground = new BufferedImage(gameAreaWidth + 2, gameAreaHeight, BufferedImage.TYPE_INT_RGB);
        gameAreaGraphics = (Graphics2D)gameAreaBackground.getGraphics();
        gameAreaGraphics.setColor(new Color(10,10,10));
        gameAreaGraphics.drawRect(0, 0, gameAreaWidth, gameAreaHeight);
        gameAreaGraphics.fillRect(0, 0, gameAreaWidth, gameAreaHeight);
                
        createGradientBackground(); 
        updateTick = 50;
        previousTick = System.currentTimeMillis();  //TODO: necessary?
        nextTick = System.currentTimeMillis();
        
        thread = new Thread(this);
        lock = new Semaphore(1);
        
        bgCounter = 0;
        
        previousBrickCoordinates = new Point();
        
        lineColor = new Color(10, 10, 10);
        
        this.images = images;
        
        textFont = new Font("serif", Font.ITALIC, 30);
        numberFont = new Font("serif", Font.BOLD, 50);
        pointsFont = new Font(Font.MONOSPACED, Font.BOLD, 35);
        fontColor = new Color(200,10,100);
        
        this.stats = stats;
        
    }
    
    
    private void createGradientBackground()
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
        
    }
    
    
    public void paint(Graphics graphics)
    {    
        
        Brick[][] tempGameArea;
        Brick[] tempCurrent;
        Brick[] tempNext;
        boolean bgUpdated = false;
        
        
        if(((bgCounter++)%8) == 0)
        {    
            createGradientBackground(); 
            g.setPaint(bg);        
            g.fillRect(0, 0,  windowWidth, windowHeight);  //TODO: paint only small area if the background is not changed
            g.drawImage(gameAreaBackground, gameAreaXStart - 2, gameAreaYStart, this);
            updateLevel();
            updatePoints();
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
            
            updateCurrent(bgUpdated);
            
        }
        
        if(tempNext != null || bgUpdated )
        {    
            if(tempNext != null)
                next = tempNext;
            
            updateNext();
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
                if(sleepTime >= 0)
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

    public void animateRowDeletion(int row)
    {
        // TODO: add row deletion animation
        
    }

    
    private void updateCurrent(boolean bgUpdated)
    {
        if(!bgUpdated)
        {
            for(int i = 0; i < current.length; i++)
            {
                while((previousBrickCoordinates = current[i].getPreviousPoint()) != null)
                {
                    g.drawImage(gameAreaBackground.getSubimage(previousBrickCoordinates.x - gameAreaXStart,
                            previousBrickCoordinates.y - gameAreaYStart, current[i].getSize(), current[i].getSize()),
                            previousBrickCoordinates.x, previousBrickCoordinates.y, this);
                }
            }
        }
    
        for(Brick brick : current)
        {
            g.drawImage(brick.getImage(), brick.getX(), brick.getY(), this);
        }
        
    }
    
    
    private void updateNext()
    {
        BufferedImage image = images.getInfoBackground();
        
        int xStart = gameAreaXStart + gameAreaWidth + 30;
        
        g.drawImage(image, xStart, gameAreaYStart, 
                image.getWidth(), image.getHeight(), this);
        
        g.setFont(textFont);
        g.setColor(fontColor);
        
        g.drawString("Next:", xStart + 10, gameAreaYStart + 30);
        
        for(Brick brick : next)
        {
            g.drawImage(brick.getImage(), xStart + 30 + brick.getRelativeColumnIndex()*brick.getSize(),
                    gameAreaYStart + 50 + brick.getRelativeRowIndex()*brick.getSize(), this);
        }
    }
    
    
    private void updateLevel()
    {
        BufferedImage image = images.getInfoBackground();
        
        int xStart = gameAreaXStart + gameAreaWidth + 30;
        int yStart = gameAreaYStart + 400;
        
        g.drawImage(image, xStart, yStart, 
                image.getWidth(), image.getHeight(), this);
        
        g.setFont(textFont);
        g.setColor(fontColor);
        
        g.drawString("Level:", xStart + 10, yStart + 30);
        
        g.setFont(numberFont);
        g.drawString(Integer.toString(stats.getLevel()), xStart + 80, yStart + 120);
        
    }
    
    private void updatePoints()
    {
        BufferedImage image = images.getInfoBackground();
        String points = Integer.toString(stats.getPoints());
        
        int xStart = gameAreaXStart + gameAreaWidth + 30;
        int yStart = gameAreaYStart + 200;
        int fontStartX = xStart + 120;
        
        fontStartX -= points.length()*(20);
        
        
        
        g.drawImage(image, xStart, yStart, 
                image.getWidth(), image.getHeight(), this);
        
        g.setFont(textFont);
        g.setColor(fontColor);
        
        g.drawString("Points:", xStart + 10, yStart + 30);
        
        g.setFont(pointsFont);
        g.drawString(points, fontStartX, yStart + 120);
        
    }
    
}
