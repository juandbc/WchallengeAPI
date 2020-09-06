package com.jdbc.wchallenge_api.repository.db;

import com.jdbc.wchallenge_api.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public interface AlbumDbRepository extends MongoRepository<Album, Integer> {

  @Query("{userId: ?0}")
  List<Album> findByUser(int userId);
}
