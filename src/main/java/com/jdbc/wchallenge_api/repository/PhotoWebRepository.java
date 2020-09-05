package com.jdbc.wchallenge_api.repository;

import com.jdbc.wchallenge_api.model.Photo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
public class PhotoWebRepository implements PhotoRepository {

  private final WebClient webClient;

  private static final String PATH = "/photos";

  public PhotoWebRepository(@Value("${webServiceRepository}") String baseUrl){
    this.webClient = WebClient.create(baseUrl);
  }

  @Override
  public List<Photo> findAll() {
    Flux<Photo> result = webClient.get().uri(PATH).header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToFlux(Photo.class);
    return result.collectList().block();
  }

  @Override
  public Photo findById(int id) {
    Mono<Photo> result = webClient.get().uri(PATH + "/{id}", id)
            .retrieve()
            .bodyToMono(Photo.class);
    return result.block();
  }
}
