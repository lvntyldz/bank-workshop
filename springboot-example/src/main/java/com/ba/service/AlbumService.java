package com.ba.service;

import com.ba.converter.AlbumMapper;
import com.ba.domain.Album;
import com.ba.dto.AlbumDTO;
import com.ba.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

  @Autowired private AlbumRepository repository;

  @Autowired private AlbumMapper mapper;

  public List<Album> getWholeAlbum() {
    return repository.findAll();
  }

  public Album addNewAlbum(AlbumDTO dto) {

    Album album = mapper.toEntity(dto);

    return repository.save(album);
  }
}
