package GUI;

import BACKEND.Contact;
import BACKEND.Person;
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
        // Add fake persons

        setUpTestInstances();

        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Kumbaya 5a");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setUpTestInstances() {
        Person testperson = new Person("1", "1");
        fakeDatabase.getUserDatabase().add(testperson);
        Contact testContact1 = new Contact("jan", "0612345678", "hallo123@hotmail.com", "haagse hogeschool");
        testperson.addContact(testContact1);
        testperson.addAnimal("Goat",500);
        testperson.addAnimal("Sheep",30);
    }
}
