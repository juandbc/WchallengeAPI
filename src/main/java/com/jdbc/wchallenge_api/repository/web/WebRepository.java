package com.jdbc.wchallenge_api.repository.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
interface WebRepository<T> {

  List<T> findAll();

  T findById(int id);

  default Mono<T> getMono(WebClient webClient, String uri, Class<T> elementClass) {
    return webClient.get().uri(uri)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, response -> {
              if (response.statusCode() == HttpStatus.NOT_FOUND)
                return Mono.empty();
              else
                return Mono.error(new ResponseStatusException(response.statusCode()));
            })
            .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new ResponseStatusException(response.statusCode())))
            .bodyToMono(elementClass);
  }

  default Flux<T> getFlux(WebClient webClient, String uri, Class<T> elementClass) {
    return webClient.get().uri(uri)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, response -> {
              if (response.statusCode() == HttpStatus.NOT_FOUND)
                return Mono.empty();
              else
                return Mono.error(new ResponseStatusException(response.statusCode()));
            })
            .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new ResponseStatusException(response.statusCode())))
            .bodyToFlux(elementClass);
  }
}
