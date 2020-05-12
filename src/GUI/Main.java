package GUI;

import BACKEND.Person;
import BACKEND.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Add fake persons
        Person person = new Person("test", "test123");
        fakeDatabase.getUserDatabase().add(person);
        singletonMarketplace.getInstance().addProduct(new Product(25, "Test Product", "Dit is een testproduct voor de marketplace", 999, 10, person));

        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Kumbaya 5a");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
