package Education;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class mathController extends GameController {

    @FXML
    private Label exerciseLabel;

    @FXML
    private TextField guessField;

    private Math game = new Math();

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
