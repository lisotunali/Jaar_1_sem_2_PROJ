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

    private ArrayList<ImageWithName> newQuestions = new ArrayList<ImageWithName>(fakeDatabase.getImagesDatabase());
    private Writing game;
    private Integer currentQuestion = 0;

    public void initialize() throws IOException {
        game = new Writing();
        shuffleQuestions();
        nextQuestion();
        startTimer();
        currentQuestion = 0;
    }


    public void nextQuestion() throws IOException {
        if (newQuestions.size() -1 == currentQuestion){
            endGame(game);
            System.out.println("Game ended");
            return;
        }
        else {
            /* if (currentQuestion < newQuestions.size()) {
                imageView.setImage(newQuestions.get(currentQuestion).getImage());
                System.out.println("image set");
            } */
            imageView.setImage(newQuestions.get(currentQuestion).getImage()); System.out.println("image set - index" + currentQuestion);
        }

    }

    public void shuffleQuestions(){
        Collections.shuffle(newQuestions);
    }

    public void doneClicked() throws IOException {
        checkAnswer(guessField.getText());
        guessField.clear();
        nextQuestion();
    }

    public void checkAnswer(String input){
        if(newQuestions.get(currentQuestion).getName().equals(input)){
            game.setCurrentscore(game.getCurrentscore() + 1);
            System.out.println("+1 score");
        }
        else{
            setSecondsLeft(getSecondsLeft() - 10, game);
        }
        currentQuestion++;
    }

    @Override
    public void startTimer() {
        setSecondsLeft(60, game);
        getTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setSecondsLeft(getSecondsLeft()-1, game);
                Platform.runLater(() ->  timerLabel.setText(getSecondsLeft().toString()));
                if(getSecondsLeft() <= 0){
                    stopTimer();
                    try {
                        endGame(game);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 1000);
    }
}
