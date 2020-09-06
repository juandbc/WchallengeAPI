package com.jdbc.wchallenge_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@SpringBootApplication
@EnableMongoRepositories("com.jdbc.wchallenge_api.repository.db")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
