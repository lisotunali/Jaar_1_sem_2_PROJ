import GUI.singletonPerson;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterControllerTest  extends TestFXTestBase {

    @Test //Testing whether the user input is reflected correctly in the text fields.

    public void credentialsInput(){
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
        write(password);

        PasswordField passwordConfirmField = lookup(passwordConfirmFieldID).query();
        PasswordField passwordField = lookup(passwordFieldID).query();
        TextField textField = lookup(passwordFieldID).query();

        assertEquals(passwordConfirmField.getText(), passwordTypo, "Password confirm field should equal '132'.");
        assertEquals(passwordField.getText(), password, "Password field should equal '123'.");
        assertEquals(textField.getText(), name, "Text field should equal 'tester123'.");
    }

    public void registerButtonClickedTest() {
        String nameFieldID = "#nameInput";
        String passwordFieldID = "#passwordInput";
        String passwordConfirmFieldID = "#passwordConfirmInput";
        String name = "1";
        String password = "123";
        String passwordTypo = "132";
    }


}
