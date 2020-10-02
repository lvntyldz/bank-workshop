package com.samples.reflection.sample2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * bu örnek ile bir class'ın private methoduna erişim sağlanmıştır.
 */
public class Run {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Product product = new Product("ayakkabı", 150);

        Method privateMethod = Product.class.getDeclaredMethod("calculateDiscount", null);
        privateMethod.setAccessible(true);

        Integer displayPrice = (Integer) privateMethod.invoke(product, null);

        System.out.println("price with discount  = " + displayPrice);
    }

}
