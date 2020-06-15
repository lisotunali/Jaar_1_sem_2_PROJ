package Account;

import BACKEND.Person.Person;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import TestFXBase.TestFXTestBase;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterControllerTest  extends TestFXTestBase {

    @Test //Testing whether the user input is reflected correctly in the text fields.

    public void credentialsInput() throws IOException {
        SceneController.switchTo("register");

        String nameFieldID = "#nameInput";
        String passwordFieldID = "#passwordInput";
        String passwordConfirmFieldID = "#passwordConfirmInput";
        String name = "tester123";
        String password = "123";
        String passwordTypo = "132";

        clickOn(nameFieldID);
        write(name);
        clickOn(passwordFieldID);
        write(password);
        clickOn(passwordConfirmFieldID);
        write(passwordTypo);

        PasswordField passwordConfirmField = lookup(passwordConfirmFieldID).query();
        PasswordField passwordField = lookup(passwordFieldID).query();
        TextField textField = lookup(nameFieldID).query();

        assertEquals(passwordTypo, passwordConfirmField.getText(), "Password confirm field should equal '132'.");
        assertEquals(password, passwordField.getText(), "Password field should equal '123'.");
        assertEquals(name,textField.getText(), "Text field should equal 'tester123'.");
    }

    @Test //Testing whether the system doesnt allow registering with an already existing username
    public void registerExistingUsernameAlert() throws IOException {
        SceneController.switchTo("register");
        String nameFieldID = "#nameInput";
        String passwordFieldID = "#passwordInput";
        String passwordConfirmFieldID = "#passwordConfirmInput";
        String registerButtonID = "#registerButton";
        String name = "1";
        String password = "1";
        clickOn(nameFieldID);
        write(name);
        clickOn(passwordFieldID);
        write(password);
        clickOn(passwordConfirmFieldID);
        write(password);
        clickOn(registerButtonID);
        assertFalse(lookup("Username already taken").tryQuery().isEmpty(), "Message 'Username already taken' should appear.");
    }

    @Test //Testing whether the system doesnt allow registering with a typo in the password confirm field

    public void registerPasswordTypoAlert() throws IOException {
        SceneController.switchTo("register");
        String nameFieldID = "#nameInput";
        String passwordFieldID = "#passwordInput";
        String passwordConfirmFieldID = "#passwordConfirmInput";
        String registerButtonID = "#registerButton";
        String name = "newuser";
        String password = "123";
        String passwordTypo = "321";
        clickOn(nameFieldID);
        write(name);
        clickOn(passwordFieldID);
        write(password);
        clickOn(passwordConfirmFieldID);
        write(passwordTypo);
        clickOn(registerButtonID);

        assertFalse(lookup("Password is not equal").tryQuery().isEmpty(), "Message 'Password is not equal' should appear.");
    }

    @Test
    public void registerSuccessful() throws IOException {
        SceneController.switchTo("register");
        String nameFieldID = "#nameInput";
        String passwordFieldID = "#passwordInput";
        String passwordConfirmFieldID = "#passwordConfirmInput";
        String registerButtonID = "#registerButton";
        String name = "validUser";
        String password = "123";
        clickOn(nameFieldID);
        write(name);
        clickOn(passwordFieldID);
        write(password);
        clickOn(passwordConfirmFieldID);
        write(password);
        clickOn(registerButtonID);
        boolean flag = false;
        for (Person person : fakeDatabase.getUserDatabase()) {
            if (person.getName().equals(name)) {
                flag = true;
                if (!person.getPassword().equals(password)){
                    flag = false;
                }
            }
        }

        assertTrue(flag, "User credentials not correctly added to database.");
        assertEquals(name, singletonPerson.getInstance().getName(), "Username should be registered as" + name);
        assertFalse(lookup("Main menu").tryQuery().isEmpty(), "Main menu should appear.");
        assertFalse(lookup("Logout").tryQuery().isEmpty(), "Main menu elements should appear.");

    }


}
