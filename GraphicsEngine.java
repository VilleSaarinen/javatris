import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class GraphicsEngine extends Canvas 
{
	/**
	 * TODO: find out what the hell is this
	 */
	private static final long serialVersionUID = 1L;  
	
	private Frame frame;
	private int x;
	private int y;
	private GradientPaint bg;
	private int red1, green1, blue1, red2, green2, blue2;
	private boolean r1, r2, g1, g2, b1, b2;
	private Graphics2D g;
	private BufferedImage buffer;
	
	
	private class CloseWindow extends WindowAdapter
	{		 
		    public void windowClosing(WindowEvent we)
		    {
		    	System.exit(0);
		    }	 
	}
	
	public GraphicsEngine(UserInput ui, int x, int y) 
	{
		super();
		this.x = x;
		this.y = y;
		
		frame = new Frame();
        frame.setSize(x, y);
        frame.setVisible(true);
        this.setSize(x, y);
        frame.add(this);
        frame.addWindowListener(new CloseWindow());
        
        //TODO: add other listeners as well
        this.addKeyListener(ui);
        
        //initial values for the gradient background
        red1 = 166;
        green1 = 254;
        blue1 = 200;
        red2 =  254;
        green2 = 254;
        blue2 = 254;
        
        r1 = r2 = g1 = g2 = b1 = b2 = true;
        
        buffer = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D)buffer.getGraphics();
        
        createBackground(); //TODO: test only here
        repaint();
        
		
	}
	
	
	private void createBackground()
	{

		if (red1 >220)
			r1 = false;
		if (red1< 130)
			r1 = true;
		if (red2 >254)
			r2 = false;
		if (red2< 222)
			r2 = true;
		if (green1 >254)
			g1 = false;
		if (green1< 190)
			g1 = true;
		if (green2 >254)
			g2 = false;
		if (green2< 90)
			g2 = true;  
		if (blue1 >210)
			b1 = false;
		if (blue1< 170)
			b1 = true;
		if (blue2 >254)
			b2 = false;
		if (blue2< 120)
			b2 = true;  
  
	    if (r1) red1++;	        
        else red1--;
	    
	    if (r2) red2++;	        
        else red2--;
	    
	    if (g1) green1++;	        
        else green1--;
	    
	    if (g2)green2++;
        else green2--;
	    
	    if (b1)blue1++;	        
        else blue1--;
	    
	    if (b2) blue2++; 
        else blue2--;
                              
	    bg = new GradientPaint(0, this.getHeight(), new Color(red1, green1, blue1), this.getWidth(), 0, new Color(red2, green2, blue2));
		
	}
	
	
	
	
	public void paint(Graphics graphics)
	{	
		g.setPaint(bg);
    	g.fillRect (0, 0,  x, y);
    	graphics.drawImage(buffer, 0,0,this);
	}

}
