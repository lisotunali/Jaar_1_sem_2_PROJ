package GUI.Controllers.Account;

import BACKEND.Person.Doctor;
import BACKEND.Person.Person;
import BACKEND.Person.SpecializationType;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.AlertClass;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class registerController {
    public TextField nameInput;
    public PasswordField passwordInput;
    public PasswordField passwordConfirmInput;
    public CheckBox isDoctor;

    public void registerButtonClicked() throws IOException {
        if (nameInput.getText().isEmpty() || passwordInput.getText().isEmpty()) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Please fill in all fields");
            return;
        }

        if (!passwordInput.getText().equals(passwordConfirmInput.getText())) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Password is not equal");
            return;
        }

        for (Person person : fakeDatabase.getUserDatabase()) {
            if (person.getName().equals(nameInput.getText())) {
                AlertClass.showAlert(Alert.AlertType.ERROR, "Username already taken");
                return;
            }
        }

        Person person;

        if (isDoctor.isSelected()) {
            // TODO: Specialization from GUI?
            person = new Doctor(nameInput.getText(), passwordInput.getText(), SpecializationType.GENERAL);
        } else {
            person = new Person(nameInput.getText(), passwordInput.getText());
        }

        fakeDatabase.getUserDatabase().add(person);
        singletonPerson.setPerson(person);

        SceneController.switchTo("Main/mainUi");
    }

    public void backButtonClicked() throws IOException {
        SceneController.switchTo("Account/login");
    }
}
