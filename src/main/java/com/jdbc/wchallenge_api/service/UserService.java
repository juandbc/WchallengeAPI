package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.User;
import com.jdbc.wchallenge_api.repository.db.UserDbRepository;
import com.jdbc.wchallenge_api.repository.web.UserWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Service
public class UserService {

  private final UserWebRepository userWebRepository;
  private final UserDbRepository userDbRepository;
  private final MongoDatabaseFactory mongoDatabaseFactory;

  @Autowired
  public UserService(UserWebRepository userRepository, UserDbRepository userDbRepository, MongoDatabaseFactory mongoDatabaseFactory) {
    this.userWebRepository = userRepository;
    this.userDbRepository = userDbRepository;
    this.mongoDatabaseFactory = mongoDatabaseFactory;
  }

  public List<User> findAll() {
    List<User> users = new ArrayList<>();
    users.addAll(userWebRepository.findAll());
    users.addAll(userDbRepository.findAll());
    return users;
  }

  public List<User> findUsersByAlbumPermission(int albumId, String permission) {
    String permissionField = "";
    if (permission.equals("r"))
      permissionField = "permission.read";
    else if (permission.equals("w"))
      permissionField = "permission.write";
    else
      return Collections.emptyList();

    List<AggregationOperation> list = new ArrayList<>();
    list.add(Aggregation.lookup("album_permission", "_id", "userId", "permission"));
    list.add(Aggregation.match(Criteria.where("permission.albumId").is(albumId).and(permissionField).is(true)));
    list.add(new ProjectionOperation().andExclude("permission"));

    Aggregation agg = Aggregation.newAggregation(list);

    MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactory);
    AggregationResults<User> results = mongoTemplate.aggregate(agg, "user", User.class);

    return results.getMappedResults();
  }

  public User findById(int id) {
    Optional<User> optionalUser = userDbRepository.findById(id);

    return optionalUser.orElse(userWebRepository.findById(id));
  }

  public void insert(User user) {
    try {
      userDbRepository.insert(user);
    } catch (DuplicateKeyException ex) {
      throw new DuplicateKeyException("Duplicated id: " + user.getId());
    }
  }

  public void update(User user) {
    userDbRepository.save(user);
  }
}
