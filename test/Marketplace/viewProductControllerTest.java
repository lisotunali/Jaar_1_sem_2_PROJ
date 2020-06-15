package Marketplace;

import BACKEND.Marketplace.Bid;
import BACKEND.Marketplace.Product;
import BACKEND.Person.Person;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.singletonPerson;
import GUI.SceneController;
import TestFXBase.TestFXTestBase;
import javafx.scene.control.TableView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class viewProductControllerTest extends TestFXTestBase {

    @Test
    public void viewOfferTest() throws IOException {
        singletonPerson.setPerson(new Person("testinst", "pass"));
        singletonPerson.getInstance().addAnimal("KIPPIES", 150);
        Product product = new Product("KIPPIES MET KORTING", "20% korting op deze mooie meiden", 100, 5, singletonPerson.getInstance());
        Bid bid1 = new Bid(150, new Person("TESTBUY", "passw"), singletonPerson.getInstance(), "Jeff Bezos", "KIPPIES MET KORTING");
        Bid bid2 = new Bid(120, new Person("Winner", "passw"), singletonPerson.getInstance(), "BillGates", "KIPPIES MET KORTING");
        product.addBid(bid1);
        product.addBid(bid2);
        fakeDatabase.addProduct(product);
        SceneController.switchTo("Marketplace/sell");
        TableView productTable = lookup("#productTableView").queryTableView();
        clickOn("#productTableView");
        clickOn("KIPPIES MET KORTING");
        Product selected = (Product) productTable.getSelectionModel().getSelectedItem();
        clickOn("#viewButton");
        TableView thisProductTable = lookup("#thisProductTableView").queryTableView();
        clickOn("#sellTable");
        TableView bidTable = lookup("#sellTable").queryTableView();
        Assertions.assertEquals(productTable.getItems().get(0), thisProductTable.getItems().get(0));
        Assertions.assertNotNull(lookup("120"));
        Assertions.assertNotNull(lookup("150"));
        Bid bidItem = (Bid) bidTable.getItems().get(0);
        Bid bidItem2 = (Bid) bidTable.getItems().get(1);
        Assertions.assertEquals(bid1.getPrice(), bidItem.getPrice());
        Assertions.assertEquals(bid1.getBuyerName(), bidItem.getBuyerName());
        Assertions.assertEquals(bid2.getPrice(), bidItem2.getPrice());
        Assertions.assertEquals(bid2.getBuyerName(), bidItem2.getBuyerName());
    }

    @Test
    public void acceptBidTestNoSelection() throws IOException {
        singletonPerson.setPerson(new Person("testinstance", "pass"));
        singletonPerson.getInstance().addAnimal("KIPPIES", 150);
        Product product = new Product("KIPPIES MET KORTING", "20% korting op deze mooie meiden", 100, 5, singletonPerson.getInstance());
        fakeDatabase.addProduct(product);
        SceneController.switchTo("Marketplace/sell");
        clickOn("#productTableView");
        clickOn("KIPPIES MET KORTING");
        clickOn("#viewButton");
        clickOn("#acceptButton");
        assertFalse(lookup("Please select a bid.").tryQuery().isEmpty(), "Alert to select a bid should be shown.");
    }

    @Test
    public void acceptBidTest() throws IOException {
        singletonPerson.setPerson(new Person("testinstance", "pass"));
        singletonPerson.getInstance().addAnimal("KIPPIES", 150);
        Product product = new Product("KIPPIES MET KORTING", "20% korting op deze mooie meiden", 100, 5, singletonPerson.getInstance());
        Bid bid1 = new Bid(150, new Person("TESTBUY", "passw"), singletonPerson.getInstance(), "Jeff Bezos", "KIPPIES MET KORTING");
        Bid bid2 = new Bid(120, new Person("Winner", "passw"), singletonPerson.getInstance(), "BillGates", "KIPPIES MET KORTING");
        product.addBid(bid1);
        product.addBid(bid2);
        fakeDatabase.addProduct(product);
        SceneController.switchTo("Marketplace/sell");
        clickOn("#productTableView");
        clickOn("KIPPIES MET KORTING");
        clickOn("#viewButton");
        clickOn("#sellTable");
        clickOn("BillGates");
        clickOn("#acceptButton");
        clickOn("Yes");

        assertNotEquals(fakeDatabase.getProduct(0).getAdvertTitle(), product.getAdvertTitle(), "Product should be removed from marketplace.");
        assertFalse(lookup("Product sold.").tryQuery().isEmpty(), "'Product sold' message should be shown.");
        clickOn("OK");
        assertFalse(lookup("Sell products").tryQuery().isEmpty(), "User should be sent back to sell page.");
        assertFalse(lookup("#offerButton").tryQuery().isEmpty(), "User should be sent back to sell page.");

    }


}
