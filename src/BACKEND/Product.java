package BACKEND;

import java.util.ArrayList;

public class Product {


    private String advertTitle;
    private String advertDescription;
    private Integer price;
    private Integer amount;
    private ArrayList<Bid> hasBid;
    private Integer ID;
    private Person person;
    private static Integer uniqueID = 0;

    public Product(Integer ID, String advertTitle, String advertDescription, Integer price, Integer amount, Person person) {
        this.advertTitle = advertTitle;
        this.advertDescription = advertDescription;
        this.price = price;
        this.amount = amount;
        this.person = person;
        this.ID = uniqueID++;
        //uniqueID = this.ID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ArrayList<Bid> getBids() {
        return hasBid;
    }

    public void addBid(Bid bid) {
        hasBid.add(bid);
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public static Integer getUniqueID() {
        return uniqueID;
    }

    public String getAdvertTitle() {
        return advertTitle;
    }

    public void setAdvertTitle(String advertTitle) {
        this.advertTitle = advertTitle;
    }

    public String getAdvertDescription() {
        return advertDescription;
    }

    public void setAdvertDescription(String advertDescription) {
        this.advertDescription = advertDescription;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getID(){
        return this.ID;
    }
}
