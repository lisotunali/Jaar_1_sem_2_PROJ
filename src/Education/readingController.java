package Education;

import GUI.ConvertClass;
import GUI.SceneController;
import GUI.fakeDatabase;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class readingController extends GameController {

    public ImageView rImage1;
    public ImageView rImage2;
    public ImageView rImage3;
    public ImageView rImage4;
    public Label readingWord;

    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private ArrayList<ImageWithName> temps = new ArrayList<>();
    private ArrayList<ImageWithName> questions = new ArrayList<>();

    private ImageWithName correctAnswer;

    public void initialize(){
        putImageViewsInArray();
        game.setCurrentGameType("reading");
        game.startTimer(timerLabel);
        nextQuestion();
    }

    public void nextQuestion(){
        game.setCurrentQuestion(game.getCurrentQuestion()+1);
        temps.clear();
        game.saveQuestionsLocally(fakeDatabase.getImagesDatabase());
        questions = game.getCurrentGameQuestions();
        print4RandomTemps();
        setCorrectAnswer();
    }

    public void print4RandomTemps(){
        Collections.shuffle(questions);
        for (int i = 0; i<4; i++){
            temps.add(questions.get(i));
            imageViews.get(i).setImage(temps.get(i).getImage());
        }
    }

    public void setCorrectAnswer(){
        Collections.shuffle(temps);
        correctAnswer = temps.get(0);
        readingWord.setText(temps.get(0).getName());
    }

    public void putImageViewsInArray(){
        imageViews.add(rImage1);
        imageViews.add(rImage2);
        imageViews.add(rImage3);
        imageViews.add(rImage4);
    }

    public void buttonClicked(MouseEvent mouseEvent){
        checkAnswer(imageViews.get(ConvertClass.convertToInt(mouseEvent.getPickResult().getIntersectedNode().getAccessibleText())).getImage().toString(), correctAnswer.getImage().toString());
        nextQuestion();
    }

    public void backButtonClicked() throws IOException {
        SceneController.switchTo("education");
    }

}
