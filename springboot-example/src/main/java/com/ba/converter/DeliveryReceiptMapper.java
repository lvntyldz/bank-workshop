package com.ba.converter;

import com.ba.domain.DeliveryReceipt;
import com.ba.dto.DeliveryReceiptDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryReceiptMapper {

    DeliveryReceiptMapper INSTANCE = Mappers.getMapper(DeliveryReceiptMapper.class);

    @Mapping(source = "productShipment.sellerFee", target = "productShipmentDTO.saticiOder")
    DeliveryReceiptDTO toDTO(DeliveryReceipt entity);

    @Mapping(source = "productShipmentDTO.saticiOder", target = "productShipment.sellerFee")
    DeliveryReceipt toEntity(DeliveryReceiptDTO dto);
}
