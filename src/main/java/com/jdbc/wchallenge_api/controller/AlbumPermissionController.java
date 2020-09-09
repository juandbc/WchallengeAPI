package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.Album;
import com.jdbc.wchallenge_api.model.AlbumPermission;
import com.jdbc.wchallenge_api.model.ErrorResponse;
import com.jdbc.wchallenge_api.service.AlbumPermissionService;
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
@RequestMapping(value = "/albums/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlbumPermissionController {

  private final AlbumPermissionService albumPermissionService;

  @Autowired
  public AlbumPermissionController(AlbumPermissionService albumPermissionService) {
    this.albumPermissionService = albumPermissionService;
  }

  @GetMapping
  @Operation(summary = "Find albums permissions")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found albums",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Album.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<AlbumPermission> findAll() {
    return albumPermissionService.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new permission")
  @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Permission created", content = @Content),
          @ApiResponse(responseCode = "400", description = "Missing or malformed request body",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public void insert(@RequestBody AlbumPermission albumPermission) {
    albumPermissionService.insert(albumPermission);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a new permission")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Permission created", content = @Content),
          @ApiResponse(responseCode = "204", description = "Permission upated", content = @Content),
          @ApiResponse(responseCode = "400", description = "Missing or malformed request body",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
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
