import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphicsEngine extends Canvas 
{
	/**
	 * TODO: find out what the hell is this
	 */
	private static final long serialVersionUID = 1L;  
	
	private Frame frame;
	
	private class CloseWindow extends WindowAdapter
	{		 
		    public void windowClosing(WindowEvent we)
		    {
		    	System.exit(0);
		    }	 
	}
	
	public GraphicsEngine(UserInput ui)
	{
		super();
		
		frame = new Frame();
        frame.setSize(300, 400);
        frame.setVisible(true);

        frame.add(this);
        frame.addWindowListener(new CloseWindow());
        
        //TODO: add other listeners as well
        this.addKeyListener(ui);
		
	}
	


}
