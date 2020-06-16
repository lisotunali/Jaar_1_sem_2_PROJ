package BACKEND.Marketplace;

import BACKEND.Person.Person;

import java.util.ArrayList;

public class Product {


    private MarketplaceInformation marketinfo;
    private ArrayList<Bid> hasBid = new ArrayList<>();
    private Integer ID;
    private Person person;
    private static Integer uniqueID = 0;

    public Product(MarketplaceInformation marketinfo, Person person) {
        this.marketinfo = marketinfo;
        this.person = person;
        this.ID = uniqueID++;
    }

    public Person getPerson() {
        return person;
    }

    public String getPersonName() { return this.person.getName(); }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ArrayList<Bid> getBids() {
        return hasBid;
    }

    public void addBid(Bid bid) {
        hasBid.add(bid);
    }

    public void getBid(Integer id) {
        hasBid.get(id);
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public static Integer getUniqueID() {
        return uniqueID;
    }

    public String getAdvertTitle() {
        return marketinfo.getAdvertTitle();
    }

    public void setAdvertTitle(String advertTitle) {
        marketinfo.setAdvertTitle(advertTitle);
    }

    public String getAdvertDescription() {
        return marketinfo.getAdvertDescription();
    }

    public void setAdvertDescription(String advertDescription) {
        marketinfo.setAdvertDescription( advertDescription);
    }

    public Integer getPrice() {
        return marketinfo.getPrice();
    }

    public Integer getInitialPrice() {
        return marketinfo.getInitialPrice();
    }

    public void setPrice(Integer price) {
        marketinfo.setPrice(price);
    }

    public Integer getAmount() {
        return marketinfo.getAmount();
    }

    public void setAmount(Integer amount) {
        marketinfo.setAmount(amount);
    }

    public Integer getID() {
        return this.ID;
    }

    public void acceptBid(Person buyer) {

    }

    public void cancelOffer() {

    }
}
