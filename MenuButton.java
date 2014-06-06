import java.awt.image.BufferedImage;


public class MenuButton
{

    //TODO: these in separate config file?
    public static final int buttonWidth = 710;
    public static final int buttonHeight = 105;
    
    private int x;
    private int y;
    private boolean hasFocus;
    private BufferedImage buttonImage;
    private BufferedImage buttonImageFocused;
    private boolean statusChanged;
    private boolean previouslyFocused;
    private GameEngine.ACTION action;
    
    public MenuButton(int index, ImageHandler images, int windowWidth, int windowHeight)
    {

        int yGap;
        
        switch(index)
        {
            case 0:
                this.action = GameEngine.ACTION.NEW_GAME;
                break;
            case 1:
                this.action = GameEngine.ACTION.OPTIONS;
                break;
            case 2:
                this.action = GameEngine.ACTION.MANUAL;
                break;
            case 3:
                this.action = GameEngine.ACTION.HIGH_SCORE;
                break;
            case 4:
                this.action = GameEngine.ACTION.QUIT;
                break;
        }
        
        yGap = windowHeight - ImageHandler.menuItemsCount*buttonHeight;
        yGap /= ImageHandler.menuItemsCount+2;
        
        x = (windowWidth-buttonWidth)/2;
        y = yGap*(index+1) + index*buttonHeight;

        buttonImage = images.getMenuButton(index);
        buttonImageFocused = images.getFocusedMenuButton(index);
        hasFocus = false;
        statusChanged = true;
        previouslyFocused = true;
    }
    
    
    public BufferedImage getCurrentImage()
    {
        statusChanged = false;
        
        if(hasFocus)
            return buttonImageFocused;
        else
            return buttonImage;
    }
    
    
    public void mouseMoved(int x, int y)
    {
        if(y >= this.y && y <= this.y + buttonHeight && x >= this.x && x <= this.x + buttonWidth)
        {
            if(!hasFocus)
            {
                statusChanged = true;
                hasFocus = true;
            }
        }
        else
        {
            if(hasFocus)
            {
                hasFocus = false;
                statusChanged = true;
            }
        }
        
    }
    
    public GameEngine.ACTION mouseClicked()
    {
        if(hasFocus)
            return action;
        else
            return GameEngine.ACTION.NONE;
    }
    
    
    public int getX()
    {
        return x;
    }
    
    
    public int getY()
    {
        return y;
    }
    
    
    public int getWidth()
    {
        return buttonWidth;
    }
    
    
    public int getHeight()
    {
        return buttonHeight;
    }
    
    
    public boolean statusChanged()
    {
        return statusChanged;
    }
    
}
