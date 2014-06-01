
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyListener;


public class UserInput implements MouseListener, KeyListener,
        MouseMotionListener, MouseWheelListener 
{

    private boolean ctrlPressed;
    
    private GameEngineUserAction gameEngine;
    
    public UserInput(GameEngineUserAction gameEngine)
    {
        this.gameEngine = gameEngine;
        ctrlPressed = false;
    }
    
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent arg0) 
    {
        // TODO rotate when mouse wheel scrolled

    }

    @Override
    public void mouseDragged(MouseEvent arg0) 
    {
        // TODO no need for implementation

    }

    @Override
    public void mouseMoved(MouseEvent arg0) 
    {
        // TODO maybe move the brick?

    }

    @Override
    public void mouseClicked(MouseEvent arg0) 
    {
        // TODO click objects

    }

    @Override
    public void mouseEntered(MouseEvent arg0) 
    {
        // TODO not needed?

    }

    @Override
    public void mouseExited(MouseEvent arg0) 
    {
        // TODO not needed?

    }

    @Override
    public void mousePressed(MouseEvent arg0) 
    {
        // TODO not needed?

    }

    @Override
    public void mouseReleased(MouseEvent arg0) 
    {
        // TODO not needed?

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        
        switch(e.getKeyCode())
        {
        case KeyEvent.VK_CONTROL:
            ctrlPressed = true;
            break;
        case KeyEvent.VK_RIGHT:
            if(ctrlPressed)
                gameEngine.moveSideways(4);
            else
                gameEngine.moveSideways(1);
            break;
        case KeyEvent.VK_LEFT:
            if(ctrlPressed)
                gameEngine.moveSideways(-4);
            else
                gameEngine.moveSideways(-1);
            break;
        case KeyEvent.VK_UP:
            gameEngine.rotate(true);
            break;
        case KeyEvent.VK_DOWN:
            if(ctrlPressed)
                gameEngine.dropCurrent(50);
            else
                gameEngine.dropCurrent(1);
            break;        
        }
        
        
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_CONTROL)
            ctrlPressed = false;
        
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        // TODO Auto-generated method stub
        
    }



}
