package code.dms_coursework.view;

import code.dms_coursework.model.Ball;
import code.dms_coursework.model.Brick.Brick;
import code.dms_coursework.model.Brick.CementBrick;
import code.dms_coursework.model.Paddle;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class GameViewTest {
    GameView gameView;

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
        gameView = new GameView(0.2);
    }


    @Test
    void getImpact() {
        assertEquals(gameView.getImpact().getBrickCount(),34);
        assertEquals(gameView.getImpact().getScores(),0);
        assertEquals(gameView.getImpact().getBallCount(),3);
        assertEquals(gameView.getImpact().getLevel(),0);
    }

    @Test
    void setMessage() {
        String message = "test";
        gameView.setMessage(message);
        assertEquals(gameView.lookup(message),null);
    }

    @Test
    void getSprite() {
        assertEquals(gameView.getSprite().getmImageView().getImage().getUrl(),
                "file:src/main/resources/code/dms_coursework/image/stop.png");
    }

    @Test
    void paint() {
        gameView.getChildren().clear();
        gameView.paint();
        Text text = (Text) gameView.getChildren().remove(0);
        assertEquals(text.getText(),"Press SPACE to start");
    }

    @Test
    void drawBrick() {
        gameView.getChildren().clear();
        Brick brick = new CementBrick(new Point2D(0,0),10,10);
        gameView.drawBrick(brick,gameView);
        assertEquals(gameView.getChildren().remove(0),brick.getBrick());
    }

    @Test
    void drawBall() {
        gameView.getChildren().clear();
        Ball ball = new Ball(new Point2D(0,0),10);
        gameView.drawBall(ball,gameView);
        assertEquals(gameView.getChildren().remove(0),ball.getBallFace());
    }

    @Test
    void drawPlayer() {
        gameView.getChildren().clear();
        Rectangle area = new Rectangle(0,0,100,100);
        Paddle paddle = new Paddle(new Point2D(0,0),10,10,area);
        gameView.drawPlayer(paddle,gameView);
        assertEquals(gameView.getChildren().remove(0),paddle.getPaddleFace());
    }
}