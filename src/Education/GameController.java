package Education;

import GUI.AlertClass;
import GUI.*;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public abstract class GameController {

    private Timer timer = new Timer();
    private Integer secondsLeft = 60;
    protected Game game;

    public void startTimer(Label timerLabel) {
        getTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setSecondsLeft(getSecondsLeft()-1, game);
                Platform.runLater(() ->  timerLabel.setText(getSecondsLeft().toString()));
            }
        }, 0, 1000);
    }

    public abstract void nextQuestion() throws IOException, ScriptException;

    public void stopTimer(){
        timer.cancel();
        timer.purge();
    }

    public void setSecondsLeft(Integer secondsLeft, Game game) {
        this.secondsLeft = secondsLeft;
        if (secondsLeft <= 0){
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
        saveNewHS();
    }

    public void showFinalScore(Game game){
        AlertClass.showAlert(Alert.AlertType.INFORMATION, "Your score was: " + game.getCurrentscore()+"!");
    }
    public void saveNewHS(){
        if (game.getCurrentscore() > singletonPerson.getInstance().getHS(game.GetCurrentGameType()).getHighScore()){
            singletonPerson.getInstance().getHS(game.GetCurrentGameType()).setHighscore(game.getCurrentscore());
            AlertClass.showAlert(Alert.AlertType.INFORMATION, "You reached a new highscore!");
            //fakeDatabase.addReadingHS(new Highscores(game.GetCurrentGameType(),game.getCurrentscore(),singletonPerson.getInstance().getName()), game.GetCurrentGameType());
        }
    }
    public Integer getSecondsLeft(){
        return secondsLeft;
    }

    public Timer getTimer(){
        return timer;
    }

    public void checkAnswer(String input, String expected){
        if(expected.equals(input)){
            game.setCurrentscore(game.getCurrentscore() + 1);
            System.out.println("+1 score");
        }
        else{
            setSecondsLeft(getSecondsLeft() - 10, game);
        }
    }

}
