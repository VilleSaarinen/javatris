
public class GameEngine implements GameEngineUserAction
{
    
    private final int width = 900;
    private final int height = 900;
    private final int rows = 27;
    private final int columns = 20;
    private int gameAreaWidth;
    private int gameAreaHeight;
    private final int gameAreaXStart = 20;
    private final int gameAreaYStart = 20;   //TODO: all final parameters in a configuration file?
    private BrickGenerator brickGenerator;
    private int brickSize;
    private UserInput ui;
    private GraphicsEngine graphicsEngine;
    private Statistics stats;
    private ImageHandler images;

    
    
    public GameEngine()
    {

        ui = new UserInput(this);
        
        gameAreaWidth = width/9*6;
        gameAreaHeight = height/10*9;
        
        brickSize = gameAreaHeight/rows;
        
        images = new ImageHandler(brickSize, gameAreaWidth, gameAreaHeight);
        
        stats = new Statistics();
        
        graphicsEngine = new GraphicsEngine(ui, width, height, gameAreaWidth, gameAreaHeight, gameAreaXStart, 
                             gameAreaYStart, images, stats);            
    }
    
    public void startNewGame()
    {
        playTheGame();
    }
    
    
    private void startScreen()
    {
        
        
    }
    
    private void menuScreen()
    {
        
    }
    
    
    private void playTheGame() 
    {
                
        int updateTick;
        long startTime;
        long endTime;
        long waitTime;
        int counter;
        
        updateTick = 50; 
        
        stats.initStats();
        
        brickGenerator = new BrickGenerator(gameAreaWidth, gameAreaHeight, rows, columns, gameAreaXStart, gameAreaYStart, 
                                            brickSize, stats, images);
        
        graphicsEngine.start();
        
        graphicsEngine.addBrickGenerator(brickGenerator);
        
        brickGenerator.updateBricks(true);
            

        for(counter = 0; true; counter++)
        {
            startTime = System.currentTimeMillis();
            
            switch(stats.getLevel())
            {
               case 1:
                    if((counter % 15) == 0)
                    {
                        brickGenerator.updateBricks(false);
                    }
                    break;
                case 2:
                    if((counter % 13) == 0)
                    {
                        brickGenerator.updateBricks(false);
                    }
                    break;
                case 3:
                    if((counter % 11) == 0)
                    {
                        brickGenerator.updateBricks(false);
                    }
                    break;
                case 4:
                    if((counter % 9) == 0)
                    {
                        brickGenerator.updateBricks(false);
                    }
                    break;
                case 5:
                    if((counter % 7) == 0)
                    {
                        brickGenerator.updateBricks(false);
                    }
                    break;
                case 6:
                    if((counter % 6) == 0)
                    {
                        brickGenerator.updateBricks(false);
                    }
                    break;
                case 7:
                    if((counter % 5) == 0)
                    {
                        brickGenerator.updateBricks(false);
                    }
                    break;
                case 8:
                    if((counter % 4) == 0)
                    {
                        brickGenerator.updateBricks(false);
                    }
                    break;
                case 9:
                    if((counter % 3) == 0)
                    {
                        brickGenerator.updateBricks(false);
                    }
                    break;
                case 10:
                    if((counter % 2) == 0)
                    {
                        brickGenerator.updateBricks(false);
                    }
                    break;
            }
            
            endTime = System.currentTimeMillis();
            
            if((waitTime = endTime - startTime) <= updateTick)
            {
                try
                {
                    Thread.sleep(updateTick - waitTime);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            
        }
        
    }

    
    public void rotate(boolean clockWise)
    {
        brickGenerator.rotate(clockWise);
    }
    

    public void dropCurrent(int drop) 
    {
        brickGenerator.dropCurrent(drop);
    }

    
    public void moveSideways(int move)
    {
        brickGenerator.moveSideways(move);
    }
    
}
