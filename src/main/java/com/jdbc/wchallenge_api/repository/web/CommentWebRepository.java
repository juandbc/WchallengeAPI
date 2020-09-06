package com.jdbc.wchallenge_api.repository.web;

import com.jdbc.wchallenge_api.model.Comment;
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
public class CommentWebRepository implements WebRepository<Comment> {

  private final WebClient webClient;

  public CommentWebRepository(@Value("${webServiceRepository}") String baseUrl, @Value("${commentsPath}") String path) {
    this.webClient = WebClient.create(baseUrl + path);
  }

  @Override
  public List<Comment> findAll() {
    Flux<Comment> result = getFlux(webClient, "", Comment.class);
    return result.collectList().block();
  }

  @Override
  public Comment findById(int id) {
    Mono<Comment> result = getMono(webClient, String.format("/%s", id), Comment.class);
    return result.block();
  }

  public List<Comment> findByName(String name) {
    String uriPath = String.format("?name=%s", name);

    Flux<Comment> result = webClient.get().uri(uriPath).header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToFlux(Comment.class);
    return result.collectList().block();
  }
}