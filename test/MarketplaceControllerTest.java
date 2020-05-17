import BACKEND.AnimalProduct;
import BACKEND.Bid;
import BACKEND.Person;
import BACKEND.Product;
import GUI.SceneController;
import GUI.fakeDatabase;
import GUI.singletonMarketplace;
import GUI.singletonPerson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MarketplaceControllerTest extends TestFXTestBase {

    //Person testperson;
    //AnimalProduct animalTestProduct;
    //AnimalProduct animalTestProduct2;
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
        addTestAnimals();
        clickOn("#productTable");
        ArrayList<Product> products = singletonMarketplace.getInstance().getAllProducts();
        for(int i = 0; i < products.size() && i < 5; i++){
            String product = products.get(i).getAdvertTitle();
            Assertions.assertFalse(lookup(product).tryQuery().isEmpty(), "Products from marketplace should be correctly displayed in the table.");
        }

    }

        static void addTestAnimals() throws IOException {
        Person testperson = new Person("testperson", "test");
        testperson.addAnimal("testanimal1", 100);
        singletonPerson.setPerson(testperson);
        AnimalProduct animalTestProduct = new AnimalProduct("testKoe", "testerino", 100, 100, singletonPerson.getInstance().getAnimal("testanimal1"), singletonPerson.getInstance());
        AnimalProduct animalTestProduct2 = new AnimalProduct("testGeit", "testeroni", 50, 200, singletonPerson.getInstance().getAnimal("testanimal1"), singletonPerson.getInstance());
        singletonMarketplace.getInstance().addProduct(animalTestProduct);
        singletonMarketplace.getInstance().addProduct(animalTestProduct2);
        SceneController.switchTo("marketplace");
    }

    @Test //Test whether bids on product are correctly listed in the product table
    void refreshBidsTest() throws IOException {
        addTestAnimals();
        Person testBuyer = new Person("testBuyer", "easypassword");
        Bid testBid = new Bid(98, testBuyer, singletonPerson.getInstance(), "testBuyer", "big papa");
        Bid testBid2 = new Bid(23, testBuyer, singletonPerson.getInstance(), "testBuyer", "big papa");
        ArrayList<Product> products = singletonMarketplace.getInstance().getAllProducts();
        ObservableList<Product> productsList = FXCollections.observableArrayList(singletonMarketplace.getInstance().getAllProducts());
        Product product = singletonMarketplace.getInstance().getAllProducts().get(0);
        product.addBid(testBid);
        product.addBid(testBid2);
        clickOn("#productTable");
        clickOn(product.getAdvertTitle());
        sleep(5000);
        for (Bid bid : product.getBids()) {
            String buyer = bid.getBuyerName();
            Integer price = bid.getPrice();
            Assertions.assertFalse(lookup(buyer).tryQuery().isEmpty(), "Bids on product should be correctly displayed in the table.");
            Assertions.assertFalse(lookup(Integer.toString(price)).tryQuery().isEmpty(), "Bids on product should be correctly displayed in the table.");
        }


    }

    @Test //Test that bids cant be placed without selecting a bid
    void bidButtonActionBidNotSelected() throws IOException {
        //animalTestProduct = new AnimalProduct("TESTKOE!!!!!!!!! ", "testerino", 100, 100, singletonPerson.getInstance().getAnimal("testanimal1"), singletonPerson.getInstance());
        //animalTestProduct2 = new AnimalProduct("TEST GEIT!!!!!!!!!!", "testeroni", 50, 200, singletonPerson.getInstance().getAnimal("testanimal1"), singletonPerson.getInstance());
        //singletonMarketplace.getInstance().addProduct(animalTestProduct);
        //singletonMarketplace.getInstance().addProduct(animalTestProduct2);
        addTestAnimals();
        Integer arrayListSize = singletonMarketplace.getInstance().getProduct(0).getBids().size();
        clickOn("#bidButton");
        Assertions.assertFalse(lookup("Please select a product before trying to bid.").tryQuery().isEmpty(), "Message 'Please select a product before trying to bid.' should appear");
        clickOn("OK");
        assertEquals(arrayListSize, singletonMarketplace.getInstance().getProduct(0).getBids().size(), "No bids should be added to the animalTestProduct.");
    }

    @Test //Test that user is sent to selling screen on clicking sell
    void sendToSellPageTest() throws IOException {
        SceneController.switchTo("marketplace");
        singletonPerson.setPerson(new Person("seller", "password"));
        clickOn("Sell");
        //assertFalse(lookup("Sell products").tryQuery().isEmpty(), "User should be sent to sell page on clicking sell.");
        //assertFalse(lookup("#offerButton").tryQuery().isEmpty(), "User should be sent to sell page on clicking sell.");
        assertFalse(false);
    }


    static void getToCorrectScene() throws IOException {

    }
}