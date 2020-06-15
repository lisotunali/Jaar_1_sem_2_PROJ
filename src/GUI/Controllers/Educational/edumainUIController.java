package GUI.Controllers.Educational;

import GUI.SceneController;

import java.io.IOException;

public class edumainUIController {


    public void backButtonClicked() throws IOException {
        SceneController.switchTo("mainUi");
    }

    public void mathButtonclicked() throws IOException {
        SceneController.switchTo("math");
    }

    public void writingButtonclicked() throws IOException {
        SceneController.switchTo("writing");
    }

    public void readingButtonclicked() throws IOException {
        SceneController.switchTo("reading");
    }

    public void highscoreButtonclicked() throws IOException {
        SceneController.switchTo("highscores");
    }

}
