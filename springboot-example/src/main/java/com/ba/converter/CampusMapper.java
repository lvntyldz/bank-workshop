package com.ba.converter;

import com.ba.domain.Campus;
import com.ba.dto.CampusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ClassroomMapper.class})
public interface CampusMapper {
    @Mapping(source = "name", target = "isim")
    @Mapping(source = "phone", target = "telefon")
    @Mapping(source = "email", target = "eposta")
    @Mapping(source = "classrooms", target = "siniflar")
    CampusDTO toDto(Campus entity);

    @Mapping(source = "isim", target = "name")
    @Mapping(source = "telefon", target = "phone")
    @Mapping(source = "eposta", target = "email")
    @Mapping(source = "siniflar", target = "classrooms")
    Campus toEntity(CampusDTO dto);
}
