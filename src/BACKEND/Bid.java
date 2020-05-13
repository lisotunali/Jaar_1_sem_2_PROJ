package BACKEND;

public class Bid {
    private Integer price;
    private Person person;
    private String name;

    public Bid(Integer price, Person person, String name) {
        this.price = price;
        this.person = person;
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    public String getName() { return name; }
}
