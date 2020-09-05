package com.jdbc.wchallenge_api.repository;

import com.jdbc.wchallenge_api.model.Album;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public interface AlbumRepository {

  List<Album> findAll();
  Album findById(int id);
  List<Album> findByUser(int user);
}
