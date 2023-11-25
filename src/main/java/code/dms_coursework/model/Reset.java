package code.dms_coursework.model;

import code.dms_coursework.model.Brick.Brick;
import java.util.Random;

/**
 * The Reset class create to check whether to pass the current level and provide reset for the game
 * @author Cheng Qin-modified
 */
public class Reset {

    private Impact impact;
    private Random rnd;

    /**
     * Create an instance of Reset with specified object of Impact
     * @param impact An Impact object used to create reset
     */
    public Reset(Impact impact) {
        this.impact = impact;
        this.rnd = new Random();
    }

    /**
     * Go to next level if there is another level
     */
    public void nextLevel(){
        if (hasLevel()) {
            impact.setLevel(impact.getLevel() + 1);
            impact.setBricks(impact.getLevels()[impact.getLevel()]);
            impact.setBrickCount(impact.getBricks().length);
        }
    }

    /**
     * Check whether there is next level
     * @return A boolean that represents whether the game has next level
     */
    public boolean hasLevel(){
        return impact.getLevel()+1 < impact.getLevels().length;
    }

    /**
     * Reset the ball, player and sprite to initial position, and give the ball a random speed
     */
    public void ballReset(){
        // reset the positions of ball and player
        impact.getPlayer().moveTo(impact.getStartPoint());
        impact.getBall().moveTo(impact.getStartPoint());
        impact.getSprite().moveTo(impact.getStartPoint());
        // random to generate the ball speed
        double speedX,speedY;
        do{
            speedX = rnd.nextInt(3) - 1.5;
        }while(speedX == 0 || (speedX < 0.5 && speedX > 0) || ((speedX >-0.5) && speedX<0));
        speedY = -rnd.nextDouble(1.2,1.6);

        impact.getBall().setSpeedX(speedX);
        impact.getBall().setSpeedY(speedY);
        impact.setBallLost(false);
    }

    /**
     * Reset the amount of balls to 3
     */
    public void resetBallCount(){
        impact.setBallCount(3);
    }

    /**
     * Reset the whole game include the amount of balls and bricks and set score and level to 0
     */
    public void wallReset(){
        // reset brick
        for (int i=0;i< impact.getLevels().length;i++)
            for(Brick b : impact.getLevels()[i])
                b.repair();
        impact.setBrickCount(impact.getBricks().length);
        // reset ball
        impact.setBallCount(3);
        // back to level 0
        impact.setLevel(0);
        impact.setBricks(impact.getLevels()[0]);
        // back to score 0
        impact.setScores(0);
    }

    /**
     * Check whether there is no ball in the game
     * @return A boolean that represents whether is no ball in the game
     */
    public boolean ballEnd(){
        return impact.getBallCount() == 0;
    }

    /**
     * Check whether there is no brick in the current level
     * @return A boolean that represents whether is no brick in the current level
     */
    public boolean isDone(){
        return impact.getBrickCount() == 0;
    }
}
