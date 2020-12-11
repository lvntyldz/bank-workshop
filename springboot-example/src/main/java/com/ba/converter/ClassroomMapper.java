package com.ba.converter;

import com.ba.domain.Classroom;
import com.ba.dto.ClassroomDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClassroomMapper {
    @Mapping(source = "name", target = "isim")
    @Mapping(source = "no", target = "roomNo")
    ClassroomDTO toDto(Classroom entity);

    @Mapping(source = "isim", target = "name")
    @Mapping(source = "roomNo", target = "no")
    Classroom toEntity(ClassroomDTO dto);
}
