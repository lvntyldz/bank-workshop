package com.ba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PRODUCT_SHIPMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private String cargoFollowUrl;

    @Column(name = "IS_SELLER_FEE")
    private Boolean sellerFee;

}
