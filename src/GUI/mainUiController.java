package GUI;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class mainUiController {
    public Text currentUserLabel;

    public void initialize() {
        currentUserLabel.setText("Current user: " + singletonPerson.getInstance().getName());
    }

    public void contactsButtonClicked() throws IOException {
        SceneController.switchTo("contacts");
    }

    public void profileButtonClicked() throws IOException {
        SceneController.switchTo("profile");
    }

    public void logoutButtonClicked() throws IOException {
        SceneController.switchTo("login");
    }

    public void pressMarketplace() throws IOException {
        SceneController.switchTo("marketplace");

    }
    public void LivestockButtonclicked() throws IOException {
        SceneController.switchTo("livestock");
    }

    public void medicalButtonClicked() throws IOException {
        SceneController.switchTo("medical");
    }
    public void educationalButtonClicked() throws IOException {
        SceneController.switchTo("education");
    }
}