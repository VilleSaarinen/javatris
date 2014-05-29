
public class GameEngine implements Runnable 
{
	
	private final int width = 1000;
	private final int height = 1000;
	private final int rows = 15;
	private final int columns = 30;
	private int updateTick;
	private long currentTime;
	private BrickGenerator brickHandler;
	private UserInput ui;
	private GraphicsEngine graphicsEngine;


	
	
	public GameEngine()
	{

		ui = new UserInput();
		
		graphicsEngine = new GraphicsEngine(ui, width, height);
				
	}
	
	public void startScreen()
	{
		
		
	}
	
	public void menuScreen()
	{
		
	}
	
	
	public void startNewGame()
	{
				
		updateTick = 1000; //TODO: not yet correct
		currentTime = System.currentTimeMillis();
		brickHandler = new BrickGenerator(width, height, 15, 30);
		graphicsEngine.run();
	}
	
	@Override
	public void run()
	{

		System.out.println("start");

	}

}
