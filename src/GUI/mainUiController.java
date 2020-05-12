package GUI;

import BACKEND.Marketplace;
import BACKEND.Person;
import BACKEND.Product;

import java.io.IOException;
import java.util.ArrayList;

public class mainUiController {

    public void pressMarketplace() throws IOException {
        ArrayList<Product> Products = new ArrayList<>();
        Marketplace marketplace = new Marketplace(Products);
        Person placeholder = new Person("user", "1234");
        Product product = new Product(25, "Test Product", "Dit is een testproduct voor de marketplace", 999, 10, placeholder);
        marketplace.addProduct(product);
        if (marketplace.getAllProducts().isEmpty()) {
            System.out.println("Marketplace is empty!");
        }
        if (!(marketplace.getAllProducts().isEmpty())) {
            System.out.println("Marketplace contains products");
        }
        Main.switchSceneTo("marketplace");
    }
}
