package code.dms_coursework.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;

/**
 * The Sprite class will create a new sprite with the specified location, width, height and container
 * @author Cheng Qin
 */
public class Sprite extends Parent {
    private int width, height;
    private ImageView mImageView;
    private Point2D ballPoint;
    private double myMove;
    private double moveAmount = 2.5;
    private int min; // left and right border
    private int max;

    /**
     * Create an instance of Sprite with specified position, width, height and container
     * @param ballPos A Point2D that represents the position
     * @param width An int that represents the width
     * @param height An int that represents the height
     * @param container A Rectangle that represents the container
     */
    public Sprite(Point2D ballPos, int width, int height, Rectangle container) {
        this.ballPoint = ballPos;
        this.width = width;
        this.height = height;
        min = (int)container.getX() + (width / 2);
        max = min + (int) container.getWidth() - width;
        Image actor = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\stop.png");
        mImageView = new ImageView(actor);
    }

    /**
     * Get the image of the sprite
     * @return An ImageView that represents the sprite
     */
    public ImageView getmImageView() {
        return mImageView;
    }

    /**
     * Move the sprite once according to the move amount
     */
    public void move(){
        double x = ballPoint.getX() + myMove;
        if(x < min+20 || x > max-50) {
            return;
        }
        ballPoint = new Point2D(x,ballPoint.getY());
        mImageView.setLayoutX(ballPoint.getX());
        mImageView.setLayoutY(ballPoint.getY());
    }

    /**
     * Set the move amount to negative for left movement, and replace the image
     */
    public void moveLeft(){
        myMove = -moveAmount;
        Image actor = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\left.png");
        mImageView = new ImageView(actor);
        mImageView.setLayoutX(ballPoint.getX());
        mImageView.setLayoutY(ballPoint.getY());
    }

    /**
     * Set the move amount to negative for right movement, and replac the image
     */
    public void movRight(){
        myMove = moveAmount;
        Image actor = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\right.png");
        mImageView = new ImageView(actor);
        mImageView.setLayoutX(ballPoint.getX());
        mImageView.setLayoutY(ballPoint.getY());
    }

    /**
     * Moves the sprite to the specified position
     * @param p A Point2D object that represents the ball point, which used to locate the paddle
     */
    public void moveTo(Point2D p){
        ballPoint = p.add(-17,0);
        Image actor = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\stop.png");
        mImageView = new ImageView(actor);
        mImageView.setLayoutX(ballPoint.getX());
        mImageView.setLayoutY(ballPoint.getY());
    }

    /**
     * Set the move amount to 0 to stop the paddle, and replace the image
     */
    public void stop() {
        myMove = 0;
        Image actor = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\stop.png");
        mImageView = new ImageView(actor);
        mImageView.setLayoutX(ballPoint.getX());
        mImageView.setLayoutY(ballPoint.getY());
    }

    /**
     * Update the speed of the Sprite (use to synchronise the speed with paddle)
     * @param speed An int that represents the speed
     * @see code.dms_coursework.model.Paddle
     * @see code.dms_coursework.model.Brick.QuestionBrick
     */
    public void updateSpeed(double speed) {
        this.moveAmount = speed;
    }
}
