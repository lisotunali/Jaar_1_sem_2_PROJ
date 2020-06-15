package GUI.Controllers.Utility;

import BACKEND.Person.Person;

public class singletonPerson {
    private static Person person;

    private singletonPerson() {
    }

    public static Person getInstance() {
        return person;
    }

    // Set the current person, this only happens at login or register
    public static void setPerson(Person person) {
        singletonPerson.person = person;
    }
}
