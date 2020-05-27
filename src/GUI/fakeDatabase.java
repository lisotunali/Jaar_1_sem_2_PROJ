package GUI;

import BACKEND.Person;
import Education.ImageWithName;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class fakeDatabase {
    private static ArrayList<Person> userDatabase = new ArrayList<>();

    private static ArrayList<ImageWithName> imagesDatabase = new ArrayList<>();

    public static ArrayList<Person> getUserDatabase() {
        return userDatabase;
    }

    public static ArrayList<ImageWithName> getImagesDatabase(){
        return imagesDatabase;
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
}
