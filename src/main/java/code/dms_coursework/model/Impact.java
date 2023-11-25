package code.dms_coursework.model;

import code.dms_coursework.model.Brick.Brick;
import javafx.geometry.Point2D;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import java.io.File;
import java.util.Random;

/**
 * The Impact class will handle all the impact effects from ball, paddle, border, bricks
 * @author Cheng Qin-modified
 */
public class Impact {

    private Random rnd;
    private Point2D startPoint;
    private Brick[] bricks; // store all bricks from current map
    private Ball ball;
    private Rectangle area;
    private Paddle player;
    private Brick[][] levels; // store map
    private Sprite sprite;

    private int brickCount;
    private Reset reset;
    private int ballCount;
    private int scores;
    private boolean ballLost; // lose game or not
    private int level;
    private double volumePercent;

    /**
     * Create an instance of Impact with specified elements from current game
     * @param ball A Ball object that represents the ball of the game
     * @param area A Rectangle object that represents the area of the game
     * @param player A Paddle object that represents the player of the game
     * @param maps A Wall object that represents all the levels of the game
     * @param ballPos A Point2D object that represents the position of the ball
     * @param sprite A Sprite object that represents the sprite of the game
     * @param volumePercent A double that represents the volume of the sound of the game
     */
    public Impact(Ball ball, Rectangle area, Paddle player, Wall maps, Sprite sprite, Point2D ballPos, double volumePercent) {
        // initialize the components
        this.ball = ball;
        this.levels = maps.getLevels();
        this.startPoint = new Point2D(ballPos.getX(),ballPos.getY());
        this.area = area;
        this.player = player;
        this.sprite = sprite;
        this.volumePercent = volumePercent;
        ballCount = 3;

        // random generate the ball speed
        rnd = new Random();
        double speedX,speedY;
        do{
            speedX = rnd.nextInt(3) - 1.5;
        }while(speedX == 0 || (speedX < 0.5 && speedX > 0) || ((speedX >-0.5) && speedX<0));
        speedY = -rnd.nextDouble(1.2,1.6);
        ball.setSpeedX(speedX);
        ball.setSpeedY(speedY);

        ballLost = false;
        level = -1;

        Reset reset = new Reset(this);
        this.reset = reset;
    }

    /**
     * Move the player, ball and sprite (see the move method in their classes)
     * @see code.dms_coursework.model.Paddle
     * @see code.dms_coursework.model.Ball
     * @see code.dms_coursework.model.Sprite
     */
    public void move(){
        player.move();
        ball.move();
        sprite.move();
    }

    /**
     * Get the player of the game
     * @return A Paddle object that represents the player
     */
    public Paddle getPlayer() { return player; }

    /**
     * Get the sprite of the game
     * @return A Sprite object that represents the sprite
     */
    public Sprite getSprite() { return sprite; }

    /**
     * Get the ball of the game
     * @return A Ball object that represents the player
     */
    public Ball getBall() { return ball;}

    /**
     * Get the current level of the game
     * @return A Brick[] object that represents the current level
     */
    public Brick[] getBricks() { return bricks; }

    /**
     * Get the number of current level of the game
     * @return An int that represents the number of current level
     */
    public int getLevel() { return level; }

    /**
     * Get the amount of balls of the game
     * @return An int that represents the amount of the ball
     */
    public int getBallCount(){ return ballCount; }

    /**
     * Get the scores of the game
     * @return An int that represents the scores
     */
    public int getScores() { return scores; }

    /**
     * Set the scores of the game
     * @param scores An int that represents the scores
     */
    public void setScores(int scores) { this.scores = scores; }

    /**
     * Check whether the ball lost
     * @return A boolean that represents the ball lost
     */
    public boolean isBallLost(){ return ballLost; }

    /**
     * Set the current level of the game
     * @param bricks A Brick[] object that represents the curren level
     */
    public void setBricks(Brick[] bricks) { this.bricks = bricks; }

    /**
     * Set the number of the current level
     * @param level An int that represents the number of current level
     */
    public void setLevel(int level) { this.level = level; }

    /**
     * Get the amount of bricks of the current level
     * @return An int that represents the amount of bricks
     */
    public int getBrickCount() { return brickCount; }

    /**
     * Set the amount of bricks of the current level
     * @param brickCount An int that represents the amount of bricks
     */
    public void setBrickCount(int brickCount) { this.brickCount = brickCount; }

    /**
     * Get all the levels of the game
     * @return A Brick[][] object that represents all the levels of the game
     */
    public Brick[][] getLevels() { return levels; }

    /**
     * Get the start point of the game
     * @return A Point2D object that represents the start point of the game
     */
    public Point2D getStartPoint() { return startPoint; }

    /**
     * Get Reset object of the game
     * @return A Reset object that controls the pass and reset the level
     * @see code.dms_coursework.model.Reset
     */
    public Reset getReset() { return reset; }

    /**
     * Set the amount of the game to specified number
     * @param ballCount An int that represents the amount of balls
     */
    public void setBallCount(int ballCount) { this.ballCount = ballCount; }

    /**
     * Set if the ball lost or not
     * @param ballLost A boolean that represents the ball lost
     */
    public void setBallLost(boolean ballLost) { this.ballLost = ballLost; }

    /**
     * Find impacts from wall, left/right/top/bottom border and paddle with the ball
     */
    public void findImpacts(){
        if(impactWall()){
            // for efficiency reverse is done into method impactWall because for every brick program checks for horizontal and vertical impacts
            impactSound();
            brickCount--;
        }
        else if (ball.getPosition().getX() < area.getX()) {
            impactBorderSound();
            ball.reverseX();
        }
        else if (ball.getPosition().getX() > area.getX() + area.getWidth()) {
            impactBorderSound();
            ball.reverseX();
        }
        else if(ball.getUp().getY() < area.getY()){ // check the impact from up
            impactBorderSound();
            ball.reverseY();
        }
        else if(player.impact(ball)){ // check the impact from paddle
            impactBorderSound();
            ball.reverseY();
        }
        else if(ball.getDown().getY() > area.getY() + area.getHeight()){ // check the impact from down
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * Handle the impact with ball and brick from four different directions when the impact happens
     * @return A boolean represents whether the ball impact with brick
     */
    public boolean impactWall(){
        for(int b=0; b<bricks.length;b++){
            boolean isImpact;
            switch(bricks[b].findImpact(ball)) {
                //Vertical Impact
                case 100:
                    impactBorderSound();
                    ball.reverseY();
                    isImpact = bricks[b].setImpact(ball.getDown(), 30);
                    if(isImpact) {
                        scores += bricks[b].getScore();
                        randomEffect(bricks[b].getChoice());
                        boom(bricks,b);
                    }
                    return isImpact;
                case 200:
                    impactBorderSound();
                    ball.reverseY();
                    isImpact = bricks[b].setImpact(ball.getUp(),40);
                    if(isImpact) {
                        scores += bricks[b].getScore();
                        randomEffect(bricks[b].getChoice());
                        boom(bricks,b);
                    }
                    return isImpact;
                //Horizontal Impact
                case 300:
                    impactBorderSound();
                    ball.reverseX();
                    isImpact = bricks[b].setImpact(ball.getRight(),20);
                    if(isImpact) {
                        scores += bricks[b].getScore();
                        randomEffect(bricks[b].getChoice());
                        boom(bricks,b);
                    }
                    return isImpact;
                case 400:
                    impactBorderSound();
                    ball.reverseX();
                    isImpact = bricks[b].setImpact(ball.getLeft(),10);
                    if(isImpact) {
                        scores += bricks[b].getScore();
                        randomEffect(bricks[b].getChoice());
                        boom(bricks,b);
                    }
                    return isImpact;
            }
        }
        return false;
    }

    /**
     * Load and play the sound that represents the ball impact with border
     */
    private void impactBorderSound() {
        // load the ball start sound
        File music = new File("src\\main\\resources\\code\\dms_coursework\\sound\\border.wav");
        MediaPlayer impactMusic = new MediaPlayer(new Media(music.toURI().toString()));
        impactMusic.setVolume(volumePercent);
        impactMusic.play();
    }

    /**
     * Load and play the sound that represents the ball impact with brick
     */
    private void impactSound() {
        // load the ball start sound
        File music = new File("src\\main\\resources\\code\\dms_coursework\\sound\\impact.wav");
        MediaPlayer impactMusic = new MediaPlayer(new Media(music.toURI().toString()));
        impactMusic.setVolume(volumePercent);
        impactMusic.play();
    }

    /**
     * Create an effect for the break of QuestionBrick with random choice value
     * @param choice An int that represents the random choice
     * @see code.dms_coursework.model.Brick.QuestionBrick
     */
    private void randomEffect(int choice) {
        switch (choice) {
            case 0,1:
                player.changeLong();
                return;
            case 2,3:
                if (player.getWidth() >= 90) {
                    player.changeShort();}
                return;
            case 4,5:
                ballCount++;
                return;
            case 6:
                if (ballCount != 1) {
                    ballCount--;}
                return;
            case 7,8:
                player.speedUp();
                return;
            case 9:
                if (player.getMoveAmount() >= 1) {
                    player.speddDown();}
                return;
            default:
                return;
        }
    }

    /**
     * Destroy the adjacent bricks when TNTBrick is breaking
     * @param bricks A Brick[] object that represents the current level
     * @param b An int that represents the position in current level of the TNT brick
     * @see code.dms_coursework.model.Brick.TNTBrick
     */
    private void boom(Brick[]bricks, int b) {
        if (bricks[b].getName() == "TNT Brick") {
            if (!bricks[b-1].isBroken()) {
                bricks[b-1].destroy();
                brickCount--;
            }
            if (!bricks[b+1].isBroken()) {
                bricks[b+1].destroy();
                brickCount--;
            }
        }
    }
}