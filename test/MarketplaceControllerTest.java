import BACKEND.AnimalProduct;
import BACKEND.Person;
import BACKEND.Product;
import GUI.SceneController;
import GUI.fakeDatabase;
import GUI.singletonMarketplace;
import GUI.singletonPerson;
import javafx.scene.control.TableView;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarketplaceControllerTest extends TestFXTestBase {

    Person testperson;
    AnimalProduct animalTestProduct;
    AnimalProduct animalTestProduct2;
/*
    public Button backButton;
    public Button bidButton;
    public Button sellPageButton;
    public TextField bidInput;

    public TableView<Product> productTable;
    public TableColumn<Product, String> titleColumn;
    public TableColumn<Product, Integer> amountColumn;
    public TableColumn<Product, String> sellerColumn;
    public TableColumn<Product, Integer> priceColumn;

    public TableView<Bid> bidTable;
    public TableColumn<Bid, String> buyerColumn;
    public TableColumn<Bid, Integer> bidColumn;

 */
    @Test
    void bidButtonAction() throws IOException {
        getToCorrectScene();
        Integer arrayListSize = animalTestProduct.getBids().size();
        TableView<Product> tableView = lookup("#productTable").query();
        clickOn("#bidButton");
        clickOn("OK");
        assertEquals(arrayListSize, animalTestProduct.getBids().size());
        sleep(2000);
    }

    void getToCorrectScene() throws IOException {
        testperson = new Person("testperson", "test");
        fakeDatabase.getUserDatabase().add(testperson);
        testperson.addAnimal("testanimal1", 100);
        singletonPerson.setPerson(testperson);
        animalTestProduct = new AnimalProduct("test", "testerino", 100, 100, singletonPerson.getInstance().getAnimal("testanimal1"), singletonPerson.getInstance());
        animalTestProduct2 = new AnimalProduct("test2", "testeroni", 50, 200, singletonPerson.getInstance().getAnimal("testanimal1"), singletonPerson.getInstance());
        singletonMarketplace.getInstance().addProduct(animalTestProduct);
        singletonMarketplace.getInstance().addProduct(animalTestProduct2);
        SceneController.switchTo("marketplace");
    }
}