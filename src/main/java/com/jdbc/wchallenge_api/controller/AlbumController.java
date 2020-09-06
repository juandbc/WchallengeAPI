package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Album;
import com.jdbc.wchallenge_api.service.AlbumService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<List<Album>> findByUser(@RequestParam int userId) {
    List<Album> body = albumService.findByUser(userId);

    if (body.isEmpty())
      return ResponseEntity.notFound().build();
    else
      return ResponseEntity.ok(body);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Album> findById(@PathVariable int id) {
    Album album = albumService.findById(id);

    if (album.getTitle() == null)
      return ResponseEntity.notFound().build();
    else
      return ResponseEntity.ok(album);
  }
}
