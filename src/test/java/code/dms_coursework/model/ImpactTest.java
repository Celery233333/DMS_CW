package code.dms_coursework.model;

import code.dms_coursework.model.Brick.Brick;
import code.dms_coursework.model.Brick.CementBrick;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ImpactTest {
    Impact impact;
    Point2D ballPos = new Point2D(10,10);
    Ball ball = new Ball(ballPos,5);
    Rectangle rectangle = new Rectangle(0,0,100,100);
    Paddle paddle = new Paddle(ballPos,10,1,rectangle);
    Wall wall = new Wall(rectangle,2,1,1);
    Sprite sprite = new Sprite(ballPos,10,1,rectangle);

    @BeforeAll
    public static void setUpJavaFXRuntime() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(() -> {
            latch.countDown();
        });
        latch.await(5, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void tearDownJavaFXRuntime() throws InterruptedException {
        Platform.exit();
    }

    @BeforeEach
    void reset() {
        impact = new Impact(ball,rectangle,paddle,wall,sprite,ballPos,0.2);
    }

    @Test
    void move() {
        assertEquals(impact.getBall().getPosition(),ballPos);
        impact.move();
        assertEquals(impact.getPlayer().getPaddleFace().getX(),5);
        assertEquals(impact.getPlayer().getPaddleFace().getY(),10);
    }

    @Test
    void getPlayer() {
        assertEquals(impact.getPlayer(),paddle);
    }

    @Test
    void getSprite() {
        assertEquals(impact.getSprite(),sprite);
    }

    @Test
    void getBall() {
        assertEquals(impact.getBall(),ball);
    }

    @Test
    void getBricks() {
        assertEquals(impact.getBricks(),null);
    }

    @Test
    void getLevel() {
        assertEquals(impact.getLevel()+1,0);
    }

    @Test
    void getBallCount() {
        assertEquals(impact.getBallCount(),3);
    }

    @Test
    void getScores() {
        assertEquals(impact.getScores(),0);
    }

    @Test
    void setScores() {
        impact.setScores(99);
        assertEquals(impact.getScores(),99);
    }

    @Test
    void isBallLost() {
        assertFalse(impact.isBallLost());
    }

    @Test
    void setBricks() {
        Brick[] test = new Brick[2];
        test[0] = new CementBrick(new Point2D(0,0),1,1);
        test[1] = new CementBrick(new Point2D(1,0),1,1);
        impact.setBricks(test);
        assertEquals(impact.getBricks(),test);
    }

    @Test
    void setLevel() {
        impact.setLevel(1);
        assertEquals(impact.getLevel(),1);
    }

    @Test
    void getBrickCount() {
        assertEquals(impact.getBrickCount(),0);
    }

    @Test
    void setBrickCount() {
        impact.setBrickCount(2);
        assertEquals(impact.getBrickCount(),2);
    }

    @Test
    void getLevels() {
        assertEquals(wall.getLevels(),impact.getLevels());
    }

    @Test
    void getStartPoint() {
        assertEquals(ballPos,impact.getStartPoint());
    }

    @Test
    void getReset() {
        assertTrue(impact.getReset().hasLevel());
    }

    @Test
    void setBallCount() {
        impact.setBallCount(66);
        assertEquals(impact.getBallCount(),66);
    }

    @Test
    void setBallLost() {
        impact.setBallLost(true);
        assertTrue(impact.isBallLost());
    }

    @Test
    void findImpacts() {
        Brick[] test = new Brick[2];
        test[0] = new CementBrick(new Point2D(0,0),1,1);
        test[1] = new CementBrick(new Point2D(1,0),1,1);
        impact.setBricks(test);
        impact.findImpacts();
        assertEquals(impact.getBrickCount(),0);
    }

    @Test
    void impactWall() {
        Brick[] test = new Brick[2];
        test[0] = new CementBrick(new Point2D(0,0),1,1);
        test[1] = new CementBrick(new Point2D(1,0),1,1);
        impact.setBricks(test);
        impact.impactWall();
        assertEquals(impact.getBrickCount(),0);
    }
}