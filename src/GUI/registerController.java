package GUI;

import BACKEND.Person;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class registerController {
    public TextField nameInput;
    public PasswordField passwordInput;
    public PasswordField passwordConfirmInput;

    public void registerButtonClicked() {
        for (Person person : fakeDatabase.getUserDatabase()) {
            if (person.getName().equals(nameInput.getText())) {
                // Dit moet natuurlijk in de GUI komen.
                System.out.println("User already exists");
                return;
            }
        }

        if (passwordInput.getText().equals(passwordConfirmInput.getText())) {
            fakeDatabase.getUserDatabase().add(new Person(nameInput.getText(), passwordInput.getText()));
        } else {
            // Dit moet natuurlijk in de GUI komen.
            System.out.println("Password is not equal");
        }
    }
}
