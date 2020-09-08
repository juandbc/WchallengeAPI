package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.AlbumPermission;
import com.jdbc.wchallenge_api.service.AlbumPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/albums/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlbumPermissionController {

  private final AlbumPermissionService albumPermissionService;

  @Autowired
  public AlbumPermissionController(AlbumPermissionService albumPermissionService) {
    this.albumPermissionService = albumPermissionService;
  }

  @GetMapping
  public List<AlbumPermission> findAll() {
    return albumPermissionService.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void insert(@RequestBody AlbumPermission albumPermission) {
    albumPermissionService.insert(albumPermission);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateAlbum(@PathVariable int id, @RequestBody AlbumPermission albumPermission) {
    AlbumPermission a = albumPermissionService.findById(id);

    if (a != null) {
      albumPermissionService.update(albumPermission);
      return ResponseEntity.noContent().build();
    } else {
      albumPermissionService.insert(albumPermission);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
  }
}
