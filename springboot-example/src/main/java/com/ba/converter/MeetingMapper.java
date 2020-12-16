package com.ba.converter;

import com.ba.domain.Meeting;
import com.ba.dto.MeetingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeetingMapper {

    MeetingMapper INSTANCE = Mappers.getMapper(MeetingMapper.class);

    @Mapping(source = "toplantilar", target = "meetings")
    Meeting toEntity(MeetingDTO dto);

    @Mapping(source = "meetings", target = "toplantilar")
    MeetingDTO toDTO(Meeting entity);

}
