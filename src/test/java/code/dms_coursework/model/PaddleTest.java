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

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaddleTest{
    Paddle paddle;
    Rectangle rectangle;

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
        rectangle = new Rectangle(0,0,100,100);
        paddle = new Paddle(p,10,1,rectangle);
    }

    @Test
    void makeRectangle() {
        paddle.makeRectangle(10,1);
        assertEquals(paddle.getPaddleFace().toString(),new Rectangle(5,10,10,1).toString());
    }

    @Test
    void impact() {
        Ball ball = new Ball(new Point2D(10,10),5);
        assertEquals(false,paddle.impact(ball));
    }

    @Test
    void move() {
        paddle.move();
        assertEquals(paddle.getPaddleFace().getX(),5);
        assertEquals(paddle.getPaddleFace().getY(),10);
    }

    @Test
    void moveLeft() {
        paddle.moveLeft();
        assertEquals(paddle.getPaddleFace().getX(),5);
        assertEquals(paddle.getPaddleFace().getY(),10);
    }

    @Test
    void movRight() {
        paddle.movRight();
        assertEquals(paddle.getPaddleFace().getX(),5);
        assertEquals(paddle.getPaddleFace().getY(),10);
    }


    @Test
    void getPaddleFace() {
        assertEquals(paddle.getPaddleFace().toString(),new Rectangle(5,10,10,1).toString());
    }

    @Test
    void getInner() {
        assertEquals(paddle.getInner().getUrl(),"file:src/main/resources/code/dms_coursework/image/paddle.png");
    }

    @Test
    void changeLong() {
        assertEquals(paddle.getWidth(),10);
        paddle.changeLong();
        assertEquals(paddle.getWidth(),30);
    }

    @Test
    void resetWidth() {
        paddle.resetWidth();
        assertEquals(paddle.getWidth(),110);
    }

    @Test
    void getWidth() {
        assertEquals(paddle.getWidth(),10);
    }

    @Test
    void changeShort() {
        paddle.resetWidth();
        paddle.changeShort();
        assertEquals(paddle.getWidth(),100);
    }

    @Test
    void speedUp() {
        paddle.speedUp();
        assertEquals(paddle.getMoveAmount(),3);
    }

    @Test
    void speddDown() {
        paddle.speddDown();
        assertEquals(paddle.getMoveAmount(),2);
    }

    @Test
    void resetSpeed() {
        paddle.speedUp();
        assertEquals(paddle.getMoveAmount(),3);
        paddle.resetSpeed();
        assertEquals(paddle.getMoveAmount(),2.5);
    }

    @Test
    void getMoveAmount() {
        assertEquals(paddle.getMoveAmount(),2.5);
    }

    @Test
    void moveTo() {
        paddle.moveTo(new Point2D(10,10));
        assertEquals(paddle.getPaddleFace().getX(),5);
        assertEquals(paddle.getPaddleFace().getY(),10);
    }
}