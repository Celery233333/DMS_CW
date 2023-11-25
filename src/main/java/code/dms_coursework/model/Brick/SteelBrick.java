package code.dms_coursework.model.Brick;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

/**
 * SteelBrick create a kind of Brick according to the specified location, height and width which Probability causes an effective impact
 * @author Cheng Qin-modified
 */
public class SteelBrick extends Brick {

    private final double STEEL_PROBABILITY = 0.4;
    private Random rnd;
    private Rectangle brickFace;

    /**
     * Create a new instance of SteelBrick with the specified location, height and width
     * @param point A Point2D object that represents the position of the brick
     * @param height An int that represents the height of the brick
     * @param width An int that represents the width of the brick
     */
    public SteelBrick(Point2D point, int height, int width){
        super("Steel Brick",point,height, width,Color.BLACK,
                new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\steel.png"),1);
        rnd = new Random();
        brickFace = super.brickFace;
        score = 3;
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
     * Check whether it can create an impact and a crack
     * @param point A Point2D object that represents the position of the crack
     * @param dir An int that represents the direction of the crack
     * @return Return false if the brick is originally broken or not broken from the impact <br>Return true if the brick is broken after the impact
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    /**
     * Generate a random double to check whether it can cause effective impact
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }
}
