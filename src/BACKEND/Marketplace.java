package BACKEND;

import java.util.ArrayList;

public class Marketplace {

    private ArrayList<Product> hasProducts = new ArrayList<>();

    private Product specifiedProduct;

    public void addProduct(Product product) {
        hasProducts.add(product);
    }

    public Product getProduct(Integer id) {
        for (Product products : hasProducts) {
            if (products.getID().equals(id)) {
                return products;
            }
        }
        return null;
    }

    public ArrayList<Product> getAllProducts() {
        return hasProducts;
    }

    public void removeProduct(Product product) {
        hasProducts.remove(product);
    }
}
