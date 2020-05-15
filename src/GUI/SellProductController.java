package GUI;

import BACKEND.Animal;
import BACKEND.AnimalProduct;
import BACKEND.Bid;
import BACKEND.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class SellProductController {
    public TableView<Animal> livestockTable;
    public TableColumn<Animal, String> animalColumn;
    public TableColumn<Animal, Integer> amountColumn;

    public TableView<Product> productTableView;
    public TableColumn<Product, String> productTitleColumn;
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


    public void initialize() {
        ObservableList<Animal> livestock = FXCollections.observableArrayList(singletonPerson.getInstance().getAnimals());
        animalColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        livestockTable.setItems(livestock);

        refreshUserProducts();
    }

    public void offerItem() {
        System.out.println("Button pressed...");
        if (livestockTable.getSelectionModel().getSelectedItem() == null) {
            AlertClass.showAlert(Alert.AlertType.WARNING, "Please select a product.");
            return;
        }

        if (livestockTable.getSelectionModel().getSelectedItem() != null) {
            System.out.println("Product selected");
            String tempTitle = AlertClass.showText("New Product", "Product Name", "Choose a name.", "This will be displayed on the marketplace.", "Name chosen: ");
            String tempDesc = AlertClass.showText("Description", "Product Description", "Choose a description.", "Describe your product in a few words.", "Description chosen: ");
            Integer tempAmount = ConvertClass.convertToInt(AlertClass.showText("0", "Product amount", "Choose an amount.", "Choose a how many you'll sell at once.", "Amount chosen: "));
            Integer tempPrice = ConvertClass.convertToInt(AlertClass.showText("0", "Product price", "Choose a price.", "Choose a starting price.", "Price chosen: "));
            if (tempTitle.isEmpty() || tempDesc.isEmpty() || tempAmount <= 0 || tempPrice < 0) {
                System.out.println("Invalid field detected");
                AlertClass.showAlert(Alert.AlertType.ERROR, "One or more fields contain invalid data.");
                return;
            }
            if (tempTitle != null && tempDesc != null && tempAmount > 0 && tempPrice >= 0) {
                singletonMarketplace.getInstance().addProduct(new AnimalProduct(tempTitle, tempDesc, tempPrice, tempAmount, livestockTable.getSelectionModel().getSelectedItem(), singletonPerson.getInstance()));
                System.out.println("Product added to marketplace.");
                refreshUserProducts();
            }
        }
    }

    public void refreshUserProducts() {
        ArrayList<Product> tempProducts = new ArrayList<>();

        for (Product product : singletonMarketplace.getInstance().getAllProducts()) {
            if (product.getPerson() == singletonPerson.getInstance()) {
                tempProducts.add(product);
                System.out.println(product.getPerson() + " " + singletonPerson.getInstance());
            }
        }

        System.out.println("ArrayList empty: " + tempProducts.isEmpty());

        ObservableList<Product> products = FXCollections.observableArrayList(tempProducts);
        productTitleColumn.setCellValueFactory(new PropertyValueFactory<>("advertTitle"));
        initialPriceColumn.setCellValueFactory(new PropertyValueFactory<>("initialPrice"));
        currentPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTableView.setItems(products);
        productTableView.getSortOrder().add(currentPriceColumn);
        productTableView.refresh();
    }

    public void getProductBids() {

    }

    public void viewOffer() throws IOException {
        if (productTableView.getSelectionModel().getSelectedItem() == null) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Please select an offer.");
        }

        if (productTableView.getSelectionModel().getSelectedItem() != null) {
            SceneController.switchTo("sellproduct");
            ObservableList<Product> selectedProduct = FXCollections.observableArrayList(productTableView.getSelectionModel().getSelectedItem());
            thisTitleColumn.setCellValueFactory(new PropertyValueFactory<>("advertTitle"));
            thisDescColumn.setCellValueFactory(new PropertyValueFactory<>("advertDescription"));
            thisInitialPriceColumn.setCellValueFactory(new PropertyValueFactory<>("initialPrice"));
            thisAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
            thisProductTableView.setItems(selectedProduct);
            thisProductTableView.getSortOrder().add(currentPriceColumn);
            thisProductTableView.refresh();

            ObservableList<Bid> bids = FXCollections.observableArrayList(productTableView.getSelectionModel().getSelectedItem().getBids());
            buyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyerName"));
            bidColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            sellTable.setItems(bids);
            sellTable.getSortOrder().add(bidColumn);
            sellTable.refresh();
        }
    }

    public void prevScreen() throws IOException {
        SceneController.switchTo("marketplace");
    }
}
