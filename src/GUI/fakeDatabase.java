package GUI;

import BACKEND.Person;
import Education.ImageWithName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class fakeDatabase {
    private static ArrayList<Person> userDatabase = new ArrayList<>();

    private static ArrayList<ImageWithName> imagesDatabase = new ArrayList<>();

    private static ArrayList<Integer> numbersDatabase = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));

    public static ArrayList<Person> getUserDatabase() {
        return userDatabase;
    }

    public static ArrayList<ImageWithName> getImagesDatabase(){
        return imagesDatabase;
    }

    public static ArrayList<Integer> getNumbersDatabase() { return numbersDatabase; }

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
