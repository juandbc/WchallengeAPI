package com.jdbc.wchallenge_api.repository.web;

import com.jdbc.wchallenge_api.model.Photo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Repository
public class PhotoWebRepository implements WebRepository<Photo> {

  private final WebClient webClient;

  public PhotoWebRepository(@Value("${webServiceRepository}") String baseUrl, @Value("${photosPath}") String path) {
    this.webClient = WebClient.create(baseUrl + path);
  }

  @Override
  public List<Photo> findAll() {
    Flux<Photo> result = getFlux(webClient, "", Photo.class);
    return result.collectList().block();
  }

  @Override
  public Photo findById(int id) {
    Mono<Photo> result = getMono(webClient, String.format("/%s", id), Photo.class);
    return result.block();
  }
}
