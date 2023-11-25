package code.dms_coursework.model.Brick;

import code.dms_coursework.model.Ball;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import java.util.Random;

/**
 * The Brick class provides a template for various bricks as an abstract class
 * @author Cheng Qin-modified
 */
abstract public class Brick  {

    public final int DEF_CRACK_DEPTH = 1;
    public final int DEF_STEPS = 35; // the number of steps in a crack

    private final int UP_IMPACT = 100;
    private final int DOWN_IMPACT = 200;
    private final int LEFT_IMPACT = 300;
    private final int RIGHT_IMPACT = 400;

    private static Random rnd;
    private String name;
    Rectangle brickFace;
    private Color border;
    private Image inner;
    private int choice = -1;
    private int fullStrength;
    private int strength; // current strength
    int score;
    private boolean broken;

    /**
     * Get the shape of the crack
     * @return A Polyline object that represents the shape of the crack
     */
    public Polyline getCrack() {
        return crack;
    }

    /**
     * Set the shape of the crack
     * @param crack A Polyline object that represents the shape of the crack
     */
    public void setCrack(Polyline crack) {
        this.crack = crack;
    }

    /**
     * Get the name of this brick
     * @return A String that represents the name of this brick
     */
    public String getName() { return name; }

    private Polyline crack;

    /**
     * Create an instance of Brick with name, inner image, border color and strength, according to the position, height and width
     * @param name A String that represents the name of the brick
     * @param pos A Point2D object that represents the position of the brick
     * @param height An int that represents the height of the brick
     * @param width An int that represents the width of the brick
     * @param border A Color object that represents the border color of the brick
     * @param inner An Image object that represents the inner image of the brick
     * @param strength An int that represents the strength of the brick
     */
    public Brick(String name, Point2D pos, int height, int width, Color border, Image inner, int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,height, width);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;
    }

    /**
     * Set the shape of the brick
     * @param pos A Point2D object that represents the position of the brick
     * @param height An int that represents the height of the brick
     * @param width An int that represents the width of the brick
     * @return A Rectangle object that represents the shape of the brick
     */
    protected abstract Rectangle makeBrickFace(Point2D pos,int height, int width);

    /**
     * Check whether the brick is broken after impact
     * @param point A Point2D object that represents the position of the crack
     * @param dir An int that represents the direction of the crack
     * @return Return false if the brick is original broke or not broken from the impact <br>Return true if the brick is broken after the impact
     */
    public  boolean setImpact(Point2D point, int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * Get the shape of the brick
     * @return A Rectangle object that represents the shape of the brick
     */
    public abstract Rectangle getBrick();

    /**
     * Get the border color of the brick
     * @return A Color object that represents the border color of the brick
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * Get the inner image of the 
     * @return An Image object that represents the inner image of the brick
     */
    public Image getInnerImage(){
        return inner;
    }

    /**
     * Get the score of the brick
     * @return An int that represents the score of the brick
     */
    public int getScore() { return score; }

    /**
     * Get the choice after breaking the brick
     * @return An int that represents the choice
     */
    public int getChoice() { return choice; }

    /**
     * Check the direction that specified ball impact with the brick
     * @param b A Ball object that impact with the brick
     * @return An int that represents the impact direction
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * Check if the brick is broken
     * @return Return true if the brick is broken, and vice versa
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * Repair the brick to non-broken status with full strength
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * Set impact the strength-- and check the broken status
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    /**
     * Set impact continually until the brick is broken
     */
    public void destroy() {
        while (!broken) {
            this.impact();
        }
    }
}