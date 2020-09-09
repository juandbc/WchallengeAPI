package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.ErrorResponse;
import com.jdbc.wchallenge_api.model.Post;
import com.jdbc.wchallenge_api.service.PostService;
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
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

  private final PostService postService;

  @Autowired
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  @Operation(summary = "Find posts")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found posts",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Post.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<Post> findAll() {
    return postService.findAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Find posts by its id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found post",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Post.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public Post findById(@PathVariable int id) {
    return postService.findById(id);
  }

  @GetMapping(params = {"userId"})
  @Operation(summary = "Find posts made by a user")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found posts",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Post.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<Post> findByUser(@RequestParam int userId) {
    return postService.findByUser(userId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new post")
  @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Post created", content = @Content),
          @ApiResponse(responseCode = "400", description = "Missing or malformed request body",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public void insertPost(@RequestBody Post post) {
    postService.insert(post);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update post")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Post created", content = @Content),
          @ApiResponse(responseCode = "204", description = "Post upated", content = @Content),
          @ApiResponse(responseCode = "400", description = "Missing or malformed request body",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public ResponseEntity<Void> updatePost(@PathVariable int id, @RequestBody Post post) {
    Post u = postService.findById(id);

    if (u.getId() != 0) {
      postService.update(post);
      return ResponseEntity.noContent().build();
    } else {
      postService.insert(post);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
  }
}
