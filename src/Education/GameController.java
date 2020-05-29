package Education;

import GUI.AlertClass;
import GUI.SceneController;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public abstract class GameController {

    private Timer timer = new Timer();
    private Integer secondsLeft = 11;

    public abstract void startTimer();

    public void stopTimer(){
        timer.cancel();
        timer.purge();
    }

    public void setSecondsLeft(Integer secondsLeft, Game game) {
        this.secondsLeft = secondsLeft;
        if (secondsLeft == 0){
            try {
                endGame(game);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void endGame(Game game) throws IOException {
        stopTimer();
        Platform.runLater(() ->  showFinalScore(game));
        SceneController.switchTo("education");
    }

    public void showFinalScore(Game game){
        AlertClass.showAlert(Alert.AlertType.INFORMATION, "Your score was: " + game.getCurrentscore()+"!");
    }

    public Integer getSecondsLeft(){
        return secondsLeft;
    }

    public Timer getTimer(){
        return timer;
    }


}
