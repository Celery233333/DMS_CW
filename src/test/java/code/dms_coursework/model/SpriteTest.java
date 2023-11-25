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

class SpriteTest {
    Sprite sprite;

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
        Rectangle rectangle = new Rectangle(0, 0, 100, 100);
        sprite = new Sprite(p,10,10,rectangle);
    }

    @Test
    void getmImageView() {
        assertEquals(sprite.getmImageView().getImage().getUrl(),
                "file:src/main/resources/code/dms_coursework/image/stop.png");
    }

    @Test
    void moveLeft() {
        sprite.moveLeft();
        assertEquals(sprite.getmImageView().getImage().getUrl(),
                "file:src/main/resources/code/dms_coursework/image/left.png");
    }

    @Test
    void movRight() {
        sprite.movRight();
        assertEquals(sprite.getmImageView().getImage().getUrl(),
                "file:src/main/resources/code/dms_coursework/image/right.png");
    }

    @Test
    void move() {
        sprite.move();
        assertEquals(sprite.getmImageView().getLayoutX(),0);
        assertEquals(sprite.getmImageView().getLayoutY(),0);
    }

    @Test
    void moveTo() {
        sprite.moveTo(new Point2D(1,1));
        // initial point is ballPos.add(-17,0)
        assertEquals(sprite.getmImageView().getLayoutX(),-16);
        assertEquals(sprite.getmImageView().getLayoutY(),1);
    }

    @Test
    void stop() {
        sprite.movRight();
        assertEquals(sprite.getmImageView().getImage().getUrl(),
                "file:src/main/resources/code/dms_coursework/image/right.png");
        sprite.stop();
        assertEquals(sprite.getmImageView().getImage().getUrl(),
                "file:src/main/resources/code/dms_coursework/image/stop.png");
    }
}