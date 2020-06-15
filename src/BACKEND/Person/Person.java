package BACKEND.Person;

import BACKEND.Contact.Contact;
import BACKEND.Education.Highscores;
import BACKEND.Livestock.Animal;

import java.util.ArrayList;

public class Person {
    private static Long uniquePersonID = 0L;
    private String name;
    private String password;
    private ArrayList<Contact> addressBook = new ArrayList<>();
    private ArrayList<Animal> hasLivestock = new ArrayList<>();
    private ArrayList<Highscores> highscoresList =  new ArrayList<>();
    private Long personID;
    private String personalData;
    private Highscores math =  new Highscores("math",0, null);
    private Highscores writing =  new Highscores("writing",0, null);
    private Highscores reading =  new Highscores("reading",0, null);

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
        this.personID = uniquePersonID++;
        this.personalData = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addAnimal(String type, Integer amount) {
        for (Animal animalsInLivestock : hasLivestock) {
            if (animalsInLivestock.getType() == type) {
                animalsInLivestock.addAmount(amount);
                return;
            }
        }
        hasLivestock.add(new Animal(type, amount));
    }

    public void removeAnimal(String type) {
        Animal toBeRemoved = null;
        for (Animal animal : hasLivestock) {
            if (type.equals(animal.getType())) {
                toBeRemoved = animal;
            }
        }
        hasLivestock.remove(toBeRemoved);
    }

    public Long getPersonID() {
        return personID;
    }

    public Animal getAnimal(String type) {
        for (Animal animal : hasLivestock) {
            if (animal.getType().equals(type))
                return animal;
        }

        return null;
    }

    public ArrayList<Animal> getAnimals() {
        return hasLivestock;
    }

    public ArrayList<Contact> getAddressBook() {
        return addressBook;
    }

    public void addContact(Contact contact) {
        addressBook.add(contact);
    }

    public void removeContact(Contact contact) {
        addressBook.remove(contact);
    }

    public Contact getContact(String name) {
        Contact toBeReturned = null;
        for (Contact contact : addressBook) {
            if (contact.getName().equals(name)) {
                toBeReturned = contact;
            }
        }
        return toBeReturned;
    }

    public String getPersonalData() {
        return personalData;
    }

    public void setPersonalData(String text) {
        this.personalData = text;
    }


    public Highscores getHS(String gameType){
       if(gameType.equals(math.getGameName())){
           return math;
       }
        if(gameType.equals(writing.getGameName())){
            return writing;
        }
        if(gameType.equals(reading.getGameName())){
            return reading;
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
