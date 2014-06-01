import java.util.concurrent.Semaphore;


public interface BrickGeneratorGraphicsInterface
{
    public int getRowCount();
    public int getColumnCount();
    public Brick[][] getGameAreaBricks();
    public Brick[] getCurrentBrick();
    public Brick[] getNextBrick();
    public void registerGraphicsObject(GraphicsInterface gi, Semaphore lock);
}
