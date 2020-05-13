package GUI;

import BACKEND.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

    public void bidButtonAction() {
        //Product product = productTable.getSelectionModel().getSelectedItem();
        //if (bidInput.getText() > productTable.getSelectionModel().getSelectedItem().getPrice()){}
    }

    public void mainScreen() throws IOException {
        Main.switchSceneTo("mainUi");
    }
}
