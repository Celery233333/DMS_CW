package code.dms_coursework.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

/**
 * The Ball class will create a new ball with the specified radius and center location measured in pixels
 * @author Cheng Qin-modified
 */
public class Ball {

    private Image inner = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\ball.png");
    private Circle ballFace;
    private Point2D center;
    private int radius;
    private double speedX;
    private double speedY;

    // four points of ball (use to check impact)
    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    /**
     * Create a new instance of Ball with the specified radius and center location
     * @param center A Point2D object that represents the center location of the ball
     * @param radius An int that represents the radius of the ball
     */
    public Ball(Point2D center,int radius){
        this.center = center;
        this.radius = radius;

        // uses these 4 points to check impact
        up = new Point2D(center.getX(),center.getY()-(radius / 2));
        down = new Point2D(center.getX(),center.getY()+(radius / 2));
        left = new Point2D(center.getX()-(radius /2),center.getY());
        right = new Point2D(center.getX()+(radius /2),center.getY());

        // load image and create circle
        ballFace = new Circle(center.getX(),center.getY(),radius);
        speedX = 0;
        speedY = 0;
    }

    /**
     * Get the coordinates of the top vertex of the ball
     * @return A Point2D object that represents the top vertex of this object
     */
    public Point2D getUp() {
        return up;
    }

    /**
     * Get the coordinates of the bottom vertex of the ball
     * @return A Point2D object that represents the bottom vertex of this object
     */
    public Point2D getDown() {
        return down;
    }

    /**
     * Get the coordinates of the left vertex of the ball
     * @return A Point2D object that represents the left vertex of this object
     */
    public Point2D getLeft() {
        return left;
    }

    /**
     * Get the coordinates of the right vertex of the ball
     * @return A Point2D object that represents the right vertex of this object
     */
    public Point2D getRight() {
        return right;
    }

    /**
     * Move the ball once according to the speedX and speedY
     */
    public void move(){
        center = center.add(speedX,speedY);
        ballFace = new Circle(center.getX(),center.getY(),radius);
        setPoints();
    }

    /**
     * Reverse the speed of the ball in x-asis
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * Reverse the speed of the ball in y-asis
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * Get the inner image of the ball
     * @return An Image object that represents the inner image of this object
     */
    public Image getInnerImage(){
        return inner;
    }

    /**
     * Get the center position of the ball
     * @return A Point2D object that represents the center of this object
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * Get the shape of the ball
     * @return A Circle object that represents the shape of the ball
     */
    public Circle getBallFace(){
        return ballFace;
    }

    /**
     * Moves the center point of the ball to the specified position
     * @param p A Point2D object that represents the position that ball will go to
     */
    public void moveTo(Point2D p){
        center = p;
        double r = ballFace.getRadius();
        Circle tmp = new Circle(p.getX(),p.getY(),r);
        ballFace = tmp;
    }

    /**
     * Set the positions of top, bottom, left and right vertex according to position of ball center
     */
    public void setPoints(){
        up = new Point2D(center.getX(),center.getY()-(radius / 2));
        down = new Point2D(center.getX(),center.getY()+(radius / 2));

        left = new Point2D(center.getX()-(radius / 2),center.getY());
        right= new Point2D(center.getX()+(radius / 2),center.getY());
    }

    /**
     * Set the ball's speed along the x-axis
     * @param x A double that represents the speed
     */
    public void setSpeedX(double x){
        speedX = x;
    }

    /**
     * Set the ball's speed along the y-axis
     * @param y A double that represents the speed
     */
    public void setSpeedY(double y) {
        speedY = y;
    }

    /**
     * Get the ball's speed along the x-axis
     * @return A double that represents the speed
     */
    public double getSpeedX(){
        return speedX;
    }

    /**
     * Get the ball's speed along the y-axis
     * @return A double that represents the speed
     */
    public double getSpeedY(){
        return speedY;
    }
}
