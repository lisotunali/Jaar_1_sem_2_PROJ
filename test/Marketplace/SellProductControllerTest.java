package Marketplace;

import BACKEND.Marketplace.AnimalProduct;
import BACKEND.Person.Person;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import TestFXBase.TestFXTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SellProductControllerTest extends TestFXTestBase {

    Person testperson;
    AnimalProduct animalTestProduct;
    AnimalProduct animalTestProduct2;

    @Test
    void offerItemWithNoItemSelected() throws IOException {
        getToCorrectScene();
        clickOn("#livestockTable");
        clickOn("#offerButton");
        Assertions.assertFalse(lookup("Please select a product.").tryQuery().isEmpty(), "Message 'Please select a product.' should appear");
        clickOn("OK");
    }

    @Test
    void offerItemValidFieldInput () throws IOException {
        getToCorrectScene();
        clickOn("#livestockTable");
        clickOn("testanimal1");
        clickOn("#offerButton");
        write("testname");
        clickOn("OK");
        write("testdescription");
        clickOn("OK");
        write("100");
        clickOn("OK");
        write("100");
        clickOn("OK");
        assertEquals(testperson.getAnimal("testanimal1").getAmount(), 0);
        assertEquals(fakeDatabase.getProductByAdvertTitle("testname").getAmount(), 100);
    }

    @Test
    void offerItemInvalidFieldInput () throws IOException {
        getToCorrectScene();
        clickOn("#livestockTable");
        clickOn("testanimal1");
        clickOn("#offerButton");
        write("testname");
        clickOn("OK");
        write("testdescription");
        clickOn("OK");
        write("1000");
        clickOn("OK");
        write("100");
        clickOn("OK");
        assertEquals(testperson.getAnimal("testanimal1").getAmount(), 100);
        assertNull((fakeDatabase.getProductByAdvertTitle("testname")));
    }

    @Test
    void removeOffer() throws IOException {
        getToCorrectScene();
        singletonPerson.getInstance().addAnimal("testanimal2", 100);
        fakeDatabase.addProduct(new AnimalProduct("testtitleblablabla", "testdesc", 100, 100, singletonPerson.getInstance().getAnimal("testanimal2"), singletonPerson.getInstance()));
        //sleep(100);
        SceneController.switchTo("sell");
        //sleep(100);
        clickOn("#productTableView");
        //sleep(100);
        clickOn("testtitleblablabla");
        clickOn("#cancelButton");
        assertNull(fakeDatabase.getProductByAdvertTitle("testtitleblablabla"));
        assertEquals((singletonPerson.getInstance().getAnimal("testanimal2").getAmount()), 100);

    }

    @Test
    void viewOffer() throws IOException {
        getToCorrectScene();
        singletonPerson.getInstance().addAnimal("testanimal2", 100);
        fakeDatabase.addProduct(new AnimalProduct("testtitle", "testdesc", 100, 100, singletonPerson.getInstance().getAnimal("testanimal2"), singletonPerson.getInstance()));
        SceneController.switchTo("sell");
        clickOn("#productTableView");
        clickOn("testtitle");
        clickOn("#viewButton");
        assertFalse(lookup("Your offers").tryQuery().isEmpty());

    }

    void getToCorrectScene() throws IOException {
        testperson = new Person("testperson", "test");
        fakeDatabase.getUserDatabase().add(testperson);
        testperson.addAnimal("testanimal1", 100);
        singletonPerson.setPerson(testperson);
        SceneController.switchTo("sell");
    }
}