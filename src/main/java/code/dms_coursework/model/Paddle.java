package code.dms_coursework.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * The Paddle class will create a new paddle in the area of container according to the position of ball, with the specified width and height
 * @author Cheng Qin-modified
 */
public class Paddle {

    private final Image inner = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\paddle.png");

    private double moveAmount = 2.5;
    private Rectangle paddleFace;
    private Point2D ballPoint; // originally the position of the ball, later used as an anchor point
    private Rectangle container;
    private double myMove; // can be negative
    private int min; // left and right border
    private int max;
    private int width;
    private  int height;

    /**
     * Create a new instance of Paddle in the area of container according to the position of ball, with the specified width and height
     * @param ballPoint A point2D object that represents the position of ball
     * @param width An int that represents the width of paddle
     * @param height An int that represents the height of paddle
     * @param container A Rectangle object that represents the area of container
     */
    public Paddle(Point2D ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        this.width = width;
        this.height = height;
        this.container = container;
        myMove = 0;
        paddleFace = makeRectangle(width, height);
        min = (int)container.getX() + (width / 2);
        max = min + (int) container.getWidth() - width;
    }

    /**
     * Create a Rectangle object with specified width and height to represent paddle
     * @param width An int that represents the width of this object
     * @param height An int that represents the height of this object
     * @return A Rectangle object that represents the paddle
     */
    public Rectangle makeRectangle(int width,int height){
        Point2D p = new Point2D((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p.getX(),p.getY(),width,height);
    }

    /**
     * Check if the specified ball impact with this paddle
     * @param b A Ball object used to check impact
     * @return Return true if the ball impact with paddle, and vice versa
     */
    public boolean impact(Ball b){
        // the reason to check first one: the ball is half in the paddle at the beginning
        return paddleFace.contains(b.getPosition()) && paddleFace.contains(b.getDown()) ;
    }

    /**
     * Move the paddle once according to the move amount
     */
    public void move(){
        double x = ballPoint.getX() + myMove;
        if(x < min || x > max)
            return;
        ballPoint = new Point2D(x,ballPoint.getY());
        paddleFace = new Rectangle(ballPoint.getX() - (int) paddleFace.getWidth()/2,ballPoint.getY(),width,height);
    }

    /**
     * Set the move amount to negative for left movement
     */
    public void moveLeft(){
        myMove = -moveAmount;
    }

    /**
     * Set the move amount to negative for right movement
     */
    public void movRight(){
        myMove = moveAmount;
    }

    /**
     * Set the move amount to 0 to stop the paddle
     */
    public void stop() { myMove = 0; }

    /**
     * Get the shape of the paddle
     * @return A Rectangle object that represents the shape of paddle
     */
    public Rectangle getPaddleFace(){
        return paddleFace;
    }

    /**
     * Get the inner image of the paddle
     * @return An Image object that represents the inner image of this object
     */
    public Image getInner() {
        return inner;
    }

    /**
     * Increase the width of paddle by 20 pixels, and change the checking of the border of container
     */
    public void changeLong() {
        width = width+20;
        min = (int)container.getX() + (width / 2);
        max = min + (int) container.getWidth() - width;
    }

    /**
     * Reset the width of paddle to original width - 110 pixels, and change the checking of the border of container
     */
    public void resetWidth() {
        width = 110;
        min = (int)container.getX() + (width / 2);
        max = min + (int) container.getWidth() - width;
    }

    /**
     * Get the width of the paddle
     * @return An int that represents the width of the paddle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Shorten the width of paddle by 20 pixels, and change the checking of the border of container
     */
    public void changeShort() {
        width = width-10;
        min = (int)container.getX() + (width / 2);
        max = min + (int) container.getWidth() - width;
    }

    /**
     * Increase the speed of the paddle by 0.5
     */
    public void speedUp() {
        moveAmount += 0.5;
    }

    /**
     * Reduce the speed of the paddle by 0.5
     */
    public void speddDown() {
        moveAmount -= 0.5;
    }

    /**
     * Reset the speed of the paddle to 2.5
     */
    public void resetSpeed() {
        moveAmount = 2.5;
    }

    /**
     * Get the move amount of the paddle
     * @return A double that represents the move amount
     */
    public double getMoveAmount() {
        return moveAmount;
    }

    /**
     * Moves the paddle to the specified position
     * @param p A Point2D object that represents the ball point, which used to locate the paddle
     */
    public void moveTo(Point2D p){
        ballPoint = p;
        paddleFace = new Rectangle(ballPoint.getX() - (int) paddleFace.getWidth()/2,ballPoint.getY(),width,height);
    }
}