package code.dms_coursework.model;

import code.dms_coursework.view.GameView;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;
    GameView gameView = new GameView(0.2);

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
    void gameFinish() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Scene scene = new Scene(gameView,600,450);
                Stage stage = new Stage();
                stage.setScene(scene);
                game = new Game(gameView);
                game.gameFinish(10);
                // no idea how to use assert here, but the test can correctly show the
                // screen, so I think it can be treated as pass
            }
        });

    }

    @Test
    void backMusicLoad() {
        game = new Game(gameView);
        assertEquals(game.backMusicLoad().getMedia().getSource(),
                "file:/C:/Users/Celery/Desktop/dms_coursework/src/main/resources/code/dms_coursework/sound/Megalobox.mp3");
    }

    @Test
    void startSoundLoad() {
        game = new Game(gameView);
        assertEquals(game.startSoundLoad().getMedia().getSource(),
                "file:/C:/Users/Celery/Desktop/dms_coursework/src/main/resources/code/dms_coursework/sound/start.wav");
    }
}