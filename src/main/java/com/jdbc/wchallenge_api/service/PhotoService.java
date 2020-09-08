package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.Album;
import com.jdbc.wchallenge_api.model.Photo;
import com.jdbc.wchallenge_api.repository.db.PhotoDbRepository;
import com.jdbc.wchallenge_api.repository.web.PhotoWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Service
public class PhotoService {

  private final PhotoWebRepository photoWebRepository;
  private final PhotoDbRepository photoDbRepository;
  private final AlbumService albumService;
  private final MongoDatabaseFactory mongoDatabaseFactory;

  @Autowired
  public PhotoService(PhotoWebRepository photoWebRepository, PhotoDbRepository photoDbRepository, AlbumService albumService,
                      MongoDatabaseFactory mongoDatabaseFactory) {
    this.photoWebRepository = photoWebRepository;
    this.photoDbRepository = photoDbRepository;
    this.albumService = albumService;
    this.mongoDatabaseFactory = mongoDatabaseFactory;
  }

  public List<Photo> findAll() {
    List<Photo> photos = new ArrayList<>();
    photos.addAll(photoWebRepository.findAll());
    photos.addAll(photoDbRepository.findAll());
    return photos;
  }

  public Photo findById(int id) {
    Optional<Photo> optionalPhoto = photoDbRepository.findById(id);

    return optionalPhoto.orElse(photoWebRepository.findById(id));
  }

  public List<Photo> findByUser(int userId) {
    List<Integer> albums = albumService.findByUser(userId).stream().map(Album::getId).collect(Collectors.toList());
    List<Photo> photos = photoWebRepository.findAll()
            .stream()
            .filter(photo -> albums.contains(photo.getAlbumId()))
            .collect(Collectors.toList());

    List<AggregationOperation> list = new ArrayList<>();
    list.add(Aggregation.lookup("album", "albumId", "_id", "albums"));
    list.add(Aggregation.lookup("user", "albums.userId", "_id", "user"));
    list.add(Aggregation.match(Criteria.where("user._id").is(userId)));
    list.add(new ProjectionOperation().andExclude("albums", "user"));

    Aggregation agg = Aggregation.newAggregation(list);

    MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactory);
    AggregationResults<Photo> results = mongoTemplate.aggregate(agg, "photo", Photo.class);

    photos.addAll(results.getMappedResults());

    return photos;
  }

  public void insert(Photo photo) {
    photoDbRepository.insert(photo);
  }

  public void update(Photo photo) {
    photoDbRepository.save(photo);
  }
}
