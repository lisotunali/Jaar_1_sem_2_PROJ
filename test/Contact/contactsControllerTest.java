package Contact;

import BACKEND.Contact.Contact;
import BACKEND.Person.Person;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import TestFXBase.TestFXTestBase;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class contactsControllerTest extends TestFXTestBase {

    String contactSearchID = "#contactsSearchButton";
    String contactsAddID = "#contactsAddButton";
    String contactsRemoveID = "#contactsRemoveButton";
    String contactsEditID = "#contactsEditButton";
    String nameTextFieldID = "#nameTextField";
    String phoneTextFieldID = "#phoneTextField";
    String emailTextFieldID = "#emailTextField";
    String addressTextFieldID = "#addressTextField";


    @Test
    void nameClicked() throws IOException {
        getToCorrectScene();
        TextField nameField = lookup(nameTextFieldID).query();
        TextField phoneField = lookup(phoneTextFieldID).query();
        TextField emailField = lookup(emailTextFieldID).query();
        TextField addressField = lookup(addressTextFieldID).query();
        clickOn("#tableView");
        clickOn("testcontact");
        assertEquals(nameField.getText(), "testcontact");
        assertEquals(phoneField.getText(), "0612345676");
        assertEquals(emailField.getText(), "nonsense@nonsensemail.com");
        assertEquals(addressField.getText(), "doesnotexiststreet");
    }

    @Test
    void contactsSearchButtonClicked() throws IOException {
        getToCorrectScene();
        clickOn("#addressGridPane");
        clickOn(nameTextFieldID);
        write("testcontact");
        sleep(100);
        clickOn(contactSearchID);
        sleep(100);
        TextField phoneField = lookup(phoneTextFieldID).query();
        assertEquals(phoneField.getText(), "0612345676");
        assertEquals(singletonPerson.getInstance().getContact("testcontact").getPhoneNumber(), "0612345676");
    }

    @Test
    void contactsAddButtonclicked() throws IOException {
        getToCorrectScene();
        clickOn(nameTextFieldID);
        write("addingtest");
        clickOn(phoneTextFieldID);
        write("123");
        clickOn(contactsAddID);
        assertNotNull(singletonPerson.getInstance().getContact("addingtest"));
    }

    @Test
    void contactsRemoveButtonClicked() throws IOException {
        getToCorrectScene();
        clickOn("#tableView");
        clickOn("testcontact");
        clickOn(contactsRemoveID);
        assertNull(singletonPerson.getInstance().getContact("testcontact"));
    }

    @Test
    void contactsEditButtonClicked() throws IOException {
        getToCorrectScene();
        TextField phoneField = lookup(phoneTextFieldID).query();
        clickOn("#tableView");
        clickOn("testcontact");
        clickOn(phoneTextFieldID);
        while (!phoneField.getText().isEmpty()){
            push(KeyCode.BACK_SPACE);
        }
        write("veranderd!");
        clickOn(contactsEditID);
        assertEquals(phoneField.getText(), "veranderd!");
        assertEquals(singletonPerson.getInstance().getContact("testcontact").getPhoneNumber(), "veranderd!");

    }

    void getToCorrectScene() throws IOException {
        Person testperson = new Person("testperson", "test");
        fakeDatabase.getUserDatabase().add(testperson);
        singletonPerson.setPerson(testperson);
        Contact contact = new Contact("testcontact", "0612345676", "nonsense@nonsensemail.com", "doesnotexiststreet");
        singletonPerson.getInstance().addContact(contact);
        SceneController.switchTo("Contacts/contacts");
    }
}