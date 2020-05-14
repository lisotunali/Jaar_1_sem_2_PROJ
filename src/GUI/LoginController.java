package GUI;

import BACKEND.Person;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    public TextField nameInput;
    public PasswordField passwordInput;
    public Button loginButton;
    public Button registerButton;

    public void loginButtonClicked() throws IOException {
        if (nameInput.getText().isEmpty() || passwordInput.getText().isEmpty()) return;

        for (Person person : fakeDatabase.getUserDatabase()) {
            if (person.getName().equals(nameInput.getText()) && person.getPassword().equals(passwordInput.getText())) {
                singletonPerson.setPerson(person);
                Main.switchSceneTo("mainUi");
            }
        }

        Main.showAlert(Alert.AlertType.ERROR, "Invalid credentials");
    }

    public void registerButtonClicked() throws IOException {
        Main.switchSceneTo("register");
    }
}
