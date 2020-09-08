package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.AlbumPermission;
import com.jdbc.wchallenge_api.repository.db.AlbumPermissionDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Service
public class AlbumPermissionService {

  private final AlbumPermissionDbRepository albumPermissionDbRepository;

  @Autowired
  public AlbumPermissionService(AlbumPermissionDbRepository albumPermissionDbRepository) {
    this.albumPermissionDbRepository = albumPermissionDbRepository;
  }

  public AlbumPermission findById(int id) {
    return albumPermissionDbRepository.findById(id).orElse(null);
  }

  public List<AlbumPermission> findByAlbum(int id) {
    return albumPermissionDbRepository.findByAlbum(id);
  }

  public void insert(AlbumPermission albumPermission) {
    albumPermissionDbRepository.insert(albumPermission);
  }

  public void update(AlbumPermission albumPermission) {
    albumPermissionDbRepository.save(albumPermission);
  }

  public List<AlbumPermission> findAll() {
    return albumPermissionDbRepository.findAll();
  }
}
