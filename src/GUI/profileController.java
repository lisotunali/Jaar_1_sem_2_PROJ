package GUI;

import BACKEND.Person;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class profileController {
    public TextField nameInput;
    public PasswordField passwordInput;
    public PasswordField passwordConfirmInput;
    public Label errorLabel;

    // Initialize with current user data
    public void initialize() {
        Person current = singletonPerson.getInstance();
        nameInput.setText(current.getName());
        passwordInput.setText(current.getPassword());
    }

    // Updates the user in the database with the new values
    public void onUpdateClicked() {
        String password = passwordInput.getText();

        if (password.equals(passwordConfirmInput.getText())) {
            Person currentPerson = singletonPerson.getInstance();
            currentPerson.setPassword(password);
            fakeDatabase.updatePerson(currentPerson);
            Main.showAlert(Alert.AlertType.INFORMATION, "Profile has been updated");
        } else {
            Main.showAlert(Alert.AlertType.ERROR, "Error updating profile, password is not the same");
        }
    }

    public void onBackClicked() throws IOException {
        Main.switchSceneTo("mainUi");
    }
}
