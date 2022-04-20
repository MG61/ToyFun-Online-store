package com.example.babystore.RecyclerAllProduct;

public class Toy {
    private int id;
    private String name;
    private String price;
    private String atr;
    private byte[] image;

    public Toy(int id, String name, String price, String atr, byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.atr = atr;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAtr() {
        return atr;
    }

    public void setAtr(String atr) {
        this.atr = atr;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
