package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Album;
import com.jdbc.wchallenge_api.model.AlbumPermission;
import com.jdbc.wchallenge_api.service.AlbumPermissionService;
import com.jdbc.wchallenge_api.service.AlbumService;
import org.springframework.http.HttpStatus;
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
  private final AlbumPermissionService albumPermissionService;

  public AlbumController(AlbumService albumService, AlbumPermissionService albumPermissionService) {
    this.albumService = albumService;
    this.albumPermissionService = albumPermissionService;
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

  @GetMapping("/{id}/permissions")
  public List<AlbumPermission> findByAlbum(@PathVariable int id) {
    return albumPermissionService.findByAlbum(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void insertAlbum(@RequestBody Album album) {
    albumService.insert(album);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateAlbum(@PathVariable int id, @RequestBody Album album) {
    Album a = albumService.findById(id);

    if (a.getId() != 0) {
      albumService.update(album);
      return ResponseEntity.noContent().build();
    } else {
      albumService.insert(album);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
  }


}
