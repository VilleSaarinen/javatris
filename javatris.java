

public class javatris 
{
	

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args)
	{
		System.out.println("jee");
		GameEngine gameEngine;
		UserInput ui;
		GraphicsEngine graphicsEngine;
		
		
		gameEngine = new GameEngine();
		ui = new UserInput();
		graphicsEngine = new GraphicsEngine(ui);
		
		gameEngine.run();
	
	}

}
