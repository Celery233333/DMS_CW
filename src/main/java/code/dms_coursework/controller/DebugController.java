package code.dms_coursework.controller;

import code.dms_coursework.view.GameView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * DebugController will handle all interactive events that occur in the DebugView, pass the effect back to gameView
 * @author Cheng Qin-modified
 */
public class DebugController {
    private GameView gameView;

    /**
     * Initialize the DebugController using the GameView
     * @param gameView A GameView object that will receive the effect
     * @see code.dms_coursework.view.GameView
     */
    public DebugController(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Handles buttons and sliders events in the DebugView include:
     * <br>     skip level, reset ball numbers, modify the speed of x-asis and y-asis
     */
    public void MouseClicked() {
        try {
            // load the Debugview
            GridPane debugMenu = FXMLLoader.load(getClass().getResource("DebugView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(debugMenu,351,70);
            stage.setScene(scene);
            stage.setX(gameView.getScene().getWindow().getX()+gameView.getWidth()*0.23+85);
            stage.setY(gameView.getScene().getWindow().getY()+gameView.getHeight()*0.5);

            // go to next level(reset the ball to start point)
            debugMenu.lookup("#skip").setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    gameView.getChildren().clear();
                    gameView.getImpact().getReset().nextLevel();
                    gameView.getImpact().getReset().ballReset();
                    gameView.paint();
                }
            });

            // reset the ball count to 3
            debugMenu.lookup("#reset").setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    gameView.getImpact().getReset().resetBallCount();
                }
            });

            // bind the left slider with speedX
            Slider speedX = (Slider) debugMenu.lookup("#speedX");
            speedX.setValue(gameView.getImpact().getBall().getSpeedX());
            speedX.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    gameView.getChildren().clear();
                    // cannot set to 0
                    if ( speedX.getValue() != 0) {
                        gameView.getImpact().getBall().setSpeedX(speedX.getValue());}
                    gameView.paint();
                }
            });

            // bind the right slider with speedY
            Slider speedY = (Slider) debugMenu.lookup("#speedY");
            speedY.setValue(gameView.getImpact().getBall().getSpeedY());
            speedY.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    gameView.getChildren().clear();
                    // cannot set to 0
                    if (speedY.getValue() != 0){
                        gameView.getImpact().getBall().setSpeedY(speedY.getValue());}
                    gameView.paint();
                }
            });

            // lock the original window
            Stage oldStage = (Stage) gameView.getScene().getWindow();
            stage.initOwner(oldStage);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Debug Console");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
