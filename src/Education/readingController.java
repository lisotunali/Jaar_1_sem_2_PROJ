package Education;
import GUI.SceneController;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;


public class readingController extends GameController {

    public ImageView rImage1;
    public ImageView rImage2;
    public ImageView rImage3;
    public ImageView rImage4;
    public Label readingWord;
    public Label timerLabel;

    public Button button1;

    private ArrayList<ImageWithName> temps = new ArrayList<>();

    public Reading game = new Reading();

    public void initialize(){
        game.initialize();
        startTimer();
        nextQuestion();
    }

    public void nextQuestion(){
        //clears out the temporary ImageWithName's that were in temps from the last round
        temps.clear();

        // reset the game questions so that all questions can appear again (ImageWithName objects)
        game.saveQuestionsLocally();

        // put 4 random images on the screen...
        createRandomImageOnScreen(rImage1);
        createRandomImageOnScreen(rImage2);
        createRandomImageOnScreen(rImage3);
        createRandomImageOnScreen(rImage4);

        // pick one of those as the correct answer
        game.setRandomIndex(game.getRan().nextInt(4));
        readingWord.setText(temps.get(game.getRandomIndex()).getName());
    }

    public void createRandomImageOnScreen(ImageView imageView){
        // set the randomIndex to a new random value
        game.setRandomIndex(game.getRan().nextInt(game.getCurrentGameQuestions().size()));

        // set the image in the actual on screen imageView
        imageView.setImage(game.getCurrentGameQuestions().get(game.getRandomIndex()).getImage());

        // add the ImageWithName to the temps arrayList so that we can pick one of 4 as the correct answer later
        temps.add(game.getCurrentGameQuestions().get(game.getRandomIndex()));

        //remove it from the current set of available ImageWithNames so that we dont get duplicates (will later be reset by saveQuestionsLocally())
        game.getCurrentGameQuestions().remove(game.getRandomIndex());
    }

    public void buttonClicked(MouseEvent mouseEvent){
        game.checkAnswer(mouseEvent.getPickResult().getIntersectedNode().getAccessibleText());
        nextQuestion();
    }

    public void backButtonClicked() throws IOException {
        SceneController.switchTo("education");
    }

    @Override
    public void startTimer() {
        getTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setSecondsLeft(getSecondsLeft()-1, game);
                Platform.runLater(() ->  timerLabel.setText(getSecondsLeft().toString()));
            }
        }, 0, 1000);
    }
}
