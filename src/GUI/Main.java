package GUI;

import BACKEND.Bid;
import BACKEND.Contact;
import BACKEND.Person;
import BACKEND.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    // Changes the current scene to the given template name
    public static void switchSceneTo(String templateName) throws IOException {
        Stage primaryStage = Main.getPrimaryStage();
        Parent root = FXMLLoader.load(Main.class.getResource(templateName + ".fxml"));
        primaryStage.getScene().setRoot(root);
    }

    public static void showAlert(Alert.AlertType type, String text) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set up instances for testing
        setUpTestInstances();

        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Kumbaya 5a");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setUpTestInstances() {
        Person testperson = new Person("test", "test123");
        fakeDatabase.getUserDatabase().add(testperson);
        Contact testContact1 = new Contact("jan", "0612345678", "hallo123@hotmail.com", "haagse hogeschool");
        testperson.addContact(testContact1);
        singletonMarketplace.getInstance().addProduct(new Product("Test Product", "Dit is een testproduct voor de marketplace", 999, 10, testperson));
        singletonMarketplace.getInstance().addProduct(new Product("New Product", "Nieuwe testproduct voor de marketplace", 50, 2, testperson));
    }
}
