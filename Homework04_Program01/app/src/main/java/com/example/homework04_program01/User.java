package com.example.homework04_program01;

public class User
{
    private String email;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private String description;

    private boolean isHandyman;

    User()
    {

    }

    User(String e, String p, String n, String a, String pN, String d, boolean h)
    {
        email = e;
        password = p;
        name = n;
        address = a;
        phoneNumber = pN;
        description = d;
        isHandyman = h;
    }

    public void fillUser(String e, String p, String n, String a, String pN, String d, boolean h)
    {
        email = e;
        password = p;
        name = n;
        address = a;
        phoneNumber = pN;
        description = d;
        isHandyman = h;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHandyman() {
        return isHandyman;
    }

    public void setHandyman(boolean handyman) {
        isHandyman = handyman;
    }
}
