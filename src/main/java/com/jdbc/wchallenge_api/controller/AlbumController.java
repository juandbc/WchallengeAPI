package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Album;
import com.jdbc.wchallenge_api.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@RestController
@RequestMapping({"/albums", "/"})
public class AlbumController {

  @Autowired
  private AlbumService albumService;

  @GetMapping()
  public List<Album> findAll() { return albumService.findAll(); }

  @GetMapping(params = {"userId"})
  public List<Album> findByUser(@RequestParam(required = false) String userId) {
    if (userId == null || userId.isEmpty())
      return Collections.emptyList();
    else
      return albumService.findByUser(Integer.parseInt(userId));
  }

  @GetMapping("/{id}")
  public Album findById(@PathVariable int id) {
    return albumService.findById(id);
  }
}
