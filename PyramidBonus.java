import java.awt.image.BufferedImage;


public class PyramidBonus extends Bonus
{

    public PyramidBonus(int brickSize, BufferedImage image)
    {
        bonusShape = new Brick[9];
        
        bonusShape[0] = new Brick(brickSize, image, 0, 2, 0, 0, 0, 2*brickSize);
        bonusShape[1] = new Brick(brickSize, image, 1, 1, 1, -1, 1*brickSize, 1*brickSize);
        bonusShape[2] = new Brick(brickSize, image, 1, 2, 1, 0, 1*brickSize, 2*brickSize);
        bonusShape[3] = new Brick(brickSize, image, 1, 3, 1, 1, 1*brickSize, 3*brickSize);
        bonusShape[4] = new Brick(brickSize, image, 2, 0, 2, -2, 2*brickSize, 0);
        bonusShape[5] = new Brick(brickSize, image, 2, 1, 2, -1, 2*brickSize, 1*brickSize);
        bonusShape[6] = new Brick(brickSize, image, 2, 2, 2, 0, 2*brickSize, 2*brickSize);
        bonusShape[7] = new Brick(brickSize, image, 2, 3, 2, 1, 2*brickSize, 3*brickSize);
        bonusShape[8] = new Brick(brickSize, image, 2, 4, 2, 2, 2*brickSize, 4*brickSize);
    }

}
