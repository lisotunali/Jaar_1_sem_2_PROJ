package Education;

import GUI.SceneController;
import javafx.event.ActionEvent;

import java.io.IOException;

public class edumainUIController {


    public void backButtonClicked() throws IOException {
        SceneController.switchTo("mainUi");
    }

    public void mathButtonclicked() {
    }

    public void writingButtonclicked() throws IOException {
        SceneController.switchTo("writing");
    }

    public void readingButtonclicked() throws IOException {
        SceneController.switchTo("reading");
    }

    public void highscoreButtonclicked() {
    }

}