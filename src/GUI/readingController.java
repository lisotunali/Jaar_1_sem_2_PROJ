package GUI;
import Education.ImageWithName;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;

import java.io.IOException;


public class readingController {

    public ImageView rImage1;
    public ImageView rImage2;
    public ImageView rImage3;
    public ImageView rImage4;
    public Label readingWord;
    public ImageWithName ok;

    public Button ImageButton1;
    public Button ImageButton2;
    public Button ImageButton3;
    public Button ImageButton4;

    public void initialize(){

        rImage1.setImage(fakeDatabase.getRandomImageWithName().getImage());
        rImage2.setImage(fakeDatabase.getRandomImageWithName().getImage());
        rImage3.setImage(fakeDatabase.getRandomImageWithName().getImage());
        rImage4.setImage(fakeDatabase.getRandomImageWithName().getImage());
        readingWord.setText(fakeDatabase.getRandomImageWithName().getName());
    }

    public void ImageButton4Clicked() throws IOException {
        System.out.println("Button 4");
    }

    public void ImageButton2Clicked() {
        System.out.println("Button 2");
    }

    public void ImageButton3Clicked() {
        System.out.println("Button 3");
    }

    public void ImageButton1Clicked() {
        System.out.println("Button 1");
    }

    public void backButtonClicked() throws IOException {
        SceneController.switchTo("education");

    }
}
