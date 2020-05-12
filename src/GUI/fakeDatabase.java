package GUI;

import BACKEND.Person;

import java.util.ArrayList;

public class fakeDatabase {
    private static ArrayList<Person> userDatabase = new ArrayList<>();

    public static ArrayList<Person> getUserDatabase() {
        return userDatabase;
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
