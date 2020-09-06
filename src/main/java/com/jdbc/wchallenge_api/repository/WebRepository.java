package com.jdbc.wchallenge_api.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
abstract class WebRepository {

  protected Object getMono(WebClient webClient, String uri, Class<?> elementClass) {
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

  protected Object getFlux(WebClient webClient, String uri, Class<?> elementClass) {
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
