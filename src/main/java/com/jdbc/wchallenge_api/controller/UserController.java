package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Album;
import com.jdbc.wchallenge_api.model.User;
import com.jdbc.wchallenge_api.service.AlbumService;
import com.jdbc.wchallenge_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/users")
public class UserController {

  private final AlbumService albumService;
  private final UserService userService;

  @Autowired
  public UserController(AlbumService albumService, UserService userService) {
    this.albumService = albumService;
    this.userService = userService;
  }

  @GetMapping
  public List<User> findAll() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public User findById(@PathVariable int id) {
    return userService.findById(id);
  }

  @GetMapping("/{id}/albums")
  public List<Album> findUserAlbums(@PathVariable int id) {
    return albumService.findByUser(id);
  }
}
