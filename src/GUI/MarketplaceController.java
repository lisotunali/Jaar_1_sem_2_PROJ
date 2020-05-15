package GUI;

import BACKEND.Bid;
import BACKEND.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class MarketplaceController {
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


    public void initialize() {
        ObservableList<Product> products = FXCollections.observableArrayList(singletonMarketplace.getInstance().getAllProducts());
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("advertTitle"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        sellerColumn.setCellValueFactory(new PropertyValueFactory<>("PersonName"));
        productTable.setItems(products);
    }

    public void sellPage() throws IOException {
        SceneController.switchTo("sell");
    }

    public void bidButtonAction() {
        System.out.println("Trying to place bid...");
        //If no product is selected
        if (productTable.getSelectionModel().getSelectedItem() == null) {
            AlertClass.showAlert(Alert.AlertType.WARNING, "Please select a product before trying to bid.");
            return;
        }

        //If seller tries to bid on their own product
        if (singletonPerson.getInstance().equals(productTable.getSelectionModel().getSelectedItem().getPerson())) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "You can't place a bid on your own product.");
            return;
        }


        Integer bidInput = ConvertClass.convertToInt(this.bidInput.getText());
        //If bid is empty, popup alert
        if (bidInput == null) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Your bid is invalid.");
            return;
        }
        //If bid is lower than current highest price
        if (!(singletonPerson.getInstance().equals(productTable.getSelectionModel().getSelectedItem().getPerson())) && bidInput <= productTable.getSelectionModel().getSelectedItem().getPrice()) {
            System.out.println("Bid is lower than current highest.");
            AlertClass.showAlert(Alert.AlertType.ERROR, "Your bid is not higher than current highest bid.");
            return;
        }

        //If bid is valid
        if (!(singletonPerson.getInstance().equals(productTable.getSelectionModel().getSelectedItem().getPerson())) && bidInput > productTable.getSelectionModel().getSelectedItem().getPrice()) {
            productTable.getSelectionModel().getSelectedItem().setPrice(bidInput);

            productTable.getSelectionModel().getSelectedItem().addBid(new Bid(bidInput, singletonPerson.getInstance(), productTable.getSelectionModel().getSelectedItem().getPerson(), singletonPerson.getInstance().getName(), productTable.getSelectionModel().getSelectedItem().getAdvertTitle()));
            System.out.println("Price: €" + bidInput + " " + "Bid from: " + singletonPerson.getInstance().getName());
            System.out.println(productTable.getSelectionModel().getSelectedItem().getBids());

            System.out.println("Bid placed");
            productTable.refresh();
            refreshBids();
            AlertClass.showAlert(Alert.AlertType.CONFIRMATION, "Bid placed");
        }
    }

    public void refreshBids() {

        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {

            ObservableList<Bid> bids = FXCollections.observableArrayList(selectedProduct.getBids());
            buyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyerName"));
            bidColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            bidTable.setItems(bids);
            bidTable.getSortOrder().add(bidColumn);
            bidTable.refresh();

        }
    }

    public void mainScreen() throws IOException {
        SceneController.switchTo("mainUi");
    }
}
