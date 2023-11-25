package code.dms_coursework.controller;

import code.dms_coursework.model.Game;
import code.dms_coursework.view.GameView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DebugControllerTest {

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
    void mouseClicked() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Scene scene = new Scene(gameView,600,450);
                Stage stage = new Stage();
                stage.setScene(scene);
                DebugController debugController = new DebugController(gameView);
                debugController.MouseClicked();
            }
        });
    }
}