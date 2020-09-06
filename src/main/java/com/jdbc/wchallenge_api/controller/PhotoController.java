package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Photo;
import com.jdbc.wchallenge_api.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public Photo findById(@PathVariable int id)  {
    return photoService.findById(id);
  }
}
