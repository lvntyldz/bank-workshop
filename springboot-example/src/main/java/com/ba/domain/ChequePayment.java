package com.ba.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ChequePayment extends Payment {

    @Column
    private int chequeNo;

    public int getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(int chequeNo) {
        this.chequeNo = chequeNo;
    }
}
