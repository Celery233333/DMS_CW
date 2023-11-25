package code.dms_coursework.model.Brick;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrickTest {
    Brick brick;

    @BeforeEach
    void reset() {
        Point2D p = new Point2D(10,10);
        brick = new Brick("test",p,5,10, Color.BLACK,null,1) {
            @Override
            protected Rectangle makeBrickFace(Point2D pos, int height, int width) {
                brickFace = new Rectangle(pos.getX(),pos.getY(),width,height);
                return brickFace;
            }

            @Override
            public Rectangle getBrick() {
                return brickFace;
            }
        };
    }

    @Test
    void getCrack() {
        assertNull(brick.getCrack());
    }

    @Test
    void setCrack() {
        Polyline pl = new Polyline(12,12);
        brick.setCrack(pl);
        assertEquals(brick.getCrack().toString(),new Polyline(12,12).toString());
    }

    @Test
    void getName() {
        assertEquals(brick.getName(),"test");
    }

    @Test
    void makeBrickFace() {
        brick.makeBrickFace(new Point2D(10,10),5,10);
        assertEquals(new Rectangle(10,10,10,5).toString(),brick.getBrick().toString());
    }

    @Test
    void setImpact() {
        assertTrue(brick.setImpact(new Point2D(0,0),0));
    }

    @Test
    void getBrick() {
        brick.makeBrickFace(new Point2D(10,10),5,10);
        assertEquals(new Rectangle(10,10,10,5).toString(),brick.getBrick().toString());
    }

    @Test
    void getBorderColor() {
        assertEquals(brick.getBorderColor(),Color.BLACK);
    }

    @Test
    void getInnerImage() {
        assertNull(brick.getInnerImage());
    }

    @Test
    void getScore() {
        assertEquals(brick.getScore(),0);
    }

    @Test
    void getChoice() {
        assertEquals(brick.getChoice(),-1);
    }

    @Test
    void isBroken() {
        assertEquals(brick.isBroken(),false);
    }

    @Test
    void repair() {
        brick.impact();
        assertEquals(brick.isBroken(),true);
        brick.repair();
        assertEquals(brick.isBroken(),false);
    }

    @Test
    void impact() {
        assertEquals(brick.isBroken(),false);
        brick.impact();
        assertEquals(brick.isBroken(),true);
    }

    @Test
    void destroy() {
        assertEquals(brick.isBroken(),false);
        brick.destroy();
        assertEquals(brick.isBroken(),true);
    }
}