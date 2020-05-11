public class Animal {
    private Integer amountInStock;
    private LivestockType type;

    public Animal (LivestockType type, Integer amountInStock){
        this.type = type ;
        this.amountInStock = amountInStock;
    }

    public Integer getAmount(){
        return amountInStock;
    }

    public LivestockType getType() {
        return type;
    }

    public void setAmount(Integer amount){
        amountInStock = amount;
    }

}

