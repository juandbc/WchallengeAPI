package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.Album;
import com.jdbc.wchallenge_api.repository.db.AlbumDbRepository;
import com.jdbc.wchallenge_api.repository.web.AlbumWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Service
public class AlbumService {

  private final AlbumDbRepository albumDbRepository;
  private final AlbumWebRepository albumWebRepository;

  @Autowired
  public AlbumService(AlbumWebRepository albumWebRepository, AlbumDbRepository albumDbRepository) {
    this.albumDbRepository = albumDbRepository;
    this.albumWebRepository = albumWebRepository;
  }

  public List<Album> findAll() {
    List<Album> albums = new ArrayList<>();

    albums.addAll(albumWebRepository.findAll());
    albums.addAll(albumDbRepository.findAll());

    return albums;
  }

  public Album findById(int id) {
    Optional<Album> optionalAlbum = albumDbRepository.findById(id);

    return optionalAlbum.orElseGet(() -> albumWebRepository.findById(id));
  }

  public List<Album> findByUser(int userId) {
    List<Album> albums = new ArrayList<>();

    albums.addAll(albumWebRepository.findByUser(userId));
    albums.addAll(albumDbRepository.findByUser(userId));

    return albums;
  }
}
