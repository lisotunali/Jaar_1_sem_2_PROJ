package Account;

import BACKEND.Person.Person;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import TestFXBase.TestFXTestBase;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class profileControllerTest extends TestFXTestBase {

    @Test
    void onUpdateClickedCorrect() throws IOException {
        Person testperson = new Person("testprofile", "test");
        fakeDatabase.getUserDatabase().add(testperson);
        singletonPerson.setPerson(testperson);
        SceneController.switchTo("Profile/profile");
        sleep(100);
        String passwordID = "#passwordInput";
        String passwordConfirmID = "#passwordConfirmInput";
        String updateButtonID = "#loginButton";
        clickOn(passwordID);
        write("change");
        clickOn(passwordConfirmID);
        write("change");
        clickOn(updateButtonID);
        clickOn("OK");
        assertEquals(singletonPerson.getInstance().getPassword(), "change");

    }


    @Test
    void onUpdateClickedWrong() throws IOException {
        Person testperson = new Person("testprofile", "test");
        fakeDatabase.getUserDatabase().add(testperson);
        singletonPerson.setPerson(testperson);
        SceneController.switchTo("Profile/profile");
        sleep(100);
        String passwordID = "#passwordInput";
        String passwordConfirmID = "#passwordConfirmInput";
        String updateButtonID = "#loginButton";
        clickOn(passwordID);
        write("change");
        clickOn(passwordConfirmID);
        write("wrong");
        clickOn(updateButtonID);
        clickOn("OK");
        assertEquals(singletonPerson.getInstance().getPassword(), "test");
    }
}