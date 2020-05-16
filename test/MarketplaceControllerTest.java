import BACKEND.AnimalProduct;
import BACKEND.Person;
import BACKEND.Product;
import GUI.SceneController;
import GUI.fakeDatabase;
import GUI.singletonMarketplace;
import GUI.singletonPerson;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.util.ArrayList;

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
    @Test //Test whether products from marketplace are correctly listed in the product table
    void displayProductsTest() throws IOException {
        getToCorrectScene();
        clickOn("#productTable");
        ArrayList<Product> products = singletonMarketplace.getInstance().getAllProducts();
        for(int i = 0; i < products.size() && i < 5; i++){
            String product = products.get(i).getAdvertTitle();
            Assertions.assertFalse(lookup(product).tryQuery().isEmpty(), "Products from marketplace should be correctly displayed in the table.");
        }

    }

    @Test //Test that bids cant be placed without selecting a bid
    void bidButtonActionBidNotSelected() throws IOException {
        getToCorrectScene();
        Integer arrayListSize = animalTestProduct.getBids().size();
        clickOn("#bidButton");
        Assertions.assertFalse(lookup("Please select a product before trying to bid.").tryQuery().isEmpty(), "Message 'Please select a product before trying to bid.' should appear");
        clickOn("OK");
        assertEquals(arrayListSize, animalTestProduct.getBids().size(), "No bids should be added to the animalTestProduct.");
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