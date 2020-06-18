package GUI.Controllers.Account;

import BACKEND.Person.IPerson;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.AlertClass;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
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

        for (IPerson IPerson : fakeDatabase.getUserDatabase()) {
            if (IPerson.getName().equals(nameInput.getText()) && IPerson.getPassword().equals(passwordInput.getText())) {
                singletonPerson.setPerson(IPerson);
                SceneController.switchTo("Main/mainUi");
                return;
            }
        }

        AlertClass.showAlert(Alert.AlertType.ERROR, "Invalid credentials");
    }

    public void registerButtonClicked() throws IOException {
        SceneController.switchTo("Account/register");
    }
}
