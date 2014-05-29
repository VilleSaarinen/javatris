
public class GameEngine implements Runnable 
{

	private int updateTick;
	private long currentTime;
	
	public GameEngine()
	{
		
		updateTick = 1000; //TODO: not yet correct
		currentTime = System.currentTimeMillis();
		
		
	}
	
	
	@Override
	public void run()
	{

		System.out.println("start");

	}

}
