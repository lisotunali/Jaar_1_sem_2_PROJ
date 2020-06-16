package GUI.Controllers.Marketplace;

import BACKEND.Livestock.Animal;
import BACKEND.Marketplace.AnimalProduct;
import BACKEND.Marketplace.Bid;
import BACKEND.Marketplace.MarketplaceInformation;
import BACKEND.Marketplace.Product;
import BACKEND.fakeDatabase;
import GUI.Controllers.Utility.AlertClass;
import GUI.Controllers.Utility.ConvertClass;
import GUI.Controllers.Utility.singletonPerson;
import GUI.Main;
import GUI.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static GUI.SceneController.getPrimaryStage;

public class SellProductController {
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
            String[] alertTextTitle = {"New Product", "Product Name", "Choose a name.", "This will be displayed on the marketplace.", "Name chosen: "};
            String tempTitle = AlertClass.showText(alertTextTitle);
            String[] alertTextDesc = {"Description", "Product Description", "Choose a description.", "Describe your product in a few words.", "Description chosen: "};
            String tempDesc = AlertClass.showText(alertTextDesc);
            String[] alertTextAmount = {"0", "Product amount", "Choose an amount.", "Choose a how many you'll sell at once.", "Amount chosen: "};
            Integer tempAmount = ConvertClass.convertToInt(AlertClass.showText(alertTextAmount));
            String[] alertTextPrice = {"0", "Product price", "Choose a price.", "Choose a starting price.", "Price chosen: "};
            Integer tempPrice = ConvertClass.convertToInt(AlertClass.showText(alertTextPrice));
            if (tempTitle.isEmpty() || tempDesc.isEmpty() || tempAmount == null || tempAmount <= 0 || tempPrice == null || tempPrice < 0 || tempAmount > livestockTable.getSelectionModel().getSelectedItem().getAmount()) {
                System.out.println("Invalid field detected");
                AlertClass.showAlert(Alert.AlertType.ERROR, "One or more fields contain invalid data.");
                return;
            }
            MarketplaceInformation DefaultInformation = new MarketplaceInformation(tempTitle, tempDesc, tempPrice, tempAmount);
            fakeDatabase.addProduct(new AnimalProduct(DefaultInformation, livestockTable.getSelectionModel().getSelectedItem(), singletonPerson.getInstance()));
            System.out.println("Product added to marketplace.");
            livestockTable.refresh();
            refreshUserProducts();
        }
    }

    public void refreshUserProducts() {
        ArrayList<Product> tempProducts = new ArrayList<>();

        for (Product product : fakeDatabase.getAllProducts()) {
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

    public void removeOffer() throws Exception {
        fakeDatabase.removeProduct(productTableView.getSelectionModel().getSelectedItem());
        productTableView.getSelectionModel().getSelectedItem().cancelOffer();
        refreshUserProducts();
    }

    public void viewOffer() throws IOException {
        if (productTableView.getSelectionModel().getSelectedItem() == null) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Please select an offer.");
        }

        if (productTableView.getSelectionModel().getSelectedItem() != null) {
            Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
            System.out.println("Attempting...");

            Stage primaryStage = getPrimaryStage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("Views/Marketplace/sellproduct.fxml"));
            Parent parent = loader.load();
            ViewProductController controller = loader.getController();
            controller.initData(selectedProduct);
            primaryStage.getScene().setRoot(parent);
        }
    }

    public void prevScreen() throws IOException {
        SceneController.switchTo("Marketplace/marketplace");
    }
}
