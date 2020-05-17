import BACKEND.Animal;
import BACKEND.AnimalProduct;
import BACKEND.Person;
import GUI.SceneController;
import GUI.fakeDatabase;
import GUI.singletonMarketplace;
import GUI.singletonPerson;
import javafx.scene.control.TableView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SellProductControllerTest extends TestFXTestBase {

    Person testperson;
    AnimalProduct animalTestProduct;
    AnimalProduct animalTestProduct2;

/*
public TableView<Animal> livestockTable;
    public TableColumn<Animal, String> animalColumn;
    public TableColumn<Animal, Integer> amountColumn;

    public TableView<Product> productTableView;
    public TableColumn<Product, String> productTitleColumn;
    public TableColumn<Product, String> productDescColumn;
    public TableColumn<Product, Integer> initialPriceColumn;
    public TableColumn<Product, Integer> currentPriceColumn;

    public TableView<Product> thisProductTableView;
    public TableColumn<Product, String> thisTitleColumn;
    public TableColumn<Product, String> thisDescColumn;
    public TableColumn<Product, Integer> thisInitialPriceColumn;
    public TableColumn<Product, Integer> thisAmountColumn;

    public TableView<Bid> sellTable;
    public TableColumn<Bid, String> buyerColumn;
    public TableColumn<Bid, Integer> bidColumn;

    public Button backButton;
    public Button offerButton;

    public Button viewButton;
    public Button cancelButton;

 */

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
        assertEquals(singletonMarketplace.getInstance().getProductByAdvertTitle("testname").getAmount(), 100);
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
        assertNull((singletonMarketplace.getInstance().getProductByAdvertTitle("testname")));
    }

    @Test
    void removeOffer() throws IOException {
        getToCorrectScene();
        singletonPerson.getInstance().addAnimal("testanimal2", 100);
        singletonMarketplace.getInstance().addProduct(new AnimalProduct("testtitleblablabla", "testdesc", 100, 100, singletonPerson.getInstance().getAnimal("testanimal2"), singletonPerson.getInstance()));
        SceneController.switchTo("sell");
        sleep(100);
        clickOn("#productTableView");
        clickOn("testtitleblablabla");
        clickOn("#cancelButton");
        assertNull(singletonMarketplace.getInstance().getProductByAdvertTitle("testtitleblablabla"));
        assertEquals((singletonPerson.getInstance().getAnimal("testanimal2").getAmount()), 100);

    }

    @Test
    void viewOffer() throws IOException {
        getToCorrectScene();
        singletonPerson.getInstance().addAnimal("testanimal2", 100);
        singletonMarketplace.getInstance().addProduct(new AnimalProduct("testtitle", "testdesc", 100, 100, singletonPerson.getInstance().getAnimal("testanimal2"), singletonPerson.getInstance()));
        SceneController.switchTo("sell");
        clickOn("#productTableView");
        clickOn("testtitle");
        sleep(100);
        clickOn("#viewButton");
        sleep(100);
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