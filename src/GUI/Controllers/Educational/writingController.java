package GUI.Controllers.Educational;

import BACKEND.Education.ImageWithName;
import BACKEND.Education.Writing;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class writingController extends GameController {
    private final Writing game = new Writing();
    public ImageView imageView;
    public Text letterText;
    public TextField guessField;

    public void initialize() {
        super.init(game);
    }

    public void nextQuestion() {
        game.nextQuestion();

        ImageWithName imageWithName = game.getQuestion();
        int imageLength = imageWithName.getName().length();

        imageView.setImage(imageWithName.getImage());
        guessField.setPromptText(imageLength + " letters");
        letterText.setText("_ ".repeat(imageLength) + "    " + imageLength + " letters");
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
