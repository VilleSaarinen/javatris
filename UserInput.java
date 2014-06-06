
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
    private MenuUserAction menu;
    
    public UserInput(GameEngineUserAction gameEngine)
    {
        this.gameEngine = gameEngine;
        ctrlPressed = false;
    }
    
    
    public void mouseWheelMoved(MouseWheelEvent arg0) 
    {
        // TODO rotate when mouse wheel scrolled

    }

    
    public void mouseDragged(MouseEvent arg0) 
    {
        // TODO no need for implementation

    }


    public void mouseMoved(MouseEvent e) 
    {
        menu.mouseMoved(e.getPoint());

    }


    public void mouseClicked(MouseEvent e) 
    {
        menu.mouseClicked(e.getPoint());

    }


    public void mouseEntered(MouseEvent arg0) 
    {
        // TODO not needed?

    }


    public void mouseExited(MouseEvent arg0) 
    {
        // TODO not needed?

    }

 
    public void mousePressed(MouseEvent arg0) 
    {
        // TODO not needed?

    }


    public void mouseReleased(MouseEvent arg0) 
    {
        // TODO not needed?

    }


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

    
    public void registerMenu(MenuUserAction menu)
    {
        this.menu = menu;
    }
    

    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_CONTROL)
            ctrlPressed = false;
        
    }


    public void keyTyped(KeyEvent e)
    {
        // TODO Auto-generated method stub
        
    }



}
