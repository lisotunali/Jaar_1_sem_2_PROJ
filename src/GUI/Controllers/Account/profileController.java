package GUI.Controllers.Account;

import BACKEND.Person.Person;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.AlertClass;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class profileController {
    public TextField nameInput;
    public PasswordField passwordInput;
    public PasswordField passwordConfirmInput;
    public TextArea personalData;

    // Initialize with current user data
    public void initialize() {
        Person current = singletonPerson.getInstance();
        nameInput.setText(current.getName());
        personalData.setText(current.getPersonalData());
    }

    // Updates the user in the database with the new values
    public void onUpdateClicked() {
        Person currentPerson = singletonPerson.getInstance();
        String password = passwordInput.getText();
        String passwordConfirm = passwordConfirmInput.getText();

        // If the password confirm input is set check if the passwords are equal
        if ((!password.isEmpty() || !passwordConfirm.isEmpty()) && !password.equals(passwordConfirm)) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Error updating profile, password is not the same");
            return;
        }

        // Just check if they want to update their password
        if (!password.isEmpty()) currentPerson.setPassword(password);

        currentPerson.setPersonalData(personalData.getText());
        fakeDatabase.updatePerson(currentPerson);
        AlertClass.showAlert(Alert.AlertType.INFORMATION, "Profile has been updated");
    }

    public void onBackClicked() throws IOException {
        SceneController.switchTo("mainUi");
    }
}
