package BACKEND;

public class AnimalProduct extends Product {

    private Animal animal;

    public AnimalProduct(String advertTitle, String advertDescription, Integer price, Integer amount, Animal animal, Person person) {
        super(advertTitle, advertDescription, price, amount, person);
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }
}
