package code.dms_coursework.controller;

import javafx.application.Platform;
import javafx.scene.text.Text;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    GameController gameController;

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
        gameController = new GameController(0.2);
    }

    @Test
    void isTimer() {
        assertFalse(gameController.isTimer());
    }

    @Test
    void getGameTimer() {
        assertEquals(gameController.getGameTimer().getClass().getName(),
                "code.dms_coursework.controller.GameController$1");
    }

    @Test
    void getGameConsole() {
        assertSame(gameController.getGameConsole(),gameController);
    }

    @Test
    void getGameBoard() {
        assertEquals(gameController.getGameBoard().getImpact().getScores(),0);
        assertEquals(gameController.getGameBoard().getImpact().getLevel(),0);
    }

    @Test
    void onLostFocus() {
        gameController.onLostFocus();
        Text text = (Text) gameController.getGameBoard().getChildren().remove(0);
        assertEquals(text.getText(),"\t\t    Focus Lost");
    }




}