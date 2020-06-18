package BACKEND.Contact;

import BACKEND.Person.IPerson;

public class Contact {

    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private IPerson IPerson = null;

    public Contact(String name, String phoneNumber, String email, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public void setPerson(IPerson IPerson){
        this.IPerson = IPerson;
    }

    public IPerson getPerson (){
        return IPerson;
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
