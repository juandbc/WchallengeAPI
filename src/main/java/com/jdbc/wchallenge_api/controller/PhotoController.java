package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Photo;
import com.jdbc.wchallenge_api.service.PhotoService;
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
@RequestMapping(value = "/photos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PhotoController {

  private final PhotoService photoService;

  @Autowired
  public PhotoController(PhotoService photoService) {
    this.photoService = photoService;
  }

  @GetMapping
  public List<Photo> findAll() {
    return photoService.findAll();
  }

  @GetMapping("/{id}")
  public Photo findById(@PathVariable int id) {
    return photoService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void insertPhoto(@RequestBody Photo photo) {
    photoService.insert(photo);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updatePhoto(@PathVariable int id, @RequestBody Photo photo) {
    Photo p = photoService.findById(id);

    if (p.getId() != 0) {
      photoService.update(photo);
      return ResponseEntity.noContent().build();
    } else {
      photoService.insert(photo);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
  }
}
