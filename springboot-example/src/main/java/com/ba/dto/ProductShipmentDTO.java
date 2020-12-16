package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductShipmentDTO {

    private Long id;

    private Long productId;

    private String cargoFollowUrl;

    private Boolean saticiOder;

}
