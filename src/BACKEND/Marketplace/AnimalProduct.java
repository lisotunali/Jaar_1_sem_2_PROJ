package BACKEND.Marketplace;

import BACKEND.Livestock.Animal;
import BACKEND.Person.Person;

public class AnimalProduct extends Product {

    private Animal animal;

    public AnimalProduct(MarketplaceInformation marketinfo, Animal animal, Person person) {
        super(marketinfo, person);
        this.animal = animal;
        person.getAnimal(animal.getType()).removeAmount(marketinfo.getAmount());
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
