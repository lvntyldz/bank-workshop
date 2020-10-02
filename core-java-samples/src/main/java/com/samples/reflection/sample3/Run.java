package com.samples.reflection.sample3;

import java.lang.reflect.Field;

/**
 * kategori adı final olarak tanımlanmış bir product class'ı
 */
class Product {

    private final String PRODUCT_CATEGORY_NAME;

    public Product(String PRODUCT_CATEGORY_NAME) {
        this.PRODUCT_CATEGORY_NAME = PRODUCT_CATEGORY_NAME;
    }

    public String getProductCategoryName() {
        return PRODUCT_CATEGORY_NAME;
    }

    @Override
    public String toString() {
        return "Product{" +
                "CATEGORY_NAME='" + PRODUCT_CATEGORY_NAME + '\'' +
                '}';
    }
}

public class Run {

    public static void main(String[] args) throws Exception {

        //final variable'a ilk değer çanta olarak atanır
        Product product = new Product("Çanta");
        System.out.println(product);

        //reflection ile private field'a erişilir
        Field field = product.getClass().getDeclaredField("PRODUCT_CATEGORY_NAME");
        field.setAccessible(true);
        field.set(product, "Ayakkabı");//private field'a(kategori) yeni değeri set edilir

        System.out.println(product);
    }
}
