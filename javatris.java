

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

		ui = new UserInput();
		graphicsEngine = new GraphicsEngine(ui, 1000, 1000);
		graphicsEngine.run();
		
		gameEngine = new GameEngine();
		gameEngine.run();
	
	}

}
