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

class QuestionBrickTest {
    QuestionBrick brick;

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
        brick = new QuestionBrick(p,5,10);
    }

    @Test
    void setImpact() {
        assertFalse(brick.setImpact(new Point2D(10,10),100));
    }

    @Test
    void repair() {
        brick.impact();
        brick.impact();
        assertTrue(brick.isBroken());
        brick.repair();
        assertFalse(brick.isBroken());
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

    @Test
    void getChoice() {
        // this method will get a random choice from 0-9
        assertTrue(brick.getChoice()<10);
        assertTrue(0<=brick.getChoice());
    }
}