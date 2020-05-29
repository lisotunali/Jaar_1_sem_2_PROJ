package Education;

import GUI.SceneController;
import GUI.fakeDatabase;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimerTask;

public class writingController extends GameController{

    @FXML
    private ImageView imageView;

    @FXML
    private TextField guessField;

    @FXML
    private Button guessButton;

    @FXML
    private Label timerLabel;

    @FXML
    private Text letterText;

    private ArrayList<ImageWithName> questions = new ArrayList<>();

    public void initialize() throws IOException {
        game = new Game();
        game.saveQuestionsLocally();
        questions = game.getCurrentGameQuestions();
        shuffleQuestions();
        nextQuestion();
        startTimer(timerLabel);
    }

    public void nextQuestion() throws IOException {
        game.setCurrentQuestion(game.getCurrentQuestion()+1);
        if (questions.size() -1 == game.getCurrentQuestion()){
            endGame(game);
            System.out.println("Game ended");
            return;
        }
        else {
            imageView.setImage(questions.get(game.getCurrentQuestion()).getImage());
            StringBuilder dashes = new StringBuilder();
            for(int i = 0; i < questions.get(game.getCurrentQuestion()).getName().length(); i++){
                dashes.append("_ ");
            }
            guessField.setPromptText(questions.get(game.getCurrentQuestion()).getName().length() + " letters");
            letterText.setText(dashes + "    " + questions.get(game.getCurrentQuestion()).getName().length() + " letters");
            System.out.println("image set - index" + game.getCurrentQuestion());
        }

    }

    public void shuffleQuestions(){
        Collections.shuffle(questions);
    }

    public void doneClicked() throws IOException {
        checkAnswer(guessField.getText(), questions.get(game.getCurrentQuestion()).getName());
        guessField.clear();
        nextQuestion();
    }

}
