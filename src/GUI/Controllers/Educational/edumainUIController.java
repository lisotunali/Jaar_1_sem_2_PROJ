package GUI.Controllers.Educational;

import GUI.SceneController;

import java.io.IOException;

public class edumainUIController {


    public void backButtonClicked() throws IOException {
        SceneController.switchTo("Main/mainUi");
    }

    public void mathButtonclicked() throws IOException {
        SceneController.switchTo("Educational/math");
    }

    public void writingButtonclicked() throws IOException {
        SceneController.switchTo("Educational/writing");
    }

    public void readingButtonclicked() throws IOException {
        SceneController.switchTo("Educational/reading");
    }

    public void highscoreButtonclicked() throws IOException {
        SceneController.switchTo("Educational/highScores");
    }

}
