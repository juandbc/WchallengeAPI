package com.jdbc.wchallenge_api.repository;

import com.jdbc.wchallenge_api.model.Photo;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public interface PhotoRepository {

  List<Photo> findAll();
  Photo findById(int id);
}
