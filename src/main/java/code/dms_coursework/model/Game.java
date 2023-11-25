package code.dms_coursework.model;

import code.dms_coursework.view.GameView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * The Game class support the relevant operations about game as a model obey the MVC pattern
 * @author Cheng Qin
 * @see code.dms_coursework.controller.GameController
 */
public class Game {
    private GameView gameView;
    private int score = 0;

    /**
     * Create an instance of Game with specified GameView
     * @param gameView A GameView object to execute the method
     */
    public Game(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Check whether the score from gameView is in top5 that can write back to file
     * @throws IOException
     */
    private void rankCheck() throws IOException {
        File file = new File("src\\main\\resources\\code\\dms_coursework\\highScore.txt");
        Rank rank = new Rank(file);
        int number = rank.check(gameView.getImpact().getScores());
        if (number != -1) {
            String name = rank.requestName(score);
            if (name != null) {
                rank.modify(name, gameView.getImpact().getScores(), number);
                try {
                    rank.writeBack();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Close the game window, and create request window for user's name and a new start window
     * @param score An int that represents the score user get
     */
    public void gameFinish(int score) {
        this.score = score;
        // close the game window
        Stage old = (Stage) gameView.getScene().getWindow();
        old.close();

        try {
            // create the start panel
            URL url = new URL("file:src\\main\\resources\\code\\dms_coursework\\controller\\startView.fxml");
            AnchorPane startMenu = FXMLLoader.load(url);
            // load background image
            Image image = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\start.jpg", 600,450, false,true);
            BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            startMenu.setBackground(new Background(myBI));

            Scene scene = new Scene(startMenu,600,450);
            Stage stage = new Stage();
            stage.setTitle("Breakout");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // check the rank request
        Runnable runnable = () -> {
            try {
                rankCheck();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Platform.runLater(runnable);
    }

    /**
     * Load background music for the game
     * @return A MediaPlayer object that represents the background music
     */
    public MediaPlayer backMusicLoad() {
        // load the background music
        File music = new File("src\\main\\resources\\code\\dms_coursework\\sound\\Megalobox.mp3");
        MediaPlayer backgroundMusic = new MediaPlayer(new Media(music.toURI().toString()));
        backgroundMusic.setVolume(0.5);
        backgroundMusic.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
        return backgroundMusic;
    }

    /**
     * Load start music for the game
     * @return A MediaPlayer object that represents the start music
     */
    public MediaPlayer startSoundLoad() {
        // load the ball start sound
        File music = new File("src\\main\\resources\\code\\dms_coursework\\sound\\start.wav");
        MediaPlayer startMusic = new MediaPlayer(new Media(music.toURI().toString()));
        return startMusic;
    }
}
