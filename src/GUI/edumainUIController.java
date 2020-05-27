package GUI;

import javafx.event.ActionEvent;

import java.io.IOException;

public class edumainUIController {


    public void backButtonClicked(ActionEvent actionEvent) {
    }

    public void mathButtonclicked(ActionEvent actionEvent) {
    }

    public void writingButtonclicked(ActionEvent actionEvent) throws IOException {
        SceneController.switchTo("writing");
    }

    public void readingButtonclicked(ActionEvent actionEvent) throws IOException {
        SceneController.switchTo("reading");
    }

    public void highscoreButtonclicked(ActionEvent actionEvent) {
    }
}
