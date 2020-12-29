package com.ba.controller;

import com.ba.domain.Album;
import com.ba.dto.AlbumDTO;
import com.ba.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

  @Autowired private AlbumService service;

  @GetMapping
  public List<Album> getWholeAlbum() {
    return service.getWholeAlbum();
  }

  @PostMapping
  public Album addNewAlbum(@Valid @RequestBody AlbumDTO dto) {
    return service.addNewAlbum(dto);
  }
}
