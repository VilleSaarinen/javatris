import java.awt.Point;
import java.awt.image.BufferedImage;


/*
 * Singleton class for menu.
 */

public class Menu implements MenuUserAction
{
    
    private static boolean menuCreated = false;
    private static Menu menu;
    
    private int windowWidth;
    private int windowHeight;
    private BufferedImage menuBackground;
    
    private MenuButton[] buttons;

    public static Menu createMenu(ImageHandler images, int windowWidth, int windowHeight)
    {
        if(!menuCreated)
        {
            menu = new Menu(images, windowWidth, windowHeight);
            menuCreated = true;
        }

        return menu;
    }
    
    private Menu(ImageHandler images, int windowWidth, int windowHeight)
    {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        this.menuBackground = images.getMenuBackground();
        
        buttons = new MenuButton[ImageHandler.menuItemsCount];  //TODO: menuitemscount should be in separate file
        
        for(int i = 0; i < ImageHandler.menuItemsCount; i++)
        {
            buttons[i] = new MenuButton(i, images, windowWidth, windowHeight);
        }
        
    }
    
    
    public MenuButton[] getButtons()
    {
        return buttons;
    }
    
    
    public BufferedImage getMenuBackground()
    {
        return menuBackground;
    }


    public void mouseMoved(Point point)
    {
        for(int i = 0; i < buttons.length; i++)
        {
            buttons[i].mouseMoved(point.x, point.y);
        }
        
    }


    public void mouseClicked(Point point)
    {

    }
    
    
}
