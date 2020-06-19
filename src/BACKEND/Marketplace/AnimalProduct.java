package BACKEND.Marketplace;

import BACKEND.Livestock.Animal;
import BACKEND.Person.IPerson;

public class AnimalProduct extends Product {

    private Animal animal;

    public AnimalProduct(MarketplaceInformation marketinfo, Animal animal, IPerson IPerson) {
        super(marketinfo, IPerson);
        this.animal = animal;
        IPerson.getAnimal(animal.getType()).removeAmount(marketinfo.getAmount());
    }

    public Animal getAnimal() {
        return animal;
    }

    @Override
    public void acceptBid(IPerson buyer) {
        buyer.addAnimal(getAnimal().getType(), getAmount());
    }

    @Override
    public void cancelOffer() {
        getPerson().addAnimal(getAnimal().getType(), getAmount());
    }
}
