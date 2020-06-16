package BACKEND.Marketplace;

public class MarketplaceInformation {

    private String advertTitle;
    private String advertDescription;
    private Integer price;
    private Integer initialPrice;
    private Integer amount;

    public MarketplaceInformation(String advertTitle, String advertDescription, Integer price, Integer amount) {
        this.advertTitle = advertTitle;
        this.advertDescription = advertDescription;
        this.price = price;
        this.initialPrice = price;
        this.amount = amount;
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

    public Integer getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Integer initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
