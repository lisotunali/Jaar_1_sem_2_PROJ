package GUI;

import BACKEND.Person;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
                // TODO: Should this pass the current user to the main menu?
                Stage primaryStage = Main.getPrimaryStage();
                Parent root = FXMLLoader.load(getClass().getResource("mainUi.fxml"));
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }
        }

        // TODO: Add error message.
    }

    public void registerButtonClicked() throws IOException {
        Stage primaryStage = Main.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
