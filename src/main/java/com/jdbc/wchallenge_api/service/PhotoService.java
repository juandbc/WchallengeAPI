package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.Photo;
import com.jdbc.wchallenge_api.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Service
public class PhotoService {

  @Autowired
  private PhotoRepository photoRepository;

  public List<Photo> findAll() {
    return photoRepository.findAll();
  }

  public Photo findById(int id) {
    return photoRepository.findById(id);
  }

}
