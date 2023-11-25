package code.dms_coursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

/**
 * GamApp class acts as an entry to the whole game and creates a start window
 * @author Cheng Qin
 */
public class GameApp extends Application {

    /**
     * Create a start window using fxml file of the game
     * @param stage A Stage object that represents the root stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        URL url = new URL("file:src\\main\\resources\\code\\dms_coursework\\controller\\startView.fxml");
        AnchorPane startMenu = FXMLLoader.load(url);
        // load background image
        Image image = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\start.jpg", 600,450, false,true);
        BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        startMenu.setBackground(new Background(myBI));

        Scene scene = new Scene(startMenu,600,450);
        stage.setTitle("Breakout");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}