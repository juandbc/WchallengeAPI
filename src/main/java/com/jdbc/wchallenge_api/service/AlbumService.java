package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.Album;
import com.jdbc.wchallenge_api.repository.AlbumRepository;
import com.jdbc.wchallenge_api.repository.AlbumWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Service
public class AlbumService {

  private final AlbumRepository albumRepository;

  @Autowired
  public AlbumService(AlbumWebRepository albumRepository) {
    this.albumRepository = albumRepository;
  }

  public List<Album> findAll() {
    return albumRepository.findAll();
  }

  public Album findById(int id) {
    return albumRepository.findById(id);
  }

  public List<Album> findByUser(int user) {
    return albumRepository.findByUser(user);
  }
}
