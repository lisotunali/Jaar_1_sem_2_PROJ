package Livestock;

import BACKEND.Person.Person;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import TestFXBase.TestFXTestBase;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LivestockControllerTest extends TestFXTestBase {

    @Test
    void addButtonclicked() throws IOException{
        getToCorrectScene();
        String addButtonID = "#addButton";
        String amountTextFieldID = "#textFamount";
        String tableViewID = "#tableView";
        clickOn(tableViewID);
        clickOn("testanimal");
        clickOn(amountTextFieldID);
        write("100");
        clickOn(addButtonID);
        assertEquals(200, singletonPerson.getInstance().getAnimal("testanimal").getAmount());
    }

    @Test
    void removeButtonclicked() throws IOException {
        getToCorrectScene();
        String removeButtonID = "#removeButton";
        String amountTextFieldID = "#textFamount";
        String tableViewID = "#tableView";
        clickOn(tableViewID);
        clickOn("testanimal");
        clickOn(amountTextFieldID);
        write("50");
        clickOn(removeButtonID);
        assertEquals(50, singletonPerson.getInstance().getAnimal("testanimal").getAmount());
    }

    @Test
    void newButtonclicked() throws IOException {
        getToCorrectScene();
        String newButtonID = "#newButton";
        String amountTextFieldID = "#textFamount";
        String typeTextFieldID = "#textFtype";
        clickOn(amountTextFieldID);
        write("100");
        clickOn(typeTextFieldID);
        write("newanimal");
        clickOn(newButtonID);
        clickOn("Yes");
        assertNotNull(singletonPerson.getInstance().getAnimal("newanimal"));
    }

    @Test
    void deleteButtonclicked() throws IOException {
        getToCorrectScene();
        String deleteButtonID = "#deleteButton";
        String tableViewID = "#tableView";
        clickOn(tableViewID);
        clickOn("testanimal");
        clickOn(deleteButtonID);
        clickOn("Yes");
        assertNull(singletonPerson.getInstance().getAnimal("testanimal"));
    }

    void getToCorrectScene() throws IOException {
        Person testperson = new Person("testperson", "test");
        fakeDatabase.getUserDatabase().add(testperson);
        singletonPerson.setPerson(testperson);
        singletonPerson.getInstance().addAnimal("testanimal", 100);
        SceneController.switchTo("livestock");
    }
}