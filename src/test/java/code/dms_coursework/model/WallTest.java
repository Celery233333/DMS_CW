package code.dms_coursework.model;

import code.dms_coursework.model.Brick.Brick;
import code.dms_coursework.model.Brick.CementBrick;
import code.dms_coursework.model.Brick.ClayBrick;
import code.dms_coursework.model.Brick.SteelBrick;
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

// award, question, tnt brick is randomly generated, so it may fail the test sometimes
// the reason for the fail is I check the type of the bricks in the test, so if the wall generate the above bricks, it will
// fail, so it is normal situation to see a fail with (expected: award/question/tnt brick)
class WallTest {
    Wall wall;
    Rectangle rectangle = new Rectangle(0,0,10,10);

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
        wall = new Wall(rectangle,10,1,1);
    }

    @Test
    void getLevels() {
        Brick test[][] = new Brick[6][2];
        test[0][0] = new ClayBrick(new Point2D(0,0),1,1);
        test[0][1] = new ClayBrick(new Point2D(1,0),1,1);
        test[1][0] = new ClayBrick(new Point2D(0,0),1,1);
        test[1][1] = new CementBrick(new Point2D(1,0),1,1);
        test[2][0] = new ClayBrick(new Point2D(0,0),1,1);
        test[2][1] = new SteelBrick(new Point2D(1,0),1,1);
        test[3][0] = new CementBrick(new Point2D(0,0),1,1);
        test[3][1] = new CementBrick(new Point2D(1,0),1,1);
        test[4][0] = new SteelBrick(new Point2D(0,0),1,1);
        test[4][1] = new CementBrick(new Point2D(1,0),1,1);
        test[5][0] = new SteelBrick(new Point2D(0,0),1,1);
        test[5][1] = new SteelBrick(new Point2D(1,0),1,1);

        for (int i=0;i<6;i++) {
            assertEquals(wall.getLevels()[i][0].getBrick().toString(), test[i][0].getBrick().toString());
            assertEquals(wall.getLevels()[i][0].getName(), test[i][0].getName());
            assertEquals(wall.getLevels()[i][1].getBrick().toString(), test[i][1].getBrick().toString());
            assertEquals(wall.getLevels()[i][1].getName(), test[i][1].getName());
        }
    }

    @Test
    void makeSingleTypeLevel() {
        Brick temp[] = wall.makeSingleTypeLevel(rectangle,2,1,1,"Clay Brick");
        Brick test[] = new Brick[2];
        test[0] = new ClayBrick(new Point2D(0,0),5,5);
        test[1] = new ClayBrick(new Point2D(5,0),5,5);

        assertEquals(temp[0].getBrick().toString(),test[0].getBrick().toString());
        assertEquals(temp[1].getBrick().toString(),test[1].getBrick().toString());
    }

    @Test
    void makeChessboardLevel() {
        Brick temp[] = wall.makeChessboardLevel(rectangle,2,1,1,"Clay Brick","Cement Brick");
        Brick test[] = new Brick[2];
        test[0] = new ClayBrick(new Point2D(0,0),5,5);
        test[1] = new CementBrick(new Point2D(5,0),5,5);

        assertEquals(temp[0].getBrick().toString(),test[0].getBrick().toString());
        assertEquals(temp[1].getBrick().toString(),test[1].getBrick().toString());
        assertEquals(temp[0].getName(),test[0].getName());
        assertEquals(temp[1].getName(),test[1].getName());
    }

    @Test
    void makeLevels() {
        Brick temp[][] = wall.makeLevels(rectangle,2,1,1);
        Brick test[][] = new Brick[6][2];
        test[0][0] = new ClayBrick(new Point2D(0,0),5,5);
        test[0][1] = new ClayBrick(new Point2D(5,0),5,5);
        test[1][0] = new ClayBrick(new Point2D(0,0),5,5);
        test[1][1] = new CementBrick(new Point2D(5,0),5,5);
        test[2][0] = new ClayBrick(new Point2D(0,0),5,5);
        test[2][1] = new SteelBrick(new Point2D(5,0),5,5);
        test[3][0] = new CementBrick(new Point2D(0,0),5,5);
        test[3][1] = new CementBrick(new Point2D(5,0),5,5);
        test[4][0] = new SteelBrick(new Point2D(0,0),5,5);
        test[4][1] = new CementBrick(new Point2D(5,0),5,5);
        test[5][0] = new SteelBrick(new Point2D(0,0),5,5);
        test[5][1] = new SteelBrick(new Point2D(5,0),5,5);

        for (int i=0;i<6;i++) {
            assertEquals(temp[i][0].getBrick().toString(), test[i][0].getBrick().toString());
            assertEquals(temp[i][0].getName(), test[i][0].getName());
            assertEquals(temp[i][1].getBrick().toString(), test[i][1].getBrick().toString());
            assertEquals(temp[i][1].getName(), test[i][1].getName());
        }
    }
}