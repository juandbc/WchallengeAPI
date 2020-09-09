package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.*;
import com.jdbc.wchallenge_api.service.AlbumService;
import com.jdbc.wchallenge_api.service.CommentService;
import com.jdbc.wchallenge_api.service.PhotoService;
import com.jdbc.wchallenge_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
  @Operation(summary = "Find users")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found users",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<User> findAll() {
    return userService.findAll();
  }


  @GetMapping("/{id}")
  @Operation(summary = "Find user by its id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found user",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public User findById(@PathVariable(name = "id") int id) {
    return userService.findById(id);
  }

  @GetMapping("/{id}/albums")
  @Operation(summary = "Find user's albums")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found albums",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Album.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<Album> findUserAlbums(@PathVariable int id) {
    return albumService.findByUser(id);
  }

  @GetMapping("/{id}/comments")
  @Operation(summary = "Find user's comments")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found comments",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Comment.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<Comment> findUserComments(@PathVariable int id) {
    return commentService.findCommentsByUser(id);
  }

  @GetMapping("/{id}/photos")
  @Operation(summary = "Find user's photos")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found photos",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Photo.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<Photo> findUserPhotos(@PathVariable int id) {
    return photoService.findByUser(id);
  }

  @GetMapping(value = "/albums/{albumId}", params = "permission")
  @Operation(summary = "Find for users with permissions in an album")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found users",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<User> findUsersByAlbumPermission(@PathVariable int albumId, @RequestParam String permission) {
    return userService.findUsersByAlbumPermission(albumId, permission);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new user")
  @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "User created", content = @Content),
          @ApiResponse(responseCode = "400", description = "Missing or malformed request body",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public void insertUser(@RequestBody User user) {
    userService.insert(user);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update user")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "User created", content = @Content),
          @ApiResponse(responseCode = "204", description = "User upated", content = @Content),
          @ApiResponse(responseCode = "400", description = "Missing or malformed request body",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody User user) {
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
