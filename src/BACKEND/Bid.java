package BACKEND;

public class Bid {
    private Integer price;
    private Person buyer;
    private Person seller;
    private String buyerName;
    private String productName;

    public Bid(Integer price, Person buyer, Person seller, String buyerName, String productName) {
        this.price = price;
        this.buyer = buyer;
        this.seller = seller;
        this.buyerName = buyerName;
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

    public String getBuyerName() {
        return buyerName;
    }

    public String getProductName() {
        return productName;
    }
}
