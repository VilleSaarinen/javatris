import java.awt.image.BufferedImage;
import java.util.Random;

public class DiagonalBonus extends Bonus
{

    public DiagonalBonus(int brickSize, BufferedImage image)
    {
        Random rand = new Random();
        
        bonusShape = new Brick[5];
        
        switch(Math.abs(rand.nextInt())%2)
        {
            case 0:
                bonusShape[0] = new Brick(brickSize, image, 0, 0, 0, 0, 0, 0);
                bonusShape[1] = new Brick(brickSize, image, 1, 1, 1, 1, 1*brickSize, 1*brickSize);
                bonusShape[2] = new Brick(brickSize, image, 2, 2, 2, 2, 2*brickSize, 2*brickSize);
                bonusShape[3] = new Brick(brickSize, image, 3, 3, 3, 3, 3*brickSize, 3*brickSize);
                bonusShape[4] = new Brick(brickSize, image, 4, 4, 4, 4, 4*brickSize, 4*brickSize);
                break;
            case 1:     
                bonusShape[0] = new Brick(brickSize, image, 0, 4, 0, 0, 0, 4*brickSize);
                bonusShape[1] = new Brick(brickSize, image, 1, 3, 1, -1, 1*brickSize, 3*brickSize);
                bonusShape[2] = new Brick(brickSize, image, 2, 2, 2, -2, 2*brickSize, 2*brickSize);
                bonusShape[3] = new Brick(brickSize, image, 3, 1, 3, -3, 3*brickSize, 1*brickSize);
                bonusShape[4] = new Brick(brickSize, image, 4, 0, 4, -4, 4*brickSize, 0);
                break;
        }
        


    }

}
