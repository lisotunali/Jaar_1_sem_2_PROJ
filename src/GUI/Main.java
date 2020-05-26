package GUI;

import BACKEND.AnimalProduct;
import BACKEND.Contact;
import BACKEND.Doctor;
import BACKEND.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Add fake persons

        setUpTestInstances();
        SceneController.setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Kumbaya 5a");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setUpTestInstances() {
        Person testperson = new Person("test", "test123");
        testperson.addAnimal("Goat", 500);
        testperson.addAnimal("Sheep", 30);
        fakeDatabase.getUserDatabase().add(testperson);

        Person testperson1 = new Person("Rishwan", "test123");
        fakeDatabase.getUserDatabase().add(testperson1);

        Doctor testDoctor = new Doctor("doctor", "test123");
        fakeDatabase.getUserDatabase().add(testDoctor);

        testperson.addContact(new Contact("jan", "0612345678", "hallo123@hotmail.com", "haagse hogeschool"));

        singletonMarketplace.getInstance().addProduct(new AnimalProduct("Test Product", "Dit is een testproduct voor de marketplace", 999, 10, testperson.getAnimal("Goat"), testperson));
        singletonMarketplace.getInstance().addProduct(new AnimalProduct("New Product", "Nieuwe testproduct voor de marketplace", 50, 2, testperson.getAnimal("Sheep"), testperson));
    }
}
