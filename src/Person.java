import java.util.ArrayList;

public class Person {
    private String name;
    private String password;
    private ArrayList<Contact> addressBook;
    private ArrayList<Animal> hasLivestock;
    private Long personID;
    private static Long uniqueID = 0L;

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
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

    public void addAnimal(Animal animal) {
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
