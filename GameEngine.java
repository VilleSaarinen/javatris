
public class GameEngine
{
	
	private final int width = 1000;
	private final int height = 1000;
	private final int rows = 15;
	private final int columns = 30;
	private BrickGenerator brickHandler;
	private UserInput ui;
	private GraphicsEngine graphicsEngine;
	private Statistics stats;


	
	
	public GameEngine()
	{

		ui = new UserInput();
		
		graphicsEngine = new GraphicsEngine(ui, width, height);
				
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
		
		
		brickHandler = new BrickGenerator(width, height, rows, columns);
		stats = new Statistics();
		
		graphicsEngine.start();

		for(counter = 0; true; counter++)
		{
			startTime = System.currentTimeMillis();
			System.out.println("jipii");
			switch(stats.getLevel())
			{
	           case 1:
	                if((counter % 15) == 0)
	                {

	                }
	                break;
	            case 2:
	                if((counter % 13) == 0)
	                {
	
	                }
	                break;
	            case 3:
	                if((counter % 11) == 0)
	                {
	                	
	                }
	                break;
	            case 4:
	                if((counter % 9) == 0)
	                {

	                }
	                break;
	            case 5:
	                if((counter % 7) == 0)
	                {

	                }
	                break;
	            case 6:
	                if((counter % 6) == 0)
	                {

	                }
	                break;
	            case 7:
	                if((counter % 5) == 0)
	                {

	                }
	                break;
	            case 8:
	                if((counter % 4) == 0)
	                {

	                }
	                break;
	            case 9:
	                if((counter % 3) == 0)
	                {

	                }
	                break;
	            case 10:
	                if((counter % 2) == 0)
	                {

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
	
}
