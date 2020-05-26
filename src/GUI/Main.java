package GUI;

import BACKEND.*;
import Education.ImageWithName;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Add fake persons

        setUpTestInstances();
        createImagewithNames();

        SceneController.setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Kumbaya 5a");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setUpTestInstances() {
        Person testperson = new Person("test", "test123");
        Person testperson1 = new Person("Rishwan", "test123");
        fakeDatabase.getUserDatabase().add(testperson);
        fakeDatabase.getUserDatabase().add(testperson1);
        Contact testContact1 = new Contact("jan", "0612345678", "hallo123@hotmail.com", "haagse hogeschool");
        testperson.addContact(testContact1);
        testperson.addAnimal("Goat", 500);
        testperson.addAnimal("Sheep", 30);
        singletonMarketplace.getInstance().addProduct(new AnimalProduct("Test Product", "Dit is een testproduct voor de marketplace", 999, 10, testperson.getAnimal("Goat"), testperson));
        singletonMarketplace.getInstance().addProduct(new AnimalProduct("New Product", "Nieuwe testproduct voor de marketplace", 50, 2, testperson.getAnimal("Sheep"), testperson));
    }

    public void createImagewithNames(){
        fakeDatabase.getImagesDatabase().add( new ImageWithName("bee", new Image("bee.png")));
        fakeDatabase.getImagesDatabase().add( new ImageWithName("doctor", new Image ("doctor.png")));
        fakeDatabase.getImagesDatabase().add( new ImageWithName("dog", new Image("dog.png")));
        fakeDatabase.getImagesDatabase().add( new ImageWithName("elephant", new Image ("elephant.png")));
        fakeDatabase.getImagesDatabase().add( new ImageWithName("fish", new Image ("fish.png")));
        fakeDatabase.getImagesDatabase().add( new ImageWithName("giraffe", new Image("giraffe.png")));
        fakeDatabase.getImagesDatabase().add( new ImageWithName("rat", new Image("rat.png")));
        fakeDatabase.getImagesDatabase().add( new ImageWithName("sheep", new Image("sheep.png")));
        fakeDatabase.getImagesDatabase().add( new ImageWithName("turtle", new Image("turtle.png")));
        fakeDatabase.getImagesDatabase().add( new ImageWithName("zebra", new Image("zebra.png")));
    }
}
