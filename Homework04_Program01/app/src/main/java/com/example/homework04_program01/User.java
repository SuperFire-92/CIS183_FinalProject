package com.example.homework04_program01;

public class User
{
    private String username;
    private String password;
    private String fName;
    private String lName;
    private String address;
    private String email;
    private boolean isHandyman;

    User()
    {

    }

    User(String u, String p, String f, String l, String a, String e, boolean h)
    {
        username = u;
        password = p;
        fName = f;
        lName = l;
        address = a;
        email = e;
        isHandyman = h;
    }

    public void fillUser(String u, String p, String f, String l, String a, String e, boolean h)
    {
        username = u;
        password = p;
        fName = f;
        lName = l;
        address = a;
        email = e;
        isHandyman = h;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String u) {
        username = u;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String p) {
        password = p;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String f) {
        fName = f;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String l) {
        lName = l;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String a) {
        address = a;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        email = e;
    }

    public boolean isHandyman() {
        return isHandyman;
    }

    public void setHandyman(boolean h) {
        isHandyman = h;
    }
}
