import java.util.Vector;


public interface GraphicsInterface
{
    public void animateRowDeletion(Vector<Integer> rowsToDelete);
    public void startMenu(Vector<MenuButton> buttons);
    //TODO: maybe notify all changes?
}
