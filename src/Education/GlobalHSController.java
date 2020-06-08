package Education;

import GUI.SceneController;
import GUI.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class GlobalHSController {
    public TableView <Highscores>readingTableView;
    public TableColumn <Highscores,Integer>readingHS;
    public TableColumn <Highscores, String>readingName;

    public void initialize(){
        ObservableList highscores = FXCollections.observableArrayList(fakeDatabase.getReadingHSList());
        readingName.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        readingHS.setCellValueFactory(new PropertyValueFactory<>("highScore"));
        readingTableView.setItems(highscores);
    }



    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        SceneController.switchTo("education");
    }

    public void localButtonClicked(ActionEvent actionEvent) throws IOException {
        SceneController.switchTo("highscores");
    }
}
