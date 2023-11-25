package code.dms_coursework.model.Brick;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * ClayBrick create the basic brick with 1 strength according to the specified location, height and width
 * @author Cheng Qin-modified
 */
public class ClayBrick extends Brick {

    /**
     * Create a new instance of ClayBrick with the specified location, height and width
     * @param point A Point2D object that represents the position of the brick
     * @param height An int that represents the height of the brick
     * @param width An int that represents the width of the brick
     */
    public ClayBrick(Point2D point, int height, int width){
        super("Clay Brick",point,height, width,Color.BLACK,
                new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\clay.png"),1);
        score = 1;
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
        return new Rectangle(pos.getX(),pos.getY(),width, height);
    }

    /**
     * Get the shape of the brick
     * @return A Rectangle object that represents the shape of the brick
     */
    @Override
    public Rectangle getBrick() {
        return super.brickFace;
    }
}