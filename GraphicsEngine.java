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
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;


public class GraphicsEngine extends Canvas implements Runnable, GraphicsInterface
{
    /**
     * TODO: find out what the hell is this
     */
    private static final long serialVersionUID = 1L;  
    //TODO: order variables
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
    private Graphics2D gameGraphics;
    private BufferedImage gameAreaBackground;
    private BufferedImage buffer;
    private long updateTick;
    private long previousTick;
    private long nextTick;
    private Thread thread;
    private BrickGeneratorGraphicsInterface brickGenerator;
    private Semaphore lock;
    private GameAreaBrick[][] bricks;
    private GameAreaBrick[] current;
    private GameAreaBrick[] next;
    private Brick[] bonus;
    private int bgCounter;
    private Point previousBrickCoordinates;
    private ImageHandler images;
    private Font textFont;
    private Font numberFont;
    private Font pointsFont;
    private Color fontColor;
    private Statistics stats;
    private Random gradientRandom;
    private Menu menu;
    private boolean gameStarted;
    private boolean menuScreen;
    private BufferedImage menuBackground;
    Graphics2D menuGraphics;

    
    
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
        
        gradientRandom = new Random();
        //initial values for the gradient background
        red1 = Math.abs(gradientRandom.nextInt()) % 256;
        green1 = Math.abs(gradientRandom.nextInt()) % 256;
        blue1 = Math.abs(gradientRandom.nextInt()) % 256;
        red2 =  Math.abs(gradientRandom.nextInt()) % 256;
        green2 = Math.abs(gradientRandom.nextInt()) % 256;
        blue2 = Math.abs(gradientRandom.nextInt()) % 256;
        
        r1 = r2 = g1 = g2 = b1 = b2 = true;
        
        buffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
        gameGraphics = (Graphics2D)buffer.getGraphics();
        
        gameAreaBackground = images.getGameAreaBackground();
            
        createGradientBackground(); 
        
        updateTick = 50;
        previousTick = System.currentTimeMillis();  //TODO: necessary?
        nextTick = System.currentTimeMillis();
        
        lock = new Semaphore(1);
        
        bgCounter = 0;
        
        thread = new Thread(this);
        lock = new Semaphore(1);
        
        previousBrickCoordinates = new Point();
        
        this.images = images;
        
        textFont = new Font("serif", Font.ITALIC, 30);
        numberFont = new Font("serif", Font.BOLD, 50);
        pointsFont = new Font(Font.MONOSPACED, Font.BOLD, 35);
        fontColor = new Color(200,10,100);
        
        this.stats = stats;
        
        gameStarted = false;
        menuScreen = false;
        
        thread.start();
    }
    
    
    private void createGradientBackground()
    {
        
        int rand = Math.abs(gradientRandom.nextInt()) % 30;

        if(red1 > 254)
            r1 = false;
        if(red1 < 10)
            r1 = true;
        if(red2 > 254)
            r2 = false;
        if(red2 < 10)
            r2 = true;
        if(green1 > 254)
            g1 = false;
        if(green1 < 10)
            g1 = true;
        if(green2 > 254)
            g2 = false;
        if(green2 < 10)
            g2 = true;  
        if(blue1 > 254)
            b1 = false;
        if(blue1 < 10)
            b1 = true;
        if(blue2 > 254)
            b2 = false;
        if(blue2 < 10)
            b2 = true; 
        
        //Randomly change some variables
        
        switch(rand)
        {
            case 0:
                if(red1 < 200 && red1 > 50)
                    r1 = !r1;
                break;
            case 1:
                if(red2 < 200 && red2 > 50)
                    r2 = !r2;
                break;
            case 2:
                if(green1 < 200 && green1 > 50)
                    g1 = !g1;
                break;
            case 3:
                if(green2 < 200 && green2 > 50)
                    g2 = !g2;
                break;
            case 4:
                if(blue1 < 200 && blue1 > 50)
                    b1 = !b1;
                break;
            case 5:
                if(blue2 < 200 && blue2 > 50)
                    b2 = !b2;
                break;
            default:
                break;
        }

        if(r1)
            red1++;            
        else
            red1--;
        
        if(r2)
            red2++;            
        else
            red2--;
        
        if(g1)
            green1++;            
        else 
            green1--;
        
        if(g2)
            green2++;
        else 
            green2--;
        
        if (b1)
            blue1++;            
        else 
            blue1--;
        
        if(b2)
            blue2++; 
        else
            blue2--;
                              
        bg = new GradientPaint(0, this.getHeight(), new Color(red1, green1, blue1), this.getWidth(), 0, new Color(red2, green2, blue2));
        
    }
    
    
    private void paintGameArea(Graphics graphics)
    {    
        
        GameAreaBrick[][] tempGameArea;
        GameAreaBrick[] tempCurrent;
        GameAreaBrick[] tempNext;
        Brick[] tempBonus;
        boolean bgUpdated = false;
        
        if(((bgCounter++)%8) == 0)  //TODO: background change rate should be configurable?
        {    
            createGradientBackground(); 
            gameGraphics.setPaint(bg);        
            gameGraphics.fillRect(0, 0,  windowWidth, windowHeight);  //TODO: paint only small area if the background is not changed
            gameGraphics.drawImage(gameAreaBackground, gameAreaXStart - 1, gameAreaYStart, this);
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
        tempBonus = brickGenerator.getBonus();
        
        
        if(tempGameArea != null || bgUpdated)
        {
            gameGraphics.drawImage(gameAreaBackground, gameAreaXStart - 1, gameAreaYStart, this);
            
            if(tempGameArea != null)
                bricks = tempGameArea;
            
            for(GameAreaBrick[] row : bricks)
            {
                for(GameAreaBrick brick : row)
                {
                    if(brick != null)
                    {
                        gameGraphics.drawImage(brick.getImage(), brick.getX(), brick.getY(), this);
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
        
        if(tempBonus != null || bgUpdated)
        {
            if(tempBonus != null)
                bonus = tempBonus;
            
            updateBonus();
        }
        
        
        lock.release();
        
        graphics.drawImage(buffer, 0, 0, windowWidth, windowHeight, this);
        graphics.dispose();
            
    }
    
    
    private void paintMenuScreen(Graphics g)
    {
        int i;
        MenuButton[] buttons = menu.getButtons();
        
        for(i = 0; i < buttons.length; i++)
        {
            if(buttons[i].statusChanged())
            {
                menuGraphics.drawImage(buttons[i].getCurrentImage(), buttons[i].getX(), buttons[i].getY(), this);
            }
        }
        
        g.drawImage(menuBackground, 0, 0, windowWidth, windowHeight, this);
        
        g.dispose();
    }
    
    
    public void update(Graphics g)
    {  
 
        if(gameStarted)
            paintGameArea(g);
        
        if(menuScreen)
            paintMenuScreen(g);
    }

    public void startGame(BrickGeneratorGraphicsInterface gi)
    {
        brickGenerator = gi;
        
        brickGenerator.registerGraphicsObject(this, lock);

        gameStarted = true;

    }
    
    
    public void gameOver()
    {
        gameStarted = false;
        System.out.println("GAME OVER");
    }
    
    
    public void startMenu(Menu menu, UserInput ui)
    {
        
        this.menu = menu;
        
        menuScreen = true;
        
        menuBackground = menu.getMenuBackground();
        menuGraphics = (Graphics2D)menuBackground.getGraphics();
        
        this.addMouseListener(ui);
        this.addMouseMotionListener(ui);

    }
    
    public void stopMenu(UserInput ui)
    {
        menuScreen = false;
        this.removeMouseListener(ui);
        this.removeMouseMotionListener(ui);
    }
    
    
    private void animateMenuStart()
    {
        
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
    

    public void animateRowDeletion(int row)
    {
        
        
    }

    public void animateRowDeletion(Vector<Integer> rowsToDelete)
    {
        Graphics graphics = this.getGraphics();
        int i;
        
        for(Iterator<Integer> it = rowsToDelete.iterator(); it.hasNext();)
        {
            i = it.next();
            gameGraphics.drawImage(gameAreaBackground.getSubimage(0, i*next[0].getSize(), gameAreaWidth, next[0].getSize()), 
                    gameAreaXStart, gameAreaYStart+i*next[0].getSize(), this);
            
        }
        
        graphics.drawImage(buffer, 0, 0, windowWidth, windowHeight, this);
        graphics.dispose();
    }
    
    private void updateCurrent(boolean bgUpdated)
    {
        if(!bgUpdated)
        {
            for(int i = 0; i < current.length; i++)
            {
                while((previousBrickCoordinates = current[i].getPreviousPoint()) != null)
                {
                    gameGraphics.drawImage(gameAreaBackground.getSubimage(previousBrickCoordinates.x - gameAreaXStart,
                            previousBrickCoordinates.y - gameAreaYStart, current[i].getSize(), current[i].getSize()),
                            previousBrickCoordinates.x-1, previousBrickCoordinates.y, this);
                }
            }
        }
    
        for(GameAreaBrick brick : current)
        {
            gameGraphics.drawImage(brick.getImage(), brick.getX(), brick.getY(), this);
        }
        
    }
    
    
    private void updateNext()
    {
        BufferedImage image = images.getInfoBackground();
        
        int xStart = gameAreaXStart + gameAreaWidth + 30;
        
        gameGraphics.drawImage(image, xStart, gameAreaYStart, 
                image.getWidth(), image.getHeight(), this);
        
        gameGraphics.setFont(textFont);
        gameGraphics.setColor(fontColor);
        
        gameGraphics.drawString("Next:", xStart + 10, gameAreaYStart + 30);
        
        for(GameAreaBrick brick : next)
        {
            gameGraphics.drawImage(brick.getImage(), xStart + 30 + brick.getRelativeColumnIndex()*brick.getSize(),
                    gameAreaYStart + 50 + brick.getRelativeRowIndex()*brick.getSize(), this);
        }
    }
    
    
    private void updateLevel()
    {
        //TODO: this can be also called if points or level is updated
        BufferedImage image = images.getInfoBackground();
        
        int xStart = gameAreaXStart + gameAreaWidth + 30;
        int yStart = gameAreaYStart + 400;
        
        gameGraphics.drawImage(image, xStart, yStart, 
                image.getWidth(), image.getHeight(), this);
        
        gameGraphics.setFont(textFont);
        gameGraphics.setColor(fontColor);
        
        gameGraphics.drawString("Level:", xStart + 10, yStart + 30);
        
        gameGraphics.setFont(numberFont);
        gameGraphics.drawString(Integer.toString(stats.getLevel()), xStart + 80, yStart + 120);
        
    }
    
    private void updatePoints()
    {
        //TODO: this can be also called if points or level is updated
        BufferedImage image = images.getInfoBackground();
        String points = Integer.toString(stats.getPoints());
        
        int xStart = gameAreaXStart + gameAreaWidth + 30;
        int yStart = gameAreaYStart + 200;
        int fontStartX = xStart + 120;
        
        fontStartX -= points.length()*(20);
        
        
        
        gameGraphics.drawImage(image, xStart, yStart, 
                image.getWidth(), image.getHeight(), this);
        
        gameGraphics.setFont(textFont);
        gameGraphics.setColor(fontColor);
        
        gameGraphics.drawString("Points:", xStart + 10, yStart + 30);
        
        gameGraphics.setFont(pointsFont);
        gameGraphics.drawString(points, fontStartX, yStart + 120);
        
    }

    
    private void updateBonus()
    {
        BufferedImage image = images.getInfoBackground();
        
        int xStart = gameAreaXStart + gameAreaWidth + 30;
        int yStart = gameAreaYStart + 600;
        
        
        
        gameGraphics.drawImage(image, xStart, yStart, 
                image.getWidth(), image.getHeight(), this);
        
        gameGraphics.setFont(textFont);
        gameGraphics.setColor(fontColor);
        
        gameGraphics.drawString("Bonus:", xStart + 10, yStart + 30); 
        
        for(Brick brick : bonus)
        {
            gameGraphics.drawImage(brick.getImage(), brick.getX() + xStart + 30, brick.getY() + yStart + 50, this);
        }
    }
    
}
