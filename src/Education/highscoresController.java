package Education;

import GUI.SceneController;
import GUI.singletonPerson;
import javafx.scene.control.Label;

import java.io.IOException;

import javafx.event.ActionEvent;

public class highscoresController {
    public Label mathHS;
    public Label readingHS;
    public Label writingHS;


    public void initialize(){
        mathHS.setText(""+ singletonPerson.getInstance().getHS("math").getHighScore());
        readingHS.setText(""+ singletonPerson.getInstance().getHS("reading").getHighScore());
        writingHS.setText(""+ singletonPerson.getInstance().getHS("writing").getHighScore());
    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        SceneController.switchTo("education");
    }
}
