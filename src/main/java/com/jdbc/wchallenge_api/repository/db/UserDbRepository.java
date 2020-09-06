package com.jdbc.wchallenge_api.repository.db;

import com.jdbc.wchallenge_api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public interface UserDbRepository extends MongoRepository<User, Integer> {
}
