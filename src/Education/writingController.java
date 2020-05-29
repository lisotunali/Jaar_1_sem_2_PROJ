package Education;

import GUI.SceneController;
import GUI.fakeDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class writingController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField guessField;

    @FXML
    private Button guessButton;

    private ArrayList<ImageWithName> newQuestions = new ArrayList<ImageWithName>(fakeDatabase.getImagesDatabase());
    private Writing writing;
    private Integer currentQuestion = 0;

    public void initialize() throws IOException {
        writing = new Writing();
        shuffleQuestions();
        nextQuestion();
        currentQuestion = 0;
    }


    public void nextQuestion() throws IOException {
        if (newQuestions.isEmpty()){
            endAndSaveGame();
            return;
        }
        else {
            if (currentQuestion < newQuestions.size()) {
                imageView.setImage(newQuestions.get(currentQuestion).getImage());
            }
            while (!newQuestions.isEmpty() && currentQuestion >= newQuestions.size()) {
                currentQuestion--;
            }
        }

    }

    public void endAndSaveGame() throws IOException {
        SceneController.switchTo("edu_UI");
    }

    public void shuffleQuestions(){
        Collections.shuffle(newQuestions);
        newQuestions.remove(newQuestions.size()-1);
        newQuestions.remove(newQuestions.size()-1);
        newQuestions.remove(newQuestions.size()-1);
        newQuestions.remove(newQuestions.size()-1);
    }

    public void doneClicked() throws IOException {
        checkAnswer(guessField.getText());
        nextQuestion();
    }

    public void checkAnswer(String input){
        if(newQuestions.get(currentQuestion).getName().equals(input)){
            writing.setCurrentscore(writing.getCurrentscore()+1);
            System.out.println("+1 score");
        }
        else{
            ImageWithName addAtEnd = newQuestions.get(currentQuestion);
            newQuestions.add(addAtEnd);
        }
        newQuestions.remove(currentQuestion);
        currentQuestion++;
    }

}
