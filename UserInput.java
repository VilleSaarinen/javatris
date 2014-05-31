
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

	private GameEngineUserAction gameEngine;
	
	public UserInput(GameEngineUserAction gameEngine)
	{
		this.gameEngine = gameEngine;
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
			System.out.println("ctrl");
			break;
		case KeyEvent.VK_RIGHT:
			gameEngine.moveSideways(1);
			break;
		case KeyEvent.VK_LEFT:
			gameEngine.moveSideways(-1);
			break;
		case KeyEvent.VK_UP:
			System.out.println("up");
			break;
		case KeyEvent.VK_DOWN:
			
			break;
		
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}



}
