package code.dms_coursework.model.Brick;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class BrickFactoryTest {

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

    @Test
    void makeBrick() {
        BrickFactory brickFactory = new BrickFactory();
        ClayBrick test = new ClayBrick(new Point2D(0,0),10,10);
        assertEquals(test.getBrick().toString(),
                brickFactory.makeBrick("Clay Brick",new Point2D(0,0),10,10).getBrick().toString());
    }
}