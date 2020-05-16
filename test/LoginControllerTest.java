import GUI.singletonPerson;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;
/*
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import org.testfx.matcher.base.NodeMatchers;
import org.junit.jupiter.api.*;
import org.hamcrest.Matcher;
import org.testfx.api.FxAssert;

import java.awt.*;
import java.io.IOException;
import java.util.function.Function;
 */

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
        //verifyThat(nameInputID, hasText(name));
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
        assertNotNull(lookup("Invalid credentials").query(), "User should receive 'invalid credentials' alert.");
        assertNull(singletonPerson.getInstance(), "User should not be logged in. singletonPerson should not be instantiated.");
    }

    @Test //Testing whether the user is sent to the register scene on clicking the register button

    public void sendToRegisterOnClick(){
        clickOn("#registerButton");
        assertNotNull(lookup("#passwordConfirmInput").query(), "User should be sent to register screen.");
    }

}
