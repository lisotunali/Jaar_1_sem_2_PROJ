import GUI.singletonPerson;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest extends TestFXTestBase {

    @Test //Testing whether the user input is reflected correctly in the text fields.

    public void credentialsInput(){
        String nameInputID = "#nameInput";
        String passwordInputID = "#passwordInput";
        String name = "bogusLoginNameabcfgh";
        String password = "bogusPasswordbyNoahgjkls";
        clickOn(nameInputID);
        write(name);
        clickOn(passwordInputID);
        write(password);
        PasswordField pwField = lookup("#passwordInput").query();
        TextField nameField = lookup("#nameInput").query();
        assertEquals(pwField.getText(), password, "User input should be correctly reflected in password field.");
        assertEquals(nameField.getText(), name, "User input should be correctly reflected in name field.");
    }

    @Test //Testing whether the system gives access to correct login credentials

    public void loginButtonClickedSuccesfulTest() throws TimeoutException {
        String nameInputID = "#nameInput";
        String passwordInputID = "#passwordInput";
        String name = "test";
        String password = "test123";
        clickOn(nameInputID);
        write(name);
        clickOn(passwordInputID);
        write(password);
        clickOn("#loginButton");
        assertEquals(name, singletonPerson.getInstance().getName(), "User not logged in with correct credentials.");

    }

    @Test // Testing whether the system denies access to non-existing login credentials.
    public void wrongLoginTest(){
        singletonPerson.setPerson(null); //reset singletonPerson
        String nameInputID = "#nameInput";
        String passwordInputID = "#passwordInput";
        String name = "hacker";
        String password = "imin";
        clickOn(nameInputID);
        write(name);
        clickOn(passwordInputID);
        write(password);
        clickOn("#loginButton");
        //verifyThat(nameInputID, hasText(name));
        PasswordField pwField = lookup("#passwordInput").query();
        TextField nameField = lookup("#nameInput").query();
        Assertions.assertFalse(lookup("Invalid credentials").tryQuery().isEmpty(), "User should receive 'invalid credentials' alert.");
        assertNull(singletonPerson.getInstance(), "User should not be logged in. singletonPerson should not be instantiated.");
    }

    @Test //Testing whether the user is sent to the register scene on clicking the register button

    public void sendToRegisterOnClick(){
        clickOn("#registerButton");
        assertFalse(lookup("#passwordConfirmInput").tryQuery().isEmpty(), "User should be sent to register screen.");
    }

}
