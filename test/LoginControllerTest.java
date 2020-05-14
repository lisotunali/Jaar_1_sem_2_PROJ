import GUI.singletonPerson;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import org.testfx.matcher.base.NodeMatchers;

import org.hamcrest.Matcher;
import org.testfx.api.FxAssert;

import java.awt.*;
import java.io.IOException;
import java.util.function.Function;

public class LoginControllerTest extends TestFXTestBase {

    @Test //Testing whether the system denies access to incorrect login credentials
    public void credentialsInput(){
        String nameInputID = "#nameInput";
        String passwordInputID = "#passwordInput";
        String name = "bogusLoginNameabcfgh";
        String password = "bullshitPasswordbyNoahgjkls";
        clickOn(nameInputID);
        write(name);
        clickOn(passwordInputID);
        write(password);
        clickOn("#loginButton");
        //verifyThat(nameInputID, hasText(name));
        PasswordField pwField = lookup("#passwordInput").query();
        TextField nameField = lookup("#nameInput").query();
        assertEquals(pwField.getText(), password);
        assertEquals(nameField.getText(), name);
    }

    @Test //Testing whether the system gives access to correct login credentials
    public void loginSuccesful() {
        String nameInputID = "#nameInput";
        String passwordInputID = "#passwordInput";
        String name = "1";
        String password = "1";
        clickOn(nameInputID);
        write(name);
        clickOn(passwordInputID);
        write(password);
        clickOn("#loginButton");
        assertEquals(name, singletonPerson.getInstance().getName());
    }

}
