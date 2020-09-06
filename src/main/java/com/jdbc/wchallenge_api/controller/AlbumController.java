package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Album;
import com.jdbc.wchallenge_api.service.AlbumService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/albums", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlbumController {

  private final AlbumService albumService;

  public AlbumController(AlbumService albumService) {
    this.albumService = albumService;
  }

  @GetMapping()
  public List<Album> findAll() {
    return albumService.findAll();
  }

  @GetMapping(params = {"userId"})
  public List<Album> findByUser(@RequestParam int userId) {
    return albumService.findByUser(userId);
  }

  @GetMapping("/{id}")
  public Album findById(@PathVariable int id) {
    return albumService.findById(id);
  }
}
