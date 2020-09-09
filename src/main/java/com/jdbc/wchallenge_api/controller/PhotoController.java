package com.jdbc.wchallenge_api.controller;

import com.jdbc.wchallenge_api.model.ErrorResponse;
import com.jdbc.wchallenge_api.model.Photo;
import com.jdbc.wchallenge_api.service.PhotoService;
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
@RequestMapping(value = "/photos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PhotoController {

  private final PhotoService photoService;

  @Autowired
  public PhotoController(PhotoService photoService) {
    this.photoService = photoService;
  }

  @GetMapping
  @Operation(summary = "Find photos")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found photos",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Photo.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public List<Photo> findAll() {
    return photoService.findAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Find photos by its id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found photo",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Photo.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public Photo findById(@PathVariable int id) {
    return photoService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new photo")
  @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Photo created", content = @Content),
          @ApiResponse(responseCode = "400", description = "Missing or malformed request body",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
  public void insertPhoto(@RequestBody Photo photo) {
    photoService.insert(photo);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update photo")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Photo created", content = @Content),
          @ApiResponse(responseCode = "204", description = "Photo upated", content = @Content),
          @ApiResponse(responseCode = "400", description = "Missing or malformed request body",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))}),
          @ApiResponse(responseCode = "500", description = "Internal Server Error",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))})
  })
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
