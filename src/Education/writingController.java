package Education;

import GUI.fakeDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class writingController {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField guessField;

    @FXML
    private Button guessButton;

    private ArrayList<ImageWithName> newQuestions = new ArrayList<ImageWithName>();

    public void initialize(){
        initializeQuestions();
        nextQuestion();

    }

    public void initializeQuestions(){
       newQuestions = fakeDatabase.getImagesDatabase();
    }

    public void nextQuestion(){
        Collections.shuffle(newQuestions);
        Random random = new Random();
        Integer randomValue = random.nextInt(newQuestions.size());
        imageView.setImage(newQuestions.get(randomValue).getImage());
        newQuestions.remove(randomValue);

    }

}
