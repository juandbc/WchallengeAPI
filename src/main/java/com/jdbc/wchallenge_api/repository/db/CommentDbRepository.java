package com.jdbc.wchallenge_api.repository.db;

import com.jdbc.wchallenge_api.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public interface CommentDbRepository extends MongoRepository<Comment, Integer> {
}
