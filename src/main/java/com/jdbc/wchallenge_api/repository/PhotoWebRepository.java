package com.jdbc.wchallenge_api.repository;

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
public class PhotoWebRepository extends WebRepository implements PhotoRepository {

  private final WebClient webClient;

  public PhotoWebRepository(@Value("${webServiceRepository}") String baseUrl, @Value("${photosPath}") String path) {
    this.webClient = WebClient.create(baseUrl + path);
  }

  @Override
  public List<Photo> findAll() {
    Flux<Photo> result = (Flux<Photo>) getFlux(webClient, "", Photo.class);
    return result.collectList().block();
  }

  @Override
  public Photo findById(int id) {
    Mono<Photo> result = (Mono<Photo>) getMono(webClient, String.format("/%s", id), Photo.class);
    return result.block();
  }
}
