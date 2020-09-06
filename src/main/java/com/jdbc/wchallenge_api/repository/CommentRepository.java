package com.jdbc.wchallenge_api.repository;

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
public class CommentRepository extends WebRepository {

  private final WebClient webClient;

  public CommentRepository(@Value("${webServiceRepository}") String baseUrl, @Value("${commentsPath}") String path) {
    this.webClient = WebClient.create(baseUrl + path);
  }

  public List<Comment> findAll() {
    Flux<Comment> result = (Flux<Comment>) getFlux(webClient, "", Comment.class);
    return result.collectList().block();
  }

  public Comment findById(int id) {
    Mono<Comment> result = (Mono<Comment>) getMono(webClient, String.format("/%s", id), Comment.class);
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