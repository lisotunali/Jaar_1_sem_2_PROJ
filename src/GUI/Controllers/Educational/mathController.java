package GUI.Controllers.Educational;

import BACKEND.Education.Math;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
    /*
Press enter instead of clicking on the "GUESS" button to confirm answer.
 */
    public void pressEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            doneClicked();
        }
    }

}
