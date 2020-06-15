package GUI;

import BACKEND.Appointment;
import BACKEND.Appointments;
import BACKEND.Person;
import BACKEND.Product;
import Education.Highscores;
import Education.ImageWithName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class fakeDatabase {
    private static ArrayList<Person> userDatabase = new ArrayList<>();
    private static ArrayList <Highscores>readingHSList = new ArrayList<>();
    private static ArrayList<ImageWithName> imagesDatabase = new ArrayList<>();
    private static ArrayList<Integer> numbersDatabase = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
    private static ArrayList<Product> marketplace = new ArrayList<Product>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    public static ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public static ArrayList<Person> getUserDatabase() {
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
    public static void updatePerson(Person person) {
        for (Person dbPerson : userDatabase) {
            if (dbPerson.getPersonID().equals(person.getPersonID())) {
                dbPerson.setPassword(person.getPassword());
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




}
