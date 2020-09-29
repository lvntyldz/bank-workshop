package com.samples.designppatterns.strategy.sample1;

public class Run {
    private static final int WEB_DISCOUNT_AMOUNT = 15;
    private static final int MOBIL_DISCOUNT_RATIO = 20;

    public static void main(String[] args) {

        Product product1 = new Product("CANTA123321", 200);
        Product product2 = new Product("TERLIK123321", 150);
        Product product3 = new Product("CUZDAN123321", 100);

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);
        cart.pay(new MobilePaymentService(MOBIL_DISCOUNT_RATIO));
        cart.pay(new WebPaymentService(WEB_DISCOUNT_AMOUNT));

    }
}
