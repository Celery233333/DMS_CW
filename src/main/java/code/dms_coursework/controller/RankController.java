package code.dms_coursework.controller;

import code.dms_coursework.model.Rank;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.io.File;
import java.io.IOException;


/**
 * RankController will read local files to the RankView
 * @author Cheng Qin
 */
public class RankController {
    @FXML Label name1;
    @FXML Label name2;
    @FXML Label name3;
    @FXML Label name4;
    @FXML Label name5;

    @FXML Label score1;
    @FXML Label score2;
    @FXML Label score3;
    @FXML Label score4;
    @FXML Label score5;

    /**
     * Automatically update the information in the program
     * @throws IOException
     */
    @FXML public void initialize() throws IOException {
        File file = new File("src\\main\\resources\\code\\dms_coursework\\highScore.txt");
        // create file if it is not exist
        if (!file.exists()) {
            file.createNewFile();
            return;
        }

        Rank rank = new Rank(file);
        String[][] data = rank.getData();
        mySet(data,0,name1,score1);
        mySet(data,1,name2,score2);
        mySet(data,2,name3,score3);
        mySet(data,3,name4,score4);
        mySet(data,4,name5,score5);
    }

    /**
     * Set the text for the labels in RankView
     * @param data A String[][] object that contains the users' names and scores
     * @param number An int that represents the rank of the user
     * @param name A Label from RankView that used to receive the names
     * @param score A Label from RankView that used to receive the scores
     */
    public void mySet(String[][] data, int number, Label name, Label score) {
        if (data[number][1] != "0") {
            name.setText(data[number][0]);
            score.setText(data[number][1]);
            name.setTextFill(Color.BLACK);
            score.setTextFill(Color.BLACK);
        }
    }
}
