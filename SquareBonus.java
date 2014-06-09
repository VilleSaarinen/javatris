import java.awt.image.BufferedImage;


public class SquareBonus extends Bonus
{

    public SquareBonus(int brickSize, BufferedImage image)
    {
        
        bonusShape = new Brick[8];
        
        bonusShape[0] = new Brick(brickSize, image, 0, 0, 0, 0, 0, 0);
        bonusShape[1] = new Brick(brickSize, image, 1, 0, 1, 0, 1*brickSize, 0);
        bonusShape[2] = new Brick(brickSize, image, 2, 0, 2, 0, 2*brickSize, 0);
        bonusShape[3] = new Brick(brickSize, image, 0, 1, 0, 1, 0, 1*brickSize);
        bonusShape[4] = new Brick(brickSize, image, 2, 1, 2, 1, 2*brickSize, 1*brickSize);
        bonusShape[5] = new Brick(brickSize, image, 0, 2, 0, 2, 0, 2*brickSize);
        bonusShape[6] = new Brick(brickSize, image, 1, 2, 1, 2, 1*brickSize, 2*brickSize);
        bonusShape[7] = new Brick(brickSize, image, 2, 2, 2, 2, 2*brickSize, 2*brickSize);
                
    }
   

}
