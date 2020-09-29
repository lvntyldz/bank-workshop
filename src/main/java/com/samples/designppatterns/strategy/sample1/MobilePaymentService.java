package com.samples.designppatterns.strategy.sample1;

public class MobilePaymentService implements PaymentService {

    private final int discountRatio;

    public MobilePaymentService(int discountRatio) {
        this.discountRatio = discountRatio;
    }

    /**
     * @param amount : indirim yapılacak miktar
     *               indirim, toplam fiyattan büyükse
     *               toplam fiyattan indirim oranında eksiltme yapılır.
     */
    @Override
    public void pay(int amount) {

        int finalPrice = amount;

        if (amount > 0 && discountRatio > 0) {
            finalPrice = amount - (amount / 100 * discountRatio);
        }

        System.out.println(String.format("Mobile üzerinden ödeme alındı. tutar : %s", finalPrice));
    }
}
