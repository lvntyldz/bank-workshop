package com.ba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_DELIVERY_RECEIPT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeliveryReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String receiptNumber;

    private String receiver;

    private Date deliveryDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_SHIPMENT_ID")
    private ProductShipment productShipment;

}
