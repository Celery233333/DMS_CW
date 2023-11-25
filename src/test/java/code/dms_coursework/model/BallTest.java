package code.dms_coursework.model;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {
    Ball ball;

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
        Point2D p = new Point2D(10,10);
        ball = new Ball(p,10);
    }

    @Test
    void getUp() {
        assertEquals(ball.getUp(), new Point2D(10,5));
    }

    @Test
    void getDown() {
        assertEquals(ball.getDown(), new Point2D(10,15));
    }

    @Test
    void getLeft() {
        assertEquals(ball.getLeft(), new Point2D(5,10));
    }

    @Test
    void getRight() {
        assertEquals(ball.getRight(), new Point2D(15,10));
    }

    @Test
    void move() {
        ball.setSpeedX(1);
        ball.setSpeedY(-1);
        ball.move();
        assertEquals(ball.getPosition(),new Point2D(11,9));
    }

    @Test
    void reverseX() {
        double original_speed = ball.getSpeedX();
        ball.reverseX();
        assertEquals(original_speed*-1,ball.getSpeedX());
    }

    @Test
    void reverseY() {
        double original_speed = ball.getSpeedY();
        ball.reverseY();
        assertEquals(original_speed*-1,ball.getSpeedY());
    }

    @Test
    void getInnerImage() {
        assertEquals(ball.getInnerImage().getUrl(),"file:src/main/resources/code/dms_coursework/image/ball.png");
    }

    @Test
    void getPosition() {
        assertEquals(ball.getPosition(),new Point2D(10,10));
    }

    @Test
    void getBallFace() {
        String circle = new Circle(10,10,10).toString();
        assertEquals(ball.getBallFace().toString(),circle);
    }

    @Test
    void moveTo() {
        ball.moveTo(new Point2D(0,0));
        assertEquals(ball.getPosition(),new Point2D(0,0));
    }

    @Test
    void setPoints() {
        ball.setPoints();
        assertEquals(ball.getUp(), new Point2D(10,5));
        assertEquals(ball.getDown(), new Point2D(10,15));
        assertEquals(ball.getLeft(), new Point2D(5,10));
        assertEquals(ball.getRight(), new Point2D(15,10));
    }

    @Test
    void setSpeedX() {
        ball.setSpeedX(1);
        assertEquals(ball.getSpeedX(),1);
    }

    @Test
    void setSpeedY() {
        ball.setSpeedY(2);
        assertEquals(ball.getSpeedY(),2);
    }

    @Test
    void getSpeedX() {
        ball.setSpeedX(3);
        assertEquals(3,ball.getSpeedX());
    }

    @Test
    void getSpeedY() {
        ball.setSpeedY(4);
        assertEquals(4,ball.getSpeedY());
    }
}