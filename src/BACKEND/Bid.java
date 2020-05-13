package BACKEND;

public class Bid {
    private Integer price;
    private Person person;
    private String personName;

    public Bid(Integer price, Person person, String personName) {
        this.price = price;
        this.person = person;
        this.personName = personName;
    }

    public Integer getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    public String getName() { return personName; }
}
