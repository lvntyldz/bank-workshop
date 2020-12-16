package com.ba.converter;

import com.ba.domain.Campus;
import com.ba.dto.CampusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ClassroomMapper.class})
public interface CampusMapper {

    CampusMapper INSTANCE = Mappers.getMapper(CampusMapper.class);

    @Mapping(source = "name", target = "isim")
    @Mapping(source = "phone", target = "telefon")
    @Mapping(source = "email", target = "eposta")
    @Mapping(source = "classrooms", target = "siniflar")
    CampusDTO toDto(Campus entity);

    @Mappings({
            @Mapping(source = "isim", target = "name"),
            @Mapping(source = "telefon", target = "phone"),
            @Mapping(source = "eposta", target = "email"),
            @Mapping(source = "siniflar", target = "classrooms")
    })
    Campus toEntity(CampusDTO dto);
}
