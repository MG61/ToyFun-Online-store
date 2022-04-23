package com.example.babystore.Auth;

public class User {
    private String name, email, pass, phone, card;

    public User() {
    }



    public User(String email, String pass, String name, String phone, String card) {
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.phone = phone;
        this.card = card;

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
