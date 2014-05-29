
public class GameEngine implements Runnable 
{

	private GraphicsEngine ge;
	
	public GameEngine(GraphicsEngine ge)
	{
		this.ge = ge;
	}
	
	
	@Override
	public void run()
	{

		System.out.println("start");

	}

}
