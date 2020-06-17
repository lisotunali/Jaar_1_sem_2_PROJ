package BACKEND.Person;

import BACKEND.Contact.Contact;
import BACKEND.Education.Highscores;
import BACKEND.Livestock.Animal;

import java.util.ArrayList;

public interface IPerson {
    String getName();

    void setName(String name);

    String getPassword();

    void setPassword(String password);

    void addAnimal(String type, Integer amount);

    void removeAnimal(String type);

    Long getPersonID();

    Animal getAnimal(String type);

    ArrayList<Animal> getAnimals();

    ArrayList<Contact> getAddressBook();

    void addContact(Contact contact);

    void removeContact(Contact contact);

    Contact getContact(String name);

    String getPersonalData();

    void setPersonalData(String text);

    Highscores getHS(String gameType);

    @Override
    String toString();
}
