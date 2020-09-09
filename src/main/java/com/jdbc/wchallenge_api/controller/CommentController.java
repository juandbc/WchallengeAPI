package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Comment;
import com.jdbc.wchallenge_api.model.ErrorResponse;
import com.jdbc.wchallenge_api.service.CommentService;
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

import java.util.Collections;
import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

  private final CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping
  @Operation(summary = "Find comments")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found comments",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Comment.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<Comment> findAll() {
    return commentService.findAll();
  }

  @GetMapping(params = "name")
  @Operation(summary = "Find comments by its name")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found comments",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Comment.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<Comment> findsByName(@RequestParam String name) {
    if (name != null && !name.isEmpty())
      return commentService.findByName(name);
    else
      return Collections.emptyList();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Find comments by its id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found comment",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Comment.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public Comment findById(@PathVariable int id) {
    return commentService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new comment")
  @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Comment created", content = @Content),
          @ApiResponse(responseCode = "400", description = "Missing or malformed request body",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public void insertComment(@RequestBody Comment comment) {
    commentService.insert(comment);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update comment")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Comment created", content = @Content),
          @ApiResponse(responseCode = "204", description = "Comment upated", content = @Content),
          @ApiResponse(responseCode = "400", description = "Missing or malformed request body",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public ResponseEntity<Void> updateComment(@PathVariable int id, @RequestBody Comment comment) {
    Comment u = commentService.findById(id);

    if (u.getId() != 0) {
      commentService.update(comment);
      return ResponseEntity.noContent().build();
    } else {
      commentService.insert(comment);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
  }
}
