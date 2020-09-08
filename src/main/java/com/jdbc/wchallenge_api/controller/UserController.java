package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Album;
import com.jdbc.wchallenge_api.model.Comment;
import com.jdbc.wchallenge_api.model.Photo;
import com.jdbc.wchallenge_api.model.User;
import com.jdbc.wchallenge_api.service.AlbumService;
import com.jdbc.wchallenge_api.service.CommentService;
import com.jdbc.wchallenge_api.service.PhotoService;
import com.jdbc.wchallenge_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  private final AlbumService albumService;
  private final UserService userService;
  private final PhotoService photoService;
  private final CommentService commentService;

  @Autowired
  public UserController(AlbumService albumService, UserService userService, PhotoService photoService, CommentService commentService) {
    this.albumService = albumService;
    this.userService = userService;
    this.photoService = photoService;
    this.commentService = commentService;
  }

  @GetMapping
  public List<User> findAll() {
    return userService.findAll();
  }


  @GetMapping("/{id}")
  public User findById(@PathVariable(name = "id") int id) {
    return userService.findById(id);
  }

  @GetMapping("/{id}/albums")
  public List<Album> findUserAlbums(@PathVariable int id) {
    return albumService.findByUser(id);
  }

  @GetMapping("/{id}/comments")
  public List<Comment> findUserComments(@PathVariable int id) {
    return commentService.findCommentsByUser(id);
  }

  @GetMapping("/{id}/photos")
  public List<Photo> findUserPhotos(@PathVariable int id) {
    return photoService.findByUser(id);
  }

  @GetMapping(value = "/albums/{albumId}", params = "permission")
  public List<User> findUsersByAlbumPermission(@PathVariable int albumId, @RequestParam String permission) {
    return userService.findUsersByAlbumPermission(albumId, permission);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void insertUser(@RequestBody User user) {
    userService.insert(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody @Validated User user) {
    User u = userService.findById(id);

    if (u.getId() != 0) {
      userService.update(user);
      return ResponseEntity.noContent().build();
    } else {
      userService.insert(user);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
  }
}
