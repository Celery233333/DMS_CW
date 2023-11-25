package code.dms_coursework.controller;

import code.dms_coursework.view.GameView;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

/**
 * PauseController will handle all interactive events that occur in the PauseView, pass the effect back to gameView
 * @author Cheng Qin-modified
 */
public class PauseController {
    private GameView gameView;
    private AnimationTimer gameTimer;
    private boolean timer;

    /**
     * Initialize the DebugController using the GameController (need to use timer)
     * @param gameController A GameController object that will receive the effect and provide timer
     * @see code.dms_coursework.controller.GameController
     */
    public PauseController(GameController gameController) {
        this.gameView = gameController.getGameBoard();
        this.gameTimer = gameController.getGameTimer();
        this.timer = gameController.isTimer();
    }

    /**
     * Handles buttons events in the PauseView include:
     * <br>     open or close the pause window, continue the game, back to start menu
     */
    public void MouseClicked() {
        try {
            AnchorPane PauseMenu = FXMLLoader.load(getClass().getResource("PauseView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(PauseMenu,600,450);
            gameView.setStyle("-fx-opacity:0.5;");
            PauseMenu.setStyle("-fx-background-color:transparent;");

            // load background image
            Image image = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\pause.png");
            scene.setFill(new ImagePattern(image));
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setX(gameView.getScene().getWindow().getX()+85);
            stage.setY(gameView.getScene().getWindow().getY()+50);

            PauseMenu.requestFocus();
            // open or close pause window using Esc
            PauseMenu.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    KeyCode code = keyEvent.getCode();
                    if (code == KeyCode.ESCAPE) {
                        gameView.setStyle("-fx-opacity:1;");
                        if (!timer) {
                            gameTimer.start();
                            timer = true;
                        }
                        gameView.getImpact().getPlayer().stop();
                        stage.close();
                    }
                }
            });

            // continue the game
            PauseMenu.lookup("#continuee").setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    gameView.setStyle("-fx-opacity:1;");
                    if (!timer) {
                        gameTimer.start();
                        timer = true;
                    }
                    gameView.getImpact().getPlayer().stop();
                    stage.close();
                }
            });

            // restart the whole game including level
            PauseMenu.lookup("#restart").setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    gameView.setStyle("-fx-opacity:1;");
                    gameView.getChildren().clear();
                    gameView.setMessage("\t\tRestarting Game...");
                    gameView.getImpact().getReset().ballReset();
                    gameView.getImpact().getReset().wallReset();
                    gameView.paint();
                    stage.close();
                }
            });

            // back to start menu
            PauseMenu.lookup("#menu").setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Stage oldStage = (Stage) gameView.getScene().getWindow();
                    oldStage.close();

                    // create the start panel
                    AnchorPane startMenu = null;
                    try {
                        startMenu = FXMLLoader.load(getClass().getResource("StartView.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // load background image
                    Image image = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\start.jpg", 600,450, false,true);
                    BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                    startMenu.setBackground(new Background(myBI));

                    Scene scene = new Scene(startMenu,600,450);
                    Stage stage = new Stage();
                    stage.setTitle("Breakout");
                    stage.setScene(scene);
                    stage.setResizable(false);

                    stage.setX(gameView.getScene().getWindow().getX()+85);
                    stage.setY(gameView.getScene().getWindow().getY()+25);
                    stage.show();
                }
            });

            // lock the original window
            Stage oldStage = (Stage) gameView.getScene().getWindow();
            stage.initOwner(oldStage);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
