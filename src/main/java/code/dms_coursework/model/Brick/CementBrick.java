package code.dms_coursework.model.Brick;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * CementBrick create a kind of Brick with 2 strength according to the specified location, height and width
 * @author Cheng Qin-modified
 */
public class CementBrick extends Brick {

    private Crack crack;
    private Rectangle brickFace;

    /**
     * Create a new instance of CementBrick with the specified location, height and width
     * @param point A Point2D object that represents the position of the brick
     * @param height An int that represents the height of the brick
     * @param width An int that represents the width of the brick
     */
    public CementBrick(Point2D point, int height, int width){
        super("Cement Brick",point,height, width,Color.BLACK,
                new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\cement.png"),2);
        brickFace = super.brickFace;
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS,brickFace);
        score = 2;
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
     * Check whether it can create an impact and a crack
     * @param point A Point2D object that represents the position of the crack
     * @param dir An int that represents the direction of the crack
     * @return Return false if the brick is originally broken or not broken from the impact <br>Return true if the brick is broken after the impact
     */
    @Override
    public boolean setImpact(Point2D point, int dir) { // dir is direction
        if(super.isBroken()) {
            return false;
        }
        super.impact();

        // check if it can draw crack
        if(!super.isBroken()){
            crack.checkCrack(point,dir);
            super.setCrack(crack.draw());
            return false;
        }
        return true;
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
     * Repair the brick to non-broken status with full strength, and also clear the crack
     */
    public void repair(){
        super.repair();
        crack.reset();
        super.setCrack(crack.draw());
        brickFace = super.brickFace;
    }
}
