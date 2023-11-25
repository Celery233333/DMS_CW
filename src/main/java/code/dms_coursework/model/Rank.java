package code.dms_coursework.model;

import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.*;
import java.util.Optional;

/**
 * The Rank class support the relevant operations about high score board as a model obey the MVC pattern
 * @author Cheng Qin
 * @see code.dms_coursework.controller.RankController
 */
public class Rank {

    private File highScore;
    private String[][] data = new String[5][2];

    /**
     * Create a new instance of Rank, automatically store the file into a String array in the program
     * @param highScore A File object that represents the file program need to store
     */
    public Rank(File highScore) {
        this.highScore = highScore;
        try {
            initialize();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the file to the program line by line into a String array
     * @throws IOException
     */
    public void initialize() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(highScore.getAbsoluteFile()));
        String text;
        int counter = 0;
        for(int i=0;i<5;i++) {
            text = in.readLine();
            if (text != null) {
                data[counter++] = text.split(";");
            }
            // initialize the null part
            else {
                data[counter][0] = "null";
                data[counter++][1] = "0";
            }
        }
        in.close();
    }

    /**
     * Get the String array that used to store the file data
     * @return A String[][] object that represents the file data
     */
    public String[][] getData() {
        return data;
    }

    /**
     * Check whether the specified score is in top5 of the data
     * @param score An int that represents the score user get
     * @return Return A positive int that represents the exact rank<br>Return -1 that represents that input score is not in the top5
     */
    public int check(int score) {
        int number = -1;
        // check which score to replace
        for (int j=0;j<5;j++) {
            if (score > Integer.parseInt(data[j][1])){
                return j;
            }
        }
        return number;
    }

    /**
     * Modify the String array that used to store high score according to the specified name, score and rank
     * @param name A String that represents the name of the user
     * @param score An int that represents the score that user get
     * @param number An int that represents the rank that user get
     */
    public void modify(String name, int score, int number) {
        for (int i=3;i>number-1;i--) {
            data[i+1][0] = data[i][0];
            data[i+1][1] = data[i][1];
        }
        data[number][0] = name;
        data[number][1] = String.valueOf(score);
    }

    /**
     * Write the String array back to the input file that creates the object
     * @throws IOException
     */
    public void writeBack() throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(highScore.getAbsoluteFile()));
        for (int i=0;i<5;i++) {
            if (data[i][1] != "0") {
                out.write(data[i][0]+";"+data[i][1]+"\n");
            }
        }
        out.close();
    }

    /**
     * Create a request dialog that user can type the name
     * @param score An int that represents the score user get
     * @return A String that represents the user's name
     */
    public String requestName(int score) {
        File music = new File("src\\main\\resources\\code\\dms_coursework\\sound\\We_Are_The_Champions.mp3");
        MediaPlayer audio = new MediaPlayer(new Media(music.toURI().toString()));
        audio.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
        audio.play();

        // create the dialog
        TextInputDialog dialog = new TextInputDialog("Celery");
        dialog.setTitle("Congratulations");
        dialog.setHeaderText("Congratulations, you got "+score+ " points!!!!!!\n" +
                "You can leave your name on the Rank menu:)");
        dialog.setContentText("Please enter your name:");

        // add congratulation image
        Image image = new Image("file:src\\main\\resources\\code\\dms_coursework\\image\\congratulation.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        dialog.setGraphic(imageView);
        Optional<String> name = dialog.showAndWait();

        audio.stop();
        // check user click ok or cancel
        if (name.isPresent()) {
            return name.get();
        }
        return null;
    }
}
