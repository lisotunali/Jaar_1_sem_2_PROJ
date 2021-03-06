package GUI.Controllers.Educational;

import BACKEND.Education.BaseGame;
import BACKEND.Education.IGame;
import BACKEND.Education.ObservableWithTypes;
import BACKEND.Education.Observer;
import GUI.Controllers.Utility.AlertClass;
import GUI.SceneController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.IOException;

public abstract class GameController implements Observer {
    public Label timerLabel;
    private IGame game;

    public void init(BaseGame game) {
        this.game = game;

        nextQuestion();
        game.startTimer();

        ObservableWithTypes events = game.getEvents();
        events.subscribe("timer", this);
        events.subscribe("endGame", this);
        events.subscribe("newHighScore", this);
    }

    public abstract void nextQuestion();

    private void updateTimer(BaseGame game) {
        Platform.runLater(() -> timerLabel.setText(game.getSecondsLeft().toString()));
    }

    private void showFinalScore(BaseGame game) throws IOException {
        Platform.runLater(() -> AlertClass.showAlert(Alert.AlertType.INFORMATION, "Your score was: " + game.getCurrentscore() + "!"));
        backButtonClicked();
    }

    private void newHighscore() {
        Platform.runLater(() -> AlertClass.showAlert(Alert.AlertType.INFORMATION, "You reached a new highscore!"));
    }

    @Override
    public void update(String eventType, BaseGame game) {
        switch (eventType) {
            case "timer":
                updateTimer(game);
                break;
            case "endGame":
                try {
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

    public void backButtonClicked() throws IOException {
        game.stopTimer();
        SceneController.switchTo("Educational/education");
    }
}
