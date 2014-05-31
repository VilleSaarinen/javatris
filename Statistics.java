
public class Statistics 
{

	private int level;
	private int points;
	
	public Statistics()
	{
		initStats();
	}
	
	public void initStats()
	{
		level = 1;
		points = 1;
	}
	
	public int getLevel()
	{
		return level;
	}

	public int getPoints()
	{
		return points;
	}
	
	public void levelUp()
	{
		level++;
	}
	
	
	public void dropBrick(int drop)
	{
		//TODO: add points based on the level
		
	}
	
	
	
	
}
