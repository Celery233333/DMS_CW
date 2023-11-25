package code.dms_coursework.model.Brick;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * TNTBrick create a kind of Brick according to the specified location, height and width which destroys the left and right bricks when it breaks (with 0 score reward)
 * @author Cheng Qin
 */
public class TNTBrick extends Brick{

    private Rectangle brickFace;
    private String name;

    /**
     * Create a new instance of TNTBrick with the specified location, height and width
     * @param point A Point2D object that represents the position of the brick
     * @param height An int that represents the height of the brick
     * @param width An int that represents the width of the brick
     */
    public TNTBrick(Point2D point, int height, int width){
        super("TNT Brick",point,height, width, Color.BLACK,
                new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\tnt.png"),1);
        brickFace = super.brickFace;
        name = "TNT Brick";
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
     * get the shape of the brick
     * @return A Rectangle object that represents the shape of the brick
     */
    @Override
    public Rectangle getBrick() {
        return brickFace;
    }

    /**
     * Get the name of the brick used for set explosion in Impact class
     * @return A String that represents the name of the brick
     * @see code.dms_coursework.model.Impact
     */
    @Override
    public String getName() {return name;}
}
