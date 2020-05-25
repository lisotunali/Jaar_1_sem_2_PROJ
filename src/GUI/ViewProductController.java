package GUI;

import BACKEND.Bid;
import BACKEND.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ViewProductController {

    public TableView<Product> productTableView;
    public TableColumn<Product, String> productTitleColumn;
    public TableColumn<Product, String> productDescColumn;
    public TableColumn<Product, Integer> initialPriceColumn;
    public TableColumn<Product, Integer> currentPriceColumn;
    public TableColumn<Product, Integer> amountColumn;

    public TableView<Product> thisProductTableView;
    public TableColumn<Product, String> thisTitleColumn;
    public TableColumn<Product, String> thisDescColumn;
    public TableColumn<Product, Integer> thisInitialPriceColumn;
    public TableColumn<Product, Integer> thisAmountColumn;

    public TableView<Bid> sellTable;
    public TableColumn<Bid, String> buyerColumn;
    public TableColumn<Bid, Integer> bidColumn;

    public Button backButton;
    public Button acceptButton;

    private Product currentProduct;


    public void initialize() {

    }

    public void prevScreen() throws IOException {
        SceneController.switchTo("sell");
    }

    public void initData(Product selectedProduct) {
        currentProduct = selectedProduct;
        System.out.println(selectedProduct);
        ObservableList<Product> thisProduct = FXCollections.observableArrayList(selectedProduct);
        thisTitleColumn.setCellValueFactory(new PropertyValueFactory<>("advertTitle"));
        thisDescColumn.setCellValueFactory(new PropertyValueFactory<>("advertDescription"));
        thisInitialPriceColumn.setCellValueFactory(new PropertyValueFactory<>("initialPrice"));
        thisAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        thisProductTableView.setItems(thisProduct);
        thisProductTableView.getSortOrder().add(thisTitleColumn);
        //thisProductTableView.refresh();

        ObservableList<Bid> bids = FXCollections.observableArrayList(selectedProduct.getBids());
        buyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyerName"));
        bidColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        sellTable.setItems(bids);
        sellTable.getSortOrder().add(bidColumn);
        //sellTable.refresh();
    }


    public void acceptBid() throws Exception {
        if (sellTable.getSelectionModel().getSelectedItem() == null) {
            AlertClass.showAlert(Alert.AlertType.ERROR, "Please select a bid.");
            return;
        }

        if (sellTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to accept this bid?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                currentProduct.acceptBid(sellTable.getSelectionModel().getSelectedItem().getBuyer());
                singletonMarketplace.getInstance().removeProduct(currentProduct);
                AlertClass.showAlert(Alert.AlertType.INFORMATION, "Product sold.");
                SceneController.switchTo("sell");
            }
        }
    }
}
