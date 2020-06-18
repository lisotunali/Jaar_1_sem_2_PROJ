package BACKEND.Livestock;

public class Animal {
    private Integer amountInStock;
    private String type;

    public Animal (String type, Integer amountInStock){
        this.type = type ;
        this.amountInStock = amountInStock;
    }

    public Integer getAmount(){
        return amountInStock;
    }

    public String getType() {
        return type;
    }

    public void addAmount(Integer amount){
        amountInStock += amount;
    }
    public void removeAmount(Integer amount){
        amountInStock -= amount;
    }
    public void setAmount(Integer amount){
        amountInStock = amount;
    }

    public String toString(){
        return type+","+ amountInStock;
    }
}
