package GUI;

import BACKEND.Person;

import java.util.ArrayList;

public class fakeDatabase {
    private static ArrayList<Person> userDatabase = new ArrayList<>();

    public static ArrayList<Person> getUserDatabase() {
        return userDatabase;
    }

}
