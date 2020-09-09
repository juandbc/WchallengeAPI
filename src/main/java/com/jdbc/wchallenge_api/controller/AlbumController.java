package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Album;
import com.jdbc.wchallenge_api.model.AlbumPermission;
import com.jdbc.wchallenge_api.model.ErrorResponse;
import com.jdbc.wchallenge_api.service.AlbumPermissionService;
import com.jdbc.wchallenge_api.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  @GetMapping
  @Operation(summary = "Find albums")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found albums",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Album.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<Album> findAll() {
    return albumService.findAll();
  }

  @GetMapping(params = {"userId"})
  @Operation(summary = "Find albums by user")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found albums user",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Album.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<Album> findByUser(@RequestParam int userId) {
    return albumService.findByUser(userId);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Find albums by its id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found album",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Album.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public Album findById(@PathVariable int id) {
    return albumService.findById(id);
  }

  @GetMapping("/{id}/permissions")
  @Operation(summary = "Find albums permissions")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found permissions",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AlbumPermission.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<AlbumPermission> findByAlbum(@PathVariable int id) {
    return albumPermissionService.findByAlbum(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new album")
  @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Album created", content = @Content),
          @ApiResponse(responseCode = "400", description = "Missing or malformed request body",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public void insertAlbum(@RequestBody Album album) {
    albumService.insert(album);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update an album")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Album created", content = @Content),
          @ApiResponse(responseCode = "204", description = "Album updated", content = @Content),
          @ApiResponse(responseCode = "400", description = "Missing or malformed request body",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
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
