import java.util.ArrayList;

public class Marketplace {

        private ArrayList<Product> hasProducts;
        private Product specifiedProduct;

        public Marketplace(ArrayList<Product> Products){
            this.hasProducts = Products;
        }

        public void addProduct(Product product){
            hasProducts.add(product);
        }

        public Product getProduct(Integer id){
            Product tempProduct;
            for (Product products : hasProducts){
                if (products.getID() == id){
                    return products;
                }
            }
            return null;
        }

        public ArrayList<Product> getAllProducts(){
            return hasProducts;
        }

        public void removeProduct(Product product){
            hasProducts.remove(product);
        }
    }
