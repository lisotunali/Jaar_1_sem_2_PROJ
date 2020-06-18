package GUI.Controllers.Educational;

import BACKEND.Education.Math;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class mathController extends GameController {
    private final Math game = new Math();
    public Label exerciseLabel;
    public TextField guessField;

    public void initialize() {
        System.out.println("Initializing..");
        super.init(game);
    }

    @Override
    public void nextQuestion() {
        game.nextQuestion();
        exerciseLabel.setText(game.getQuestion());
    }

    public void doneClicked() {
        game.checkAnswer(guessField.getText());
        guessField.clear();
        nextQuestion();
    }
}
