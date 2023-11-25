package code.dms_coursework.controller;

import code.dms_coursework.model.Game;
import code.dms_coursework.view.GameView;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;

/**
 * GameController will handle all interactive events that occur in the gameView, and also provides a timer for gameView
 * @author Cheng Qin-modified
 */
public class GameController {

    private GameView gameView;
    private Game game;
    private AnimationTimer gameTimer;
    private boolean timer;
    private MediaPlayer backgroundMusic;
    private MediaPlayer startSound;
    private double volumePercent;

    /**
     * Create timer and check the win and lose situation to stop it, and initialize the sound
     * @param volumePercent A double that represents the volume of the sound
     */
    public GameController(double volumePercent) {
        super();
        this.volumePercent = volumePercent;
        GameView gameView = new GameView(volumePercent);
        this.gameView = gameView;
        Game game = new Game(this.gameView);
        this.game = game;
        this.backgroundMusic = game.backMusicLoad();
        this.startSound = game.startSoundLoad();
        this.backgroundMusic.setVolume(0.5 * volumePercent);
        this.startSound.setVolume(volumePercent);

        // create animation
        timer = false;
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                backgroundMusic.play();
                gameView.getImpact().move();
                gameView.getImpact().findImpacts();
                String string = String.format("Level: %d       Balls: %d       Score: %d", gameView.getImpact().getLevel()+1,
                gameView.getImpact().getBallCount(), gameView.getImpact().getScores());
                gameView.setMessage(string);
                gameView.getSprite().updateSpeed(gameView.getImpact().getPlayer().getMoveAmount());
                // check the loses
                if (gameView.getImpact().isBallLost()) {
                    if (gameView.getImpact().getReset().ballEnd()) {
                        backgroundMusic.stop();
                        game.gameFinish(gameView.getImpact().getScores());
                    }
                    timer=false;
                    gameTimer.stop();
                    // reset to continue next ball
                    gameView.getImpact().getReset().ballReset();
                    gameView.getImpact().getPlayer().resetWidth();
                    gameView.getImpact().getPlayer().resetSpeed();
                    gameView.getSprite().updateSpeed(gameView.getImpact().getPlayer().getMoveAmount());
                }

                // check the wins
                else if (gameView.getImpact().getReset().isDone()) {
                    int award = (int) ((gameView.getImpact().getBallCount()*0.1)*gameView.getImpact().getScores());
                    if (award < 1) { award = 1; }
                    gameView.getImpact().setScores((gameView.getImpact().getScores()) + award);
                    if (gameView.getImpact().getReset().hasLevel()) {
                        gameView.setMessage("\t\tReward "+award+" points :)");
                        timer = false;
                        gameTimer.stop();
                        // reset to go to next level
                        gameView.getImpact().getReset().ballReset();
                        gameView.getImpact().getReset().nextLevel();
                        gameView.getImpact().getPlayer().resetWidth();
                        gameView.getImpact().getPlayer().resetSpeed();
                        gameView.getSprite().updateSpeed(gameView.getImpact().getPlayer().getMoveAmount());
                    } else {
                        backgroundMusic.stop();
                        game.gameFinish(gameView.getImpact().getScores());
                    }
                }
                gameView.requestFocus();
                keyPressed();
                keyReleased();
                gameView.getChildren().clear();
                gameView.paint();
                if (gameView.isFocused() == false) {
                    onLostFocus();
                }
            }
        };
        startSound.play();
        gameTimer.start();
    }

    /**
     * Handle the keyboard event in the gameView include:
     * <br>     [←] [→] to control the move of player, [space] to pause, [Esc] to call the pause menu and [alt+shift+f1] to call the debug menu
     */
    public void keyPressed() {
        gameView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();
                if(code == KeyCode.LEFT){
                    gameView.getImpact().getPlayer().moveLeft();
                    gameView.getSprite().moveLeft();
                }
                if(code == KeyCode.RIGHT){
                    gameView.getImpact().getPlayer().movRight();
                    gameView.getSprite().movRight();
                }
                if(code == KeyCode.SPACE){
                    if(timer) {
                        backgroundMusic.pause();
                        timer = false;
                        gameTimer.stop();
                    }
                    else {
                        if (gameView.getImpact().getBall().getPosition().getX() == 300) {
                            startSound = game.startSoundLoad();
                            startSound.setVolume(volumePercent);
                            startSound.play();}
                        timer = true;
                        gameTimer.start();
                    }
                }
                if (code == KeyCode.ESCAPE) {
                    backgroundMusic.pause();
                    gameTimer.stop();
                    timer = false;
                    PauseController pauseController = new PauseController(getGameConsole());
                    pauseController.MouseClicked();
                }
                // method to check multiple keys
                if(code == KeyCode.F1){
                    if(keyEvent.isAltDown() && keyEvent.isShiftDown()) {
                        backgroundMusic.pause();
                        timer = false;
                        gameTimer.stop();
                        DebugController debugController = new DebugController(getGameBoard());
                        debugController.MouseClicked();
                    }
                }
                gameView.getChildren().clear();
                gameView.paint();
            }
        });
    }

    /**
     * Handle the keyboard event that stop the player when release the keyboard
     */
    public void keyReleased() {
        gameView.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                gameView.getImpact().getPlayer().stop();
                gameView.getSprite().stop();
                gameView.getChildren().clear();
                gameView.paint();
            }
        });
    }

    /**
     * Handle the focus lost situation
     */
    public void onLostFocus(){
        backgroundMusic.pause();
        timer = false;
        gameTimer.stop();
        gameView.setMessage("\t\t    Focus Lost");
        gameView.getChildren().clear();
        gameView.paint();
    }

    /**
     * Get the GameView of the game
     * @return A GameView object that represents the game board of the game
     */
    public GameView getGameBoard() {
        return gameView;
    }

    /**
     * Get the GameController itself
     * @return A GameController object
     */
    public GameController getGameConsole() {
        return this;
    }

    /**
     * Check the status of the gameTimer
     * @return A boolean that represents the status of the gameTimer
     */
    public boolean isTimer() {
        return timer;
    }

    /**
     * Get the timer of the game
     * @return An AnimationTimer represents the timer of the game
     */
    public AnimationTimer getGameTimer() {
        return gameTimer;
    }
}