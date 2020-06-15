package Education;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class writingController extends GameController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField guessField;

    @FXML
    private Button guessButton;

    @FXML
    private Text letterText;

    private Writing game = new Writing();

    public void initialize() {
        super.init(game);
    }

    public void nextQuestion() {
        game.nextQuestion();

        ImageWithName imageWithName = game.getCurrentImage();
        int imageLength = imageWithName.getName().length();

        imageView.setImage(imageWithName.getImage());
        guessField.setPromptText(imageLength + " letters");
        letterText.setText("_ ".repeat(imageLength) + "    " + imageLength + " letters");

        System.out.println("image set - index" + game.getCurrentQuestion());
    }

    public void doneClicked() {
        game.checkAnswer(guessField.getText());
        guessField.clear();
        nextQuestion();
    }
}
