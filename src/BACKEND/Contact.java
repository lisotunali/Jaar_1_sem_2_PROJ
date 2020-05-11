package BACKEND;

public class Contact {

    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private Person person = null;

    public Contact(String name, String phoneNumber, String email, String address){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    public Person getPerson (){
        return person;
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
