package com.ba.converter;

import com.ba.domain.SystemUser;
import com.ba.dto.SystemUserDTO;
import com.ba.dto.SystemUserDTOTr;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemUserMapper {

    SystemUserMapper INSTANCE = Mappers.getMapper(SystemUserMapper.class);

    SystemUserDTO toDTO(SystemUser systemUser);

    @Mappings({
            @Mapping(source = "telefon", target = "phone"),
            @Mapping(source = "sifre", target = "password")
    })
    SystemUser toEntity(SystemUserDTOTr dto);

}
