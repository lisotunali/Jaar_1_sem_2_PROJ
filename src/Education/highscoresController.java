package Education;

import GUI.ConvertClass;
import GUI.SceneController;
import GUI.singletonPerson;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;

public class highscoresController {
    public Label mathHS;
    public Label readingHS;
    public Label writingHS;


    public void initialize(){
        mathHS.setText(""+singletonPerson.getInstance().getHS("math").getHighScore());
        readingHS.setText(""+singletonPerson.getInstance().getHS("reading").getHighScore());
        writingHS.setText(""+singletonPerson.getInstance().getHS("writing").getHighScore());
    }

    public void globalButtonClicked() throws IOException {
        SceneController.switchTo("globalHS");
    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        SceneController.switchTo("education");
    }
}
