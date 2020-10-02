package com.samples.reflection.sample2;

public class Product {

    private static final int DISCOUNT_VAL = 10;
    private String title;
    private int price;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * indirimli price değerini dönen public method
     */
    public int getPrice() {
        return calculateDiscount();
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * bu private  methoda reflection ile erişilecek
     */
    private int calculateDiscount() {
        return price > DISCOUNT_VAL ? price - DISCOUNT_VAL : price;
    }


}
