package Education;

import GUI.AlertClass;
import GUI.SceneController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import javax.script.ScriptException;
import java.io.IOException;

public abstract class GameController implements Observer {
    public Label timerLabel;

//    protected Game game = new Game();

    public GameController() {
        ObservableWithTypes events = game.getEvents();
        events.subscribe("timer", this);
        events.subscribe("endGame", this);
        events.subscribe("newHighScore", this);
    }

    public abstract void nextQuestion() throws IOException, ScriptException;

    public void updateTimer(Game game) {
        Platform.runLater(() -> timerLabel.setText(game.getSecondsLeft().toString()));
    }

    public void showFinalScore(Game game) throws IOException {
        Platform.runLater(() -> AlertClass.showAlert(Alert.AlertType.INFORMATION, "Your score was: " + game.getCurrentscore() + "!"));
        SceneController.switchTo("education");
    }

    public void checkAnswer(String input, String expected) {
        game.checkAnswer(input, expected);
    }

    public void newHighscore() {
        Platform.runLater(() -> AlertClass.showAlert(Alert.AlertType.INFORMATION, "You reached a new highscore!"));
    }

    @Override
    public void update(String eventType, Game game) {
        switch (eventType) {
            case "timer":
                updateTimer(game);
                break;
            case "endGame":
                try {
                    System.out.println("Hello??");
                    showFinalScore(game);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "newHighScore":
                newHighscore();
                break;
        }
    }
}
