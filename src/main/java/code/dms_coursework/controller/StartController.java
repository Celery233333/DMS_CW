package code.dms_coursework.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * StartController will handle all interactive events that occur in the StartView and SettingView, pass the effect back to gameView
 * @author Cheng Qin
 */
public class StartController {
    @FXML Text start;
    private int theme = 0;
    private double volumePercent = 0.2;

    /**
     * Handle the event of start button to create a new game
     */
    @FXML public void OnStartClicked() {
        // create the game menu
        GameController gameController = new GameController(volumePercent);
        Pane pane = gameController.getGameBoard();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(770,500);
        anchorPane.getChildren().add(pane);
        pane.setLayoutX(85);

        Image img_test = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\border.png", 770,500, false,true);
        BackgroundImage myBI_test = new BackgroundImage(img_test, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        anchorPane.setBackground(new Background(myBI_test));

        // load background image
        if (theme == 1) {
            Image img = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\theme2.jpg", 600,450, false,true);
            BackgroundImage myBI = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            pane.setBackground(new Background(myBI));
        }
        else {
            Image img = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\theme1.jpg", 600,450, false,true);
            BackgroundImage myBI = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            pane.setBackground(new Background(myBI));
        }

        Scene scene = new Scene(anchorPane,770,500);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Breakout");
        stage.setResizable(false);

        // set the position of the window
        stage.setX(start.getScene().getWindow().getX()-85);
        stage.setY(start.getScene().getWindow().getY()-25);
        stage.show();

        // close the start menu
        Stage oldStage = (Stage)start.getScene().getWindow();
        oldStage.close();
    }

    /**
     * Handle the event of rank button to create the rankView
     * @throws IOException
     */
    @FXML public void OnRankClicked() throws IOException {
        // load background image
        VBox rankMenu = FXMLLoader.load(getClass().getResource("RankView.fxml"));
        Image image = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\background.jpg", 600,450, false,true);
        BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        rankMenu.setBackground(new Background(myBI));

        Scene scene = new Scene(rankMenu,600,450);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Rank");
        stage.setResizable(false);

        // lock the original stage
        Stage oldStage = (Stage)start.getScene().getWindow();
        stage.initOwner(oldStage);
        stage.initModality(Modality.WINDOW_MODAL);

        // set the position of the window
        stage.setX(oldStage.getX());
        stage.setY(oldStage.getY());
        stage.show();
    }

    /**
     * Handle the event of setting button to create a settingView
     * @throws IOException
     */
    @FXML public void OnSettingClicked() throws IOException {
        AnchorPane settingMenu = FXMLLoader.load(getClass().getResource("SettingView.fxml"));
        // load background image
        Image image = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\background.jpg", 600,450, false,true);
        BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        settingMenu.setBackground(new Background(myBI));

        // add chocie values
        ChoiceBox choiceBox = (ChoiceBox) settingMenu.lookup("#choice");
        choiceBox.getItems().addAll("Theme1","Theme2");
        choiceBox.getSelectionModel().select(0);

        // check the value change in set menu
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov,
                                Number value, Number new_value) {
                theme = new_value.intValue();
            }
        });

        // bind the left slider with speedX
        Slider volume = (Slider) settingMenu.lookup("#volume");
        volume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                volumePercent = volume.getValue();
            }
        });

        Scene scene = new Scene(settingMenu,600,450);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Setting");
        stage.setResizable(false);

        // lock the original stage
        Stage oldStage = (Stage)start.getScene().getWindow();
        stage.initOwner(oldStage);
        stage.initModality(Modality.WINDOW_MODAL);

        // set the position of the window
        stage.setX(oldStage.getX());
        stage.setY(oldStage.getY());
        stage.show();
    }

}
