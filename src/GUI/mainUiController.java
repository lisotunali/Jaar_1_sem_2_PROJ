package GUI;

import javafx.scene.text.Text;

import java.io.IOException;

import java.io.IOException;

public class mainUiController {
    public Text currentUserLabel;

    public void initialize() {
        currentUserLabel.setText("Current user: " + singletonPerson.getInstance().getName());
    }

    public void contactsButtonClicked() throws IOException {
        Main.switchSceneTo("contacts");
    }

    public void profileButtonClicked() throws IOException {
        Main.switchSceneTo("profile");
    }

    public void logoutButtonClicked() throws IOException {
        Main.switchSceneTo("login");
    }

    public void pressMarketplace() throws IOException {
        Main.switchSceneTo("marketplace");

    }
}
