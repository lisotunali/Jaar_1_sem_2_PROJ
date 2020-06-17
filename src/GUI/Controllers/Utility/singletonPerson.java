package GUI.Controllers.Utility;

import BACKEND.Person.IPerson;

public class singletonPerson {
    private static IPerson IPerson;

    private singletonPerson() {
    }

    public static IPerson getInstance() {
        return IPerson;
    }

    // Set the current person, this only happens at login or register
    public static void setPerson(IPerson IPerson) {
        singletonPerson.IPerson = IPerson;
    }
}
