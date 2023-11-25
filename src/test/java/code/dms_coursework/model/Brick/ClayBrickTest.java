package code.dms_coursework.model.Brick;

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

class ClayBrickTest {
    ClayBrick brick;

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
        brick = new ClayBrick(p,5,10);
    }

    @Test
    void makeBrickFace() {
        brick.makeBrickFace(new Point2D(10,10),5,10);
        assertEquals(new Rectangle(10,10,10,5).toString(),brick.getBrick().toString());
    }

    @Test
    void getBrick() {
        assertEquals(brick.getBrick().toString(),new Rectangle(10,10,10,5).toString());
    }
}