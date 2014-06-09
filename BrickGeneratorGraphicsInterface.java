import java.util.concurrent.Semaphore;


public interface BrickGeneratorGraphicsInterface
{
    public int getRowCount();
    public int getColumnCount();
    public GameAreaBrick[][] getGameAreaBricks();
    public GameAreaBrick[] getCurrentBrick();
    public GameAreaBrick[] getNextBrick();
    public Brick[] getBonus();
    public int getMaxDrop();
    public void registerGraphicsObject(GraphicsInterface gi, Semaphore lock);
}
