package GUI;

import BACKEND.Bid;
import BACKEND.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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


    public void initialize() {
        ObservableList<Product> selectedProduct = FXCollections.observableArrayList(productTableView.getSelectionModel().getSelectedItem());
        thisTitleColumn.setCellValueFactory(new PropertyValueFactory<>("advertTitle"));
        thisDescColumn.setCellValueFactory(new PropertyValueFactory<>("advertDescription"));
        thisInitialPriceColumn.setCellValueFactory(new PropertyValueFactory<>("initialPrice"));
        thisAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        thisProductTableView.setItems(selectedProduct);
        thisProductTableView.getSortOrder().add(thisTitleColumn);
        //thisProductTableView.refresh();

        ObservableList<Bid> bids = FXCollections.observableArrayList(productTableView.getSelectionModel().getSelectedItem().getBids());
        buyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyerName"));
        bidColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        sellTable.setItems(bids);
        sellTable.getSortOrder().add(bidColumn);
        //sellTable.refresh();
    }


    public void prevScreen() throws IOException {
        SceneController.switchTo("sell");
    }
}
