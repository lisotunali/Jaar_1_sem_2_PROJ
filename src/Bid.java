public class Bid {
    private Integer price;
    private Person person;

    public Bid(Integer price, Person person) {
        this.price = price;
        this.person = person;
    }

    public Integer getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }
}
