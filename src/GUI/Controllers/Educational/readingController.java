package GUI.Controllers.Educational;

import BACKEND.Education.ImageWithName;
import BACKEND.Education.Reading;
import GUI.Controllers.Utility.ConvertClass;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class readingController extends GameController {
    public ImageView rImage1;
    public ImageView rImage2;
    public ImageView rImage3;
    public ImageView rImage4;
    public Label readingWord;

    private ArrayList<ImageView> imageViews = new ArrayList<>();
    public Reading game = new Reading();

    public void initialize() {
        putImageViewsInArray();
        super.init(game);
    }

    public void nextQuestion() {
        game.nextQuestion();
        print4RandomTemps(game.getTemps());
        setCorrectAnswer();
    }

    private void print4RandomTemps(ArrayList<ImageWithName> randomImages) {
        for (int i = 0; i < 4; i++) {
            imageViews.get(i).setImage(randomImages.get(i).getImage());
        }
    }

    private void setCorrectAnswer() {
        readingWord.setText(game.getCorrectAnswer());
    }

    private void putImageViewsInArray() {
        imageViews.add(rImage1);
        imageViews.add(rImage2);
        imageViews.add(rImage3);
        imageViews.add(rImage4);
    }

    public void buttonClicked(MouseEvent mouseEvent) {
        Integer integer = ConvertClass.convertToInt(mouseEvent.getPickResult().getIntersectedNode().getAccessibleText());

        if (integer != null) game.checkAnswer(game.getTemps().get(integer).getName());

        nextQuestion();
    }
}
