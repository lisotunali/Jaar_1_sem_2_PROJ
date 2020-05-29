package Education;

import GUI.SceneController;
import GUI.fakeDatabase;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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

    public void initialize() throws IOException {
        game = new Game();
        game.saveQuestionsLocally();
        shuffleQuestions();
        nextQuestion();
        startTimer(timerLabel);
    }

    public void nextQuestion() throws IOException {
        game.setCurrentQuestion(game.getCurrentQuestion()+1);
        if (game.getCurrentGameQuestions().size() -1 == game.getCurrentQuestion()){
            endGame(game);
            System.out.println("Game ended");
            return;
        }
        else {
            imageView.setImage(game.getCurrentGameQuestions().get(game.getCurrentQuestion()).getImage());
            System.out.println("image set - index" + game.getCurrentQuestion());
        }

    }

    public void shuffleQuestions(){
        Collections.shuffle(game.getCurrentGameQuestions());
    }

    public void doneClicked() throws IOException {
        checkAnswer(guessField.getText(), game.getCurrentGameQuestions().get(game.getCurrentQuestion()).getName());
        guessField.clear();
        nextQuestion();
    }

}
