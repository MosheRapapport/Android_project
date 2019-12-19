package com.example.my.model.entities;

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private AddressAndLocation address;

    public Person() {
        id="1";
        firstName="1";
        lastName="1";
        phoneNumber="1";
        email="1";




    }

    public Person(String id, String firstName, String lastName, String phoneNumber, String email, AddressAndLocation address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }




    public Person(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phoneNumber = person.getPhoneNumber();
        this.email = person.getEmail();
        this.address = person.getAddress();
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressAndLocation getAddress() {
        return address;
    }

    public void setAddress(AddressAndLocation address) {
        this.address = address;
    }
}
