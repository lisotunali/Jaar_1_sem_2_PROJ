package BACKEND.Marketplace;

import BACKEND.Person.IPerson;

public class Bid {
    private Integer price;
    private IPerson buyer;
    private IPerson seller;
    private String buyerName;
    private String productName;

    public Bid(Integer price, IPerson buyer, IPerson seller, String buyerName, String productName) {
        this.price = price;
        this.buyer = buyer;
        this.seller = seller;
        this.buyerName = buyerName;
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public IPerson getBuyer() {
        return buyer;
    }

    public IPerson getSeller() {
        return seller;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getProductName() {
        return productName;
    }
}
