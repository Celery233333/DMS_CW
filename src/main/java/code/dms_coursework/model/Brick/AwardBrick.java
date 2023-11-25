package code.dms_coursework.model.Brick;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

/**
 * AwardBrick create a kind of Brick that rewards high score with 1 strength according to the specified location, height and width
 * @author Cheng Qin
 */
public class AwardBrick extends Brick{

    private Rectangle brickFace;
    private Random rnd;

    /**
     * Create a new instance of AwardBrick with the specified location, height and width
     * @param point A Point2D object that represents the position of the brick
     * @param height An int that represents the height of the brick
     * @param width An int that represents the width of the brick
     */
    public AwardBrick(Point2D point, int height, int width){
        super("Award Brick",point,height, width, Color.GOLD,
                new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\award.gif"),1);
        brickFace = super.brickFace;
        rnd = new Random();
        score = rnd.nextInt(10);
    }

    /**
     * Set the shape of the brick
     * @param pos A Point2D object that represents the position of the brick
     * @param height An int that represents the height of the brick
     * @param width An int that represents the width of the brick
     * @return A Rectangle object that represents the shape of the brick
     */
    @Override
    protected Rectangle makeBrickFace(Point2D pos, int height, int width) {
        return new Rectangle(pos.getX(),pos.getY(),width,height);
    }

    /**
     * Get the shape of the brick
     * @return A Rectangle object that represents the shape of the brick
     */
    @Override
    public Rectangle getBrick() {
        return brickFace;
    }
}
