package code.dms_coursework.model;

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

class ResetTest {
    Reset reset;
    Impact impact;
    Point2D ballPos = new Point2D(10,10);

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
        Ball ball = new Ball(ballPos,5);
        Rectangle rectangle = new Rectangle(0,0,100,100);
        Paddle paddle = new Paddle(ballPos,10,1,rectangle);
        Wall wall = new Wall(rectangle,2,1,1);
        Sprite sprite = new Sprite(ballPos,10,1,rectangle);
        impact = new Impact(ball,rectangle,paddle,wall,sprite,ballPos,0.2);
        reset = new Reset(impact);
    }

    @Test
    void nextLevel() {
        assertEquals(impact.getLevel()+1,0);
        reset.nextLevel();
        assertEquals(impact.getLevel()+1,1);
    }

    @Test
    void hasLevel() {
        assertTrue(reset.hasLevel());
        for (int i=0;i<6;i++) {
            reset.nextLevel();
        }
        assertFalse(reset.hasLevel());
    }

    @Test
    void ballReset() {
        impact.getBall().moveTo(new Point2D(99,99));
        reset.ballReset();
        assertEquals(impact.getBall().getPosition(),ballPos);
    }

    @Test
    void resetBallCount() {
        impact.setBallCount(1);
        reset.resetBallCount();
        assertEquals(impact.getBallCount(),3);
    }

    @Test
    void wallReset() {
        impact.setBricks(impact.getLevels()[0]);
        impact.impactWall();
        reset.wallReset();
        assertEquals(impact.getBricks()[0].getName(),"Clay Brick");
        assertEquals(impact.getBricks()[1].getName(),"Clay Brick");
    }

    @Test
    void ballEnd() {
        assertFalse(reset.ballEnd());
        impact.setBallCount(0);
        assertTrue(reset.ballEnd());
    }

    @Test
    void isDone() {
        impact.setBricks(impact.getLevels()[0]);
        assertTrue(reset.isDone());
    }
}