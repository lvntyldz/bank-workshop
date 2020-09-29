package com.samples.designppatterns.strategy.sample1;

public class Product {
    private String plu;
    private int price;

    public Product(String plu, int price) {
        this.plu = plu;
        this.price = price;
    }

    public String getPlu() {
        return plu;
    }

    public void setPlu(String plu) {
        this.plu = plu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
