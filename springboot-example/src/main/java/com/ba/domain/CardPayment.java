package com.ba.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CardPayment extends Payment {

    @Column
    private int cardNo;

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }
}
