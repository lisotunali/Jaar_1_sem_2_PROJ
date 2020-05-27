package GUI;

import BACKEND.Doctor;
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
        if (singletonPerson.getInstance() instanceof Doctor) {
            SceneController.switchTo("profileDoctor");
        } else {
            SceneController.switchTo("profile");
        }
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
        if (singletonPerson.getInstance() instanceof Doctor) {
            SceneController.switchTo("medicalDoctor");
        } else {
            SceneController.switchTo("medicalPerson");
        }
    }

    public void educationalButtonClicked() throws IOException {
        SceneController.switchTo("educational");
    }
}
