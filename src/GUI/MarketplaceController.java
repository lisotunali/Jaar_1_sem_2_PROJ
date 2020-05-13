package GUI;

import BACKEND.Bid;
import BACKEND.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MarketplaceController {
    public Button backButton;
    public Button bidButton;
    public TextField bidInput;

    public TableView<Product> productTable;
    public TableColumn<Product, String> titleColumn;
    public TableColumn<Product, Integer> amountColumn;
    public TableColumn<Product, Integer> priceColumn;


    public void initialize() {
        ObservableList<Product> products = (ObservableList<Product>) FXCollections.observableArrayList(singletonMarketplace.getInstance().getAllProducts());
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("advertTitle"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTable.setItems(products);
    }

    public void bidButtonAction() throws IOException {
        System.out.println("Trying to place bid...");
        //Product product = productTable.getSelectionModel().getSelectedItem();
        if (Integer.parseInt(bidInput.getText()) > productTable.getSelectionModel().getSelectedItem().getPrice()){
            productTable.getSelectionModel().getSelectedItem().setPrice(Integer.parseInt(bidInput.getText()));

            productTable.getSelectionModel().getSelectedItem().addBid(new Bid(Integer.parseInt(bidInput.getText()), singletonPerson.getInstance()));
            //productTable.getSelectionModel().getSelectedItem().getBids();

            System.out.println("Bid placed");
            Main.switchSceneTo("marketplace");
            Main.showAlert(Alert.AlertType.CONFIRMATION, "Bid placed");
        }
        if (Integer.parseInt(bidInput.getText()) < productTable.getSelectionModel().getSelectedItem().getPrice()){
            System.out.println("Bid is lower than current highest.");
            Main.showAlert(Alert.AlertType.ERROR, "Bid is lower than current highest.");
        }
    }

    public void mainScreen() throws IOException {
        Main.switchSceneTo("mainUi");
    }
}
