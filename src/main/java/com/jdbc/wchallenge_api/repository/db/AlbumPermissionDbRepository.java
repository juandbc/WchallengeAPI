package com.jdbc.wchallenge_api.repository.db;

import com.jdbc.wchallenge_api.model.AlbumPermission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public interface AlbumPermissionDbRepository extends MongoRepository<AlbumPermission, Integer> {

  @Query("{albumId: ?0}")
  List<AlbumPermission> findByAlbum(int id);
}
