public class AnimalProduct {
    private String title; private String description; private Integer price;
    private Integer amount; private Person person ;private Animal animal;

    public AnimalProduct(String title, String description, Integer price, Integer amount, Person person, Animal animal) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.person = person;
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }
}
