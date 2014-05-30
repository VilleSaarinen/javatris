import java.util.concurrent.atomic.AtomicReferenceArray;


public interface BrickGeneratorGraphicsInterface
{
	public int getRowCount();
	public int getColumnCount();
	public AtomicReferenceArray<AtomicReferenceArray<Brick>>  getGameAreaBricks();
	public void registerGraphicsObject(GraphicsInterface gi);
}
