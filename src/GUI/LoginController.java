package GUI;

import BACKEND.Person;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    public TextField nameInput;
    public PasswordField passwordInput;
    public Button loginButton;
    public Button registerButton;

    public void loginButtonClicked() throws IOException {
        ArrayList<Person> users = fakeDatabase.getUserDatabase();

        for (Person person : fakeDatabase.getUserDatabase()) {
            if (person.getName().equals(nameInput.getText()) && person.getPassword().equals(passwordInput.getText())) {
                singletonPerson.setPerson(person);
                Main.switchSceneTo("mainUi");
            }
        }

        // TODO: Add error message.
    }

    public void registerButtonClicked() throws IOException {
        Main.switchSceneTo("register");
    }
}
