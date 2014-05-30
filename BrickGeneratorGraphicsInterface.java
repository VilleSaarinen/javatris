
public interface BrickGeneratorGraphicsInterface
{
	public int getRowCount();
	public int getColumnCount();
	public Brick[][] getGameAreaBricks();
	public void registerGraphicsObject(GraphicsInterface gi);
}
