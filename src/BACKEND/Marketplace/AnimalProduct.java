package BACKEND.Marketplace;

import BACKEND.Livestock.Animal;
import BACKEND.Person.Person;

public class AnimalProduct extends Product {

    private Animal animal;

    public AnimalProduct(String advertTitle, String advertDescription, Integer price, Integer amount, Animal animal, Person person) {
        super(advertTitle, advertDescription, price, amount, person);
        this.animal = animal;
        person.getAnimal(animal.getType()).removeAmount(amount);
    }

    public Animal getAnimal() {
        return animal;
    }

    @Override
    public void acceptBid(Person buyer) {
        buyer.addAnimal(getAnimal().getType(), getAmount());
    }

    @Override
    public void cancelOffer() {
        getPerson().addAnimal(getAnimal().getType(), getAmount());
    }
}
