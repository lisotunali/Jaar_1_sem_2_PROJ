package BACKEND;

import BACKEND.Contact.Contact;
import BACKEND.Education.Highscores;
import BACKEND.Education.ImageWithName;
import BACKEND.Marketplace.AnimalProduct;
import BACKEND.Marketplace.MarketplaceInformation;
import BACKEND.Marketplace.Product;
import BACKEND.Medical.Appointment;
import BACKEND.Medical.Appointments;
import BACKEND.Person.Doctor;
import BACKEND.Person.IPerson;
import BACKEND.Person.Person;
import BACKEND.Person.SpecializationType;
import javafx.scene.image.Image;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class fakeDatabase {
    private static ArrayList<IPerson> userDatabase = new ArrayList<>();
    private static ArrayList <Highscores>readingHSList = new ArrayList<>();
    private static ArrayList<ImageWithName> imagesDatabase = new ArrayList<>();
    private static ArrayList<Integer> numbersDatabase = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
    private static ArrayList<Product> marketplace = new ArrayList<Product>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    public static ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public static ArrayList<IPerson> getUserDatabase() {
        return userDatabase;
    }

    public static ArrayList<ImageWithName> getImagesDatabase(){
        return imagesDatabase;
    }

    public static ArrayList<Integer> getNumbersDatabase() { return numbersDatabase; }

    public static ArrayList<Highscores> getReadingHSList(){ return readingHSList;}



    public static void addReadingHS(Highscores highscores, String gameType){

    }

    public static ImageWithName getRandomImageWithName(){
        Random ran = new Random();
        int randomIndex = ran.nextInt(imagesDatabase.size());
        return imagesDatabase.get(randomIndex);
    }

    // Find given person in the database and update it
    public static void updatePerson(IPerson IPerson) {
        for (IPerson dbIPerson : userDatabase) {
            if (dbIPerson.getPersonID().equals(IPerson.getPersonID())) {
                dbIPerson.setPassword(IPerson.getPassword());
            }
        }
    }

    //All the marketplace methods
    public static ArrayList<Product> getMarketplace() {
        return marketplace;
    }

    public static void addProduct(Product product) {
        marketplace.add(product);
    }

    public static Product getProduct(Integer id) {
        for (Product products : marketplace) {
            if (products.getID().equals(id)) {
                return products;
            }
        }
        return null;
    }

    public static Product getProductByAdvertTitle(String advertTitle) {
        for (Product products : marketplace) {
            if (products.getAdvertTitle().equals(advertTitle)) {
                return products;
            }
        }
        return null;
    }

    public static ArrayList<Product> getAllProducts() {
        return marketplace;
    }

    public static void removeProduct(Product product) {
        marketplace.remove(product);
    }

    public static void setUpTestInstances() {
        IPerson testperson = new Person("1", "1");
        IPerson testperson2 = new Person("2", "2");
        IPerson testperson1 = new Person("Test2", "test123");
        getUserDatabase().add(testperson);
        getUserDatabase().add(testperson1);
        getUserDatabase().add(testperson2);
        Contact testContact1 = new Contact("jan", "0612345678", "hallo123@hotmail.com", "haagse hogeschool");
        testperson.addContact(testContact1);


        testperson.addAnimal("Goat", 500);
        testperson.addAnimal("Sheep", 30);
        getUserDatabase().add(testperson);

        Doctor testDoctor = new Doctor("doctor", "test123", SpecializationType.EAR);
        testDoctor.setAvailableDay(DayOfWeek.SATURDAY, false);
        getUserDatabase().add(testDoctor);

        Doctor testDoctor2 = new Doctor("doctor2", "test123", Arrays.asList(SpecializationType.EAR, SpecializationType.GENERAL));
        getUserDatabase().add(testDoctor2);

        testperson.addContact(new Contact("jan", "0612345678", "hallo123@hotmail.com", "haagse hogeschool"));

        MarketplaceInformation marketinfo = new MarketplaceInformation("Test Product", "Dit is een testproduct voor de marketplace", 999, 10);
        MarketplaceInformation marketinfo2 = new MarketplaceInformation("New Product", "Nieuwe testproduct voor de marketplace", 50, 2);
        addProduct(new AnimalProduct(marketinfo, testperson.getAnimal("Goat"), testperson));
        addProduct(new AnimalProduct(marketinfo2, testperson.getAnimal("Sheep"), testperson));

        Appointments.createAppointment(LocalDate.of(2020, 6, 2), "test condition for a test appointment", SpecializationType.EAR, testperson1);
        Appointments.createAppointment(LocalDate.of(2022, 2, 13), "test condition", SpecializationType.GENERAL, testperson);
    }

    public static void setUpGameData() {
        getImagesDatabase().add(new ImageWithName("bee", new Image(fakeDatabase.class.getResourceAsStream("/BACKEND/Education/Images/bee.png"))));
        getImagesDatabase().add(new ImageWithName("doctor", new Image(fakeDatabase.class.getResourceAsStream("/BACKEND/Education/Images/doctor.jpg"))));
        getImagesDatabase().add(new ImageWithName("dog", new Image(fakeDatabase.class.getResourceAsStream("/BACKEND/Education/Images/dog.jpg"))));
        getImagesDatabase().add(new ImageWithName("elephant", new Image(fakeDatabase.class.getResourceAsStream("/BACKEND/Education/Images/elephant.png"))));
        getImagesDatabase().add(new ImageWithName("fish", new Image(fakeDatabase.class.getResourceAsStream("/BACKEND/Education/Images/fish.png"))));
        getImagesDatabase().add(new ImageWithName("giraffe", new Image(fakeDatabase.class.getResourceAsStream("/BACKEND/Education/Images/giraffe.png"))));
        getImagesDatabase().add(new ImageWithName("rat", new Image(fakeDatabase.class.getResourceAsStream("/BACKEND/Education/Images/rat.png"))));
        getImagesDatabase().add(new ImageWithName("sheep", new Image(fakeDatabase.class.getResourceAsStream("/BACKEND/Education/Images/sheep.png"))));
        getImagesDatabase().add(new ImageWithName("turtle", new Image(fakeDatabase.class.getResourceAsStream("/BACKEND/Education/Images/turtle.png"))));
        getImagesDatabase().add(new ImageWithName("zebra", new Image(fakeDatabase.class.getResourceAsStream("/BACKEND/Education/Images/zebra.png"))));
    }
}
