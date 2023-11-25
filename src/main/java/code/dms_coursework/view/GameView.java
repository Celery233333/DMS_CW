package code.dms_coursework.view;

import code.dms_coursework.model.*;
import code.dms_coursework.model.Brick.Brick;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Create a Pane object to show all the elements of the game
 * @author Cheng Qin-modified
 */
public class GameView extends Pane{

    private Point2D ballPos;
    private Ball ball;
    private Paddle player;
    private Wall wall;
    private Impact impact;
    private String message;
    private Sprite sprite;

    /**
     * Create an instance of GameView, initialize Ball, Wall, Player, Sprite and Impact object of the game
     * @param volumePercent A double that represents the volume of the sound
     */
    public GameView(double volumePercent) {

        message = "Press SPACE to start";
        ballPos = new Point2D(300,406);
        Rectangle rectangle = new Rectangle(0, 0, 600, 450);
        player = new Paddle(ballPos,110,10, rectangle);
        wall = new Wall(rectangle, 32, 4, 6 / 2);
        ball = new Ball(ballPos,5);
        // because the width is 34, need to -17 to be the center
        sprite = new Sprite(ballPos.add(-17,0),34,40,rectangle);
        impact = new Impact(ball, rectangle, player, wall, sprite, ballPos, volumePercent);

        //initialize the first level
        impact.getReset().nextLevel();
        paint();
    }

    /**
     * Get the Impact object of the game
     * @return An Impact object used to control the impact of the game
     */
    public Impact getImpact() {
        return impact;
    }

    /**
     * Set the message on the gameView
     * @param message A String tha represents the current message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the sprite of the game
     * @return A Sprite object that represents the sprite of the game
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Draw the all the elements on the gameView
     */
    public void paint() {
        this.setPrefSize(600,450);

        Text text = new Text(message);
        text.setStroke(Color.rgb(255,255,255));
        text.setLayoutY(490);
        text.setFont(new Font(36));
        this.getChildren().add(text);

        drawBall(ball, this);

        for (Brick b : impact.getBricks())
            if (!b.isBroken())
                drawBrick(b, this);

        drawPlayer(player, this);
        this.getChildren().add(sprite.getmImageView());
    }

    /**
     * Draw bricks on the gameView
     * @param brick A Brick object that represents the brick that need to be drawn
     * @param g2d A Pane object that represents the gameView
     */
    public void drawBrick(Brick brick, Pane g2d) {
        // draw brick border
        brick.getBrick().setStroke(brick.getBorderColor());
        // draw inner brick
        brick.getBrick().setFill(new ImagePattern(brick.getInnerImage()));
        g2d.getChildren().add(brick.getBrick());

        // check whether draw the crack
        if (brick.getCrack() != null) {
            g2d.getChildren().add(brick.getCrack());
        }
    }

    /**
     * Draw ball on the gameView
     * @param ball A Ball object that represents the ball that need to be drawn
     * @param g2d A Pane object that represents the gameView
     */
    public void drawBall(Ball ball, Pane g2d) {
        // draw inner ball
        ball.getBallFace().setFill(new ImagePattern(ball.getInnerImage()));
        g2d.getChildren().add(ball.getBallFace());
    }

    /**
     * Draw player on the gameView
     * @param p A Paddle object that represents the player that need to be drawn
     * @param g2d A Pane object that represents the gameView
     */
    public void drawPlayer(Paddle p, Pane g2d) {
        // draw inner paddle
        p.getPaddleFace().setFill(new ImagePattern(p.getInner()));
        g2d.getChildren().add(p.getPaddleFace());
    }
}
