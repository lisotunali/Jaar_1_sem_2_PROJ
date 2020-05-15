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

    public void bidButtonAction() {
        System.out.println("Trying to place bid...");
        //If no product is selected
        if (productTable.getSelectionModel().getSelectedItem() == null){
            Main.showAlert(Alert.AlertType.WARNING, "Please select a product before trying to bid.");
        }

        //If seller tries to bid on their own product
        if (singletonPerson.getInstance().equals(productTable.getSelectionModel().getSelectedItem().getPerson())){
            Main.showAlert(Alert.AlertType.ERROR, "You can't place a bid on your own product.");
        }

        //If bid is lower than current highest price
        if (!(singletonPerson.getInstance().equals(productTable.getSelectionModel().getSelectedItem().getPerson())) && Integer.parseInt(bidInput.getText()) <= productTable.getSelectionModel().getSelectedItem().getPrice()){
            System.out.println("Bid is lower than current highest.");
            Main.showAlert(Alert.AlertType.ERROR, "Your bid is not higher than current highest bid.");
        }

        //If bid is valid
        if (!(singletonPerson.getInstance().equals(productTable.getSelectionModel().getSelectedItem().getPerson())) && Integer.parseInt(bidInput.getText()) > productTable.getSelectionModel().getSelectedItem().getPrice()){
            productTable.getSelectionModel().getSelectedItem().setPrice(Integer.parseInt(bidInput.getText()));

            productTable.getSelectionModel().getSelectedItem().addBid(new Bid(Integer.parseInt(bidInput.getText()), singletonPerson.getInstance(), singletonPerson.getInstance().getName()));
            System.out.println("Price: €" + Integer.parseInt(bidInput.getText()) + " " + "Bid from: " + singletonPerson.getInstance().getName());
            System.out.println(productTable.getSelectionModel().getSelectedItem().getBids());

            System.out.println("Bid placed");
            productTable.refresh();
            refreshBids();
            Main.showAlert(Alert.AlertType.CONFIRMATION, "Bid placed");
        }
    }

    public void refreshBids(){
        /*
        if (productTable.getSelectionModel().getSelectedItem().getBids().isEmpty()){
            bidTable.setItems(null);
            Main.showAlert(Alert.AlertType.ERROR, "No bids have been made on this product yet.");
        }
         */

        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null){

            ObservableList<Bid> bids = FXCollections.observableArrayList(selectedProduct.getBids());
            buyerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
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
