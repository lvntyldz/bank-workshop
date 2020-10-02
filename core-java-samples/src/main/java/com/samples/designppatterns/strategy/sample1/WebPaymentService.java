package com.samples.designppatterns.strategy.sample1;

public class WebPaymentService implements PaymentService {
    private final int discount;

    public WebPaymentService(int discount) {
        this.discount = discount;
    }

    /**
     * @param amount : indirim yapılacak miktar
     *               indirim, toplam fiyattan büyükse toplam fiyattan
     *               indrim çıkarılarak finalPirice hesaplanır
     */
    @Override
    public void pay(int amount) {

        int finalPrice = amount;

        if (finalPrice > discount) {
            finalPrice = amount - discount;
        }

        System.out.println(String.format("Web üzerinden ödeme alındı. tutar : %s", finalPrice));
    }
}
