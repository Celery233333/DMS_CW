package code.dms_coursework.model.Brick;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

/**
 * QuestionBrick create a kind of Brick according to the specified location, height and width which obtain random effect when breaking it
 * @author Cheng Qin
 */
public class QuestionBrick extends Brick{

    private Rectangle brickFace;
    private Crack crack;
    private Random rnd;
    private int choice;

    /**
     * Create a new instance of QuestionBrick with the specified location, height and width
     * @param point A Point2D object that represents the position of the brick
     * @param height An int that represents the height of the brick
     * @param width An int that represents the width of the brick
     */
    public QuestionBrick(Point2D point, int height, int width){
        super("Question Brick",point,height, width, Color.BLACK,
                new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\question.jpg"),2);
        brickFace = super.brickFace;
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS,brickFace);
        this.rnd = new Random();
        this.choice = rnd.nextInt(10);
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
     * Repair the brick to non-broken status with full strength, and also clear the crack
     */
    public void repair(){
        super.repair();
        crack.reset();
        super.setCrack(crack.draw());
        brickFace = super.brickFace;
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
     * Get the random choice of the brick (method that handle this choice is in Impact class)
     * @see code.dms_coursework.model.Impact
     * @return An int that represents the random choice
     */
    @Override
    public int getChoice() {return choice;}
}
