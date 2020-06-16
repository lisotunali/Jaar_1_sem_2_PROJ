package GUI;

import BACKEND.Contact.Contact;
import BACKEND.Education.ImageWithName;
import BACKEND.Marketplace.AnimalProduct;
import BACKEND.Marketplace.MarketplaceInformation;
import BACKEND.Medical.Appointments;
import BACKEND.Person.Doctor;
import BACKEND.Person.Person;
import BACKEND.Person.SpecializationType;
import BACKEND.fakeDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Add fake persons

        setUpTestInstances();
        setUpGameData();

        SceneController.setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("Views/Account/login.fxml"));
        primaryStage.setTitle("Kumbaya 5a");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setUpTestInstances() {
        Person testperson = new Person("1", "1");
        Person testperson2 = new Person("2", "2");
        Person testperson1 = new Person("Test2", "test123");
        fakeDatabase.getUserDatabase().add(testperson);
        fakeDatabase.getUserDatabase().add(testperson1);
        fakeDatabase.getUserDatabase().add(testperson2);
        Contact testContact1 = new Contact("jan", "0612345678", "hallo123@hotmail.com", "haagse hogeschool");
        testperson.addContact(testContact1);
        

        testperson.addAnimal("Goat", 500);
        testperson.addAnimal("Sheep", 30);
        fakeDatabase.getUserDatabase().add(testperson);

        Doctor testDoctor = new Doctor("doctor", "test123", SpecializationType.EAR);
        testDoctor.setAvailableDay(DayOfWeek.SATURDAY, false);
        fakeDatabase.getUserDatabase().add(testDoctor);

        Doctor testDoctor2 = new Doctor("doctor2", "test123", Arrays.asList(SpecializationType.EAR, SpecializationType.GENERAL));
        fakeDatabase.getUserDatabase().add(testDoctor2);

        testperson.addContact(new Contact("jan", "0612345678", "hallo123@hotmail.com", "haagse hogeschool"));

        MarketplaceInformation marketinfo = new MarketplaceInformation("Test Product", "Dit is een testproduct voor de marketplace", 999, 10);
        MarketplaceInformation marketinfo2 = new MarketplaceInformation("New Product", "Nieuwe testproduct voor de marketplace", 50, 2);
        fakeDatabase.addProduct(new AnimalProduct(marketinfo, testperson.getAnimal("Goat"), testperson));
        fakeDatabase.addProduct(new AnimalProduct(marketinfo2, testperson.getAnimal("Sheep"), testperson));

        Appointments.createAppointment(LocalDate.of(2020, 6, 2), "test condition for a test appointment", SpecializationType.EAR, testperson1);
        Appointments.createAppointment(LocalDate.of(2022, 2, 13), "test condition", SpecializationType.GENERAL, testperson);
    }

    public void setUpGameData() {
        fakeDatabase.getImagesDatabase().add(new ImageWithName("bee", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/bee.png"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("doctor", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/doctor.jpg"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("dog", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/dog.jpg"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("elephant", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/elephant.png"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("fish", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/fish.png"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("giraffe", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/giraffe.png"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("rat", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/rat.png"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("sheep", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/sheep.png"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("turtle", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/turtle.png"))));
        fakeDatabase.getImagesDatabase().add(new ImageWithName("zebra", new Image(getClass().getResourceAsStream("/BACKEND/Education/Images/zebra.png"))));
    }
}
