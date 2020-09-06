package com.jdbc.wchallenge_api.repository;

import com.jdbc.wchallenge_api.model.Album;
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
public class AlbumWebRepository extends WebRepository implements AlbumRepository {

  private final WebClient webClient;

  public AlbumWebRepository(@Value("${webServiceRepository}") String baseUrl, @Value("${albumsPath}") String path) {
    this.webClient = WebClient.builder().baseUrl(baseUrl + path).build();
  }

  @Override
  public List<Album> findAll() {
    Flux<Album> result = (Flux<Album>) getFlux(webClient, "", Album.class);
    return result.collectList().block();
  }

  @Override
  public Album findById(int id) {
    Mono<Album> result = (Mono<Album>) getMono(webClient, String.format("/%s", id), Album.class);
    return result.block();
  }

  @Override
  public List<Album> findByUser(int user) {
    String uriPath = String.format("?userId=%d", user);

    Flux<Album> result = (Flux<Album>) getFlux(webClient, uriPath, Album.class);
    return result.collectList().block();
  }
}
