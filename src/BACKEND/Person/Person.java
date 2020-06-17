package BACKEND.Person;

import BACKEND.Contact.Contact;
import BACKEND.Education.Highscores;
import BACKEND.Livestock.Animal;

import java.util.ArrayList;

public class Person implements IPerson {
    private static Long uniquePersonID = 0L;
    private String name;
    private String password;
    private final ArrayList<Contact> addressBook = new ArrayList<>();
    private final ArrayList<Animal> hasLivestock = new ArrayList<>();
    private final ArrayList<Highscores> highscoresList = new ArrayList<>();
    private final Long personID;
    private String personalData;
    private final Highscores math = new Highscores("math", 0, null);
    private final Highscores writing = new Highscores("writing", 0, null);
    private final Highscores reading = new Highscores("reading", 0, null);

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
        this.personID = uniquePersonID++;
        this.personalData = "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void addAnimal(String type, Integer amount) {
        for (Animal animalsInLivestock : hasLivestock) {
            if (animalsInLivestock.getType().equals(type)) {
                animalsInLivestock.addAmount(amount);
                return;
            }
        }
        hasLivestock.add(new Animal(type, amount));
    }

    @Override
    public void removeAnimal(String type) {
        Animal toBeRemoved = null;
        for (Animal animal : hasLivestock) {
            if (type.equals(animal.getType())) {
                toBeRemoved = animal;
            }
        }
        hasLivestock.remove(toBeRemoved);
    }

    @Override
    public Long getPersonID() {
        return personID;
    }

    @Override
    public Animal getAnimal(String type) {
        for (Animal animal : hasLivestock) {
            if (animal.getType().equals(type))
                return animal;
        }

        return null;
    }

    @Override
    public ArrayList<Animal> getAnimals() {
        return hasLivestock;
    }

    @Override
    public ArrayList<Contact> getAddressBook() {
        return addressBook;
    }

    @Override
    public void addContact(Contact contact) {
        addressBook.add(contact);
    }

    @Override
    public void removeContact(Contact contact) {
        addressBook.remove(contact);
    }

    @Override
    public Contact getContact(String name) {
        Contact toBeReturned = null;
        for (Contact contact : addressBook) {
            if (contact.getName().equals(name)) {
                toBeReturned = contact;
            }
        }
        return toBeReturned;
    }

    @Override
    public String getPersonalData() {
        return personalData;
    }

    @Override
    public void setPersonalData(String text) {
        this.personalData = text;
    }


    @Override
    public Highscores getHS(String gameType) {
        if (gameType.equals(math.getGameName())) {
            return math;
        }
        if (gameType.equals(writing.getGameName())) {
            return writing;
        }
        if (gameType.equals(reading.getGameName())) {
            return reading;
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}

