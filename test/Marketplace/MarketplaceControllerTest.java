package Marketplace;

import BACKEND.Marketplace.AnimalProduct;
import BACKEND.Marketplace.Bid;
import BACKEND.Marketplace.MarketplaceInformation;
import BACKEND.Marketplace.Product;
import BACKEND.Person.IPerson;
import BACKEND.Person.Person;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import TestFXBase.TestFXTestBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class  MarketplaceControllerTest extends TestFXTestBase {

    @Test //Test whether products from marketplace are correctly listed in the product table
    void displayProductsTest() throws IOException {
        SceneController.switchTo("Marketplace/marketplace");
        clickOn("#productTable");
        ArrayList<Product> products = fakeDatabase.getAllProducts();
        for(int i = 0; i < products.size() && i < 5; i++){
            String product = products.get(i).getAdvertTitle();
            assertFalse(lookup(product).tryQuery().isEmpty(), "Products from marketplace should be correctly displayed in the table.");
        }

    }

    static void addTestAnimals() throws IOException {
        IPerson testperson = new Person("testperson", "test");
        testperson.addAnimal("testanimal1", 100);
        singletonPerson.setPerson(testperson);
        MarketplaceInformation definfo1 = new MarketplaceInformation("testKoe", "testerino", 100, 100);
        AnimalProduct animalTestProduct = new AnimalProduct(definfo1, singletonPerson.getInstance().getAnimal("testanimal1"), singletonPerson.getInstance());
        MarketplaceInformation definfo2 = new MarketplaceInformation("testGeit", "testeroni", 50, 200);
        AnimalProduct animalTestProduct2 = new AnimalProduct(definfo2, singletonPerson.getInstance().getAnimal("testanimal1"), singletonPerson.getInstance());
        fakeDatabase.addProduct(animalTestProduct);
        fakeDatabase.addProduct(animalTestProduct2);
        SceneController.switchTo("Marketplace/marketplace");
    }

    @Test //Test whether bids on product are correctly listed in the product table
    void refreshBidsTest() throws IOException {
        addTestAnimals();
        IPerson testBuyer = new Person("testBuyer", "easypassword");
        Bid testBid = new Bid(98, testBuyer, singletonPerson.getInstance(), "testBuyer", "big papa");
        Bid testBid2 = new Bid(23, testBuyer, singletonPerson.getInstance(), "testBuyer", "big papa");
        ArrayList<Product> products = fakeDatabase.getAllProducts();
        ObservableList<Product> productsList = FXCollections.observableArrayList(fakeDatabase.getAllProducts());
        Product product = fakeDatabase.getAllProducts().get(0);
        product.addBid(testBid);
        product.addBid(testBid2);
        clickOn("#productTable");
        clickOn(product.getAdvertTitle());
        for (Bid bid : product.getBids()) {
            String buyer = bid.getBuyerName();
            Integer price = bid.getPrice();
            Assertions.assertFalse(lookup(buyer).tryQuery().isEmpty(), "Bids on product should be correctly displayed in the table.");
            Assertions.assertFalse(lookup(Integer.toString(price)).tryQuery().isEmpty(), "Bids on product should be correctly displayed in the table.");
        }


    }

    @Test //Test that bids cant be placed without selecting a bid
    void bidButtonActionBidNotSelected() throws IOException {
        addTestAnimals();
        Integer arrayListSize = fakeDatabase.getProduct(0).getBids().size();
        clickOn("#bidButton");
        Assertions.assertFalse(lookup("Please select a product before trying to bid.").tryQuery().isEmpty(), "Message 'Please select a product before trying to bid.' should appear");
        clickOn("OK");
        assertEquals(arrayListSize, fakeDatabase.getProduct(0).getBids().size(), "No bids should be added to the animalTestProduct.");
    }

    @Test //Test that user is sent to selling screen on clicking sell
    void sendToSellPageTest() throws IOException {
        SceneController.switchTo("Marketplace/marketplace");
        singletonPerson.setPerson(new Person("seller", "password"));
        sleep(200);
        clickOn("#sellPageButton");
        assertFalse(lookup("Sell products").tryQuery().isEmpty(), "User should be sent to sell page on clicking sell.");
        assertFalse(lookup("#offerButton").tryQuery().isEmpty(), "User should be sent to sell page on clicking sell.");
    }


    static void getToCorrectScene() throws IOException {

    }
}