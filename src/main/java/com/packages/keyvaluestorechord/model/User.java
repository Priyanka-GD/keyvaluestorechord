package com.packages.keyvaluestorechord.model;

public class User {
    String name;
    String address;
    String email;
    int userId;

    public User (String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.userId = hashCode(name);
    }

    private int hashCode (String name) {
        return Math.abs(name.hashCode()) % 100;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public int getUserId () {
        return userId;
    }

    public void setUserId (int userId) {
        this.userId = userId;
    }
}