package GUI;

import BACKEND.Person;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class registerController {
    public TextField nameInput;
    public PasswordField passwordInput;
    public PasswordField passwordConfirmInput;

    public void registerButtonClicked() throws IOException {
        for (Person person : fakeDatabase.getUserDatabase()) {
            if (person.getName().equals(nameInput.getText())) {
                // Dit moet natuurlijk in de GUI komen.
                System.out.println("User already exists");
                return;
            }
        }

        if (passwordInput.getText().equals(passwordConfirmInput.getText())) {
            Person person = new Person(nameInput.getText(), passwordInput.getText());
            fakeDatabase.getUserDatabase().add(person);
            singletonPerson.setPerson(person);

            Main.switchSceneTo("mainUi");
        } else {
            // Dit moet natuurlijk in de GUI komen.
            System.out.println("Password is not equal");
        }
    }
}
