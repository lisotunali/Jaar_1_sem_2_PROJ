package GUI;
import Education.Game;
import Education.ImageWithName;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class readingController {

    public ImageView rImage1;
    public ImageView rImage2;
    public ImageView rImage3;
    public ImageView rImage4;
    public Label readingWord;

    public Button ImageButton1;
    public Button ImageButton2;
    public Button ImageButton3;
    public Button ImageButton4;

    public Game game = new Game();

    public void initialize(){
        nextQuestion();
    }

    public void nextQuestion(){
        game.initialize();
        rImage1.setImage(game.getTemp1().getImage());
        rImage2.setImage(game.getTemp2().getImage());
        rImage3.setImage(game.getTemp3().getImage());
        rImage4.setImage(game.getTemp4().getImage());
        readingWord.setText(game.getRandomTemp().getName());
    }

    public void ImageButton4Clicked() {
        game.checkAnswer(3);
        nextQuestion();
    }

    public void ImageButton2Clicked() {
        game.checkAnswer(1);
        nextQuestion();
    }

    public void ImageButton3Clicked() {
        game.checkAnswer(2);
        nextQuestion();
    }

    public void ImageButton1Clicked() {
        game.checkAnswer(0);
        nextQuestion();
    }

    public void backButtonClicked() throws IOException {
        SceneController.switchTo("education");

    }
}
