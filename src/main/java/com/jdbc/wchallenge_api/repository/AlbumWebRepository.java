package com.jdbc.wchallenge_api.repository;

import com.jdbc.wchallenge_api.model.Album;
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
public class AlbumWebRepository implements AlbumRepository {

  private final WebClient webClient;

  private static final String PATH = "/albums";

  public AlbumWebRepository(@Value("${webServiceRepository}") String baseUrl) {
    this.webClient = WebClient.create(baseUrl);
  }

  @Override
  public List<Album> findAll() {
    Flux<Album> result = webClient.get().uri(PATH).header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToFlux(Album.class);
    return result.collectList().block();
  }

  @Override
  public Album findById(int id) {
    Mono<Album> result = webClient.get().uri(PATH + "/{id}", id)
            .retrieve()
            .bodyToMono(Album.class);
    return result.block();
  }

  @Override
  public List<Album> findByUser(int user) {
    String uriPath = String.format("%s?userId=%d", PATH, user);

    Flux<Album> result = webClient.get().uri(uriPath).header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToFlux(Album.class);
    return result.collectList().block();
  }
}
