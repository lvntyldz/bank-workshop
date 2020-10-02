package com.samples.designppatterns.strategy.sample1;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<Product>();
    }

    public List<Product> addProduct(Product product) {
        products.add(product);
        return products;
    }

    public List<Product> removeProduct(Product product) {
        if (CollectionUtils.isEmpty(products)) {
            return null;
        }
        products.remove(product);
        return products;
    }

    public int calculateTotalAmount() {
        return products.stream().mapToInt(Product::getPrice).sum();
    }

    public void pay(PaymentService service) {
        int amount = calculateTotalAmount();
        service.pay(amount);
    }
}
