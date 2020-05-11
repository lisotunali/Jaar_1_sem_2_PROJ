public class Contact {

    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private Long contactID;
    private static Long uniqueID = 0L;

    public Contact(String name, String phoneNumber, String email, String address){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.uniqueID++;
        contactID = getUniqueID();
    }

    public String getName(){
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Long getUniqueID(){
        return uniqueID;
    }

    public void setName (String name){
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public void setAddress (String address){
        this.address = address;
    }


}
