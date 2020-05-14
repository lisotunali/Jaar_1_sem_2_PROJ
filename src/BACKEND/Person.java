package BACKEND;

import java.util.ArrayList;

public class Person {
    private static Long uniquePersonID = 0L;
    private String name;
    private String password;
    private ArrayList<Contact> addressBook = new ArrayList<>();
    private ArrayList<Animal> hasLivestock = new ArrayList<>();
    private Long personID;

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
        this.personID = uniquePersonID++;
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
            hasLivestock.add(new Animal(type,amount));
    }
     public void removeAnimal(String type) {
        for (Animal animal:hasLivestock) {
            if(type.equals(animal.getType())){
                hasLivestock.remove(animal);
            }
        }
    }
    public Long getPersonID() {
        return personID;
    }

    public Animal getAnimal(LivestockType type) {
        for (Animal animal : hasLivestock) {
            if (animal.getType() == type)
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
}
