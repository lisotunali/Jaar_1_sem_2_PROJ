package BACKEND;

public class Bid {
    private Integer price;
    private Person buyer;
    private Person seller;
    private String name;
    private String productName;

    public Bid(Integer price, Person buyer, Person seller, String name, String productName) {
        this.price = price;
        this.buyer = buyer;
        this.seller = seller;
        this.name = name;
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public Person getBuyer() {
        return buyer;
    }

    public Person getSeller() {
        return seller;
    }

    public String getName() {
        return name;
    }

    public String getProductName() {
        return productName;
    }
}
