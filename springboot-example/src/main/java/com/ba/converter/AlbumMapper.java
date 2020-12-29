package com.ba.converter;

import com.ba.domain.Album;
import com.ba.dto.AlbumDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

  AlbumDTO toDTO(Album album);

  Album toEntity(AlbumDTO dto);
}
