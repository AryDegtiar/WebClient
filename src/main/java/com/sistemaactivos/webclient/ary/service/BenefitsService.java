package com.sistemaactivos.webclient.ary.service;

import com.sistemaactivos.webclient.ary.model.Benefits;
import com.sistemaactivos.webclient.ary.model.Costumer;
import com.sistemaactivos.webclient.ary.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class BenefitsService implements IBenefitsService {

    @Autowired
    WebClient webClientBenefits;

    @Override
    public Flux<Benefits> findAll() {
        return webClientBenefits.get()
                .uri("/benefits")
                .retrieve()
                .bodyToFlux(Benefits.class)
                .timeout(Duration.ofMillis(10_000));
    }

    @Override
    public Mono<Benefits> findByID(Integer id) {
        return webClientBenefits.get()
                .uri("/benefits/{id}", id)
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(errorResponse -> Mono.error(
                                        new RuntimeException(errorResponse.getStatus() + ": " + errorResponse.getMessage()))))
                .bodyToMono(Benefits.class)
                .timeout(Duration.ofMillis(10_000))
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontró el recurso")));
    }

    @Override
    public Flux<Benefits> getCostumers(int pageSize, int pageNumber) {
        int offset = pageSize * pageNumber;
        return webClientBenefits.get()
                .uri("/benefits?page={pageNumber}&size={pageSize}", pageNumber, pageSize)
                .retrieve()
                .bodyToFlux(Benefits.class)
                .skip(offset)
                .take(pageSize);
    }

    @Override
    public Mono<Benefits> create(Benefits benefits) {
        return webClientBenefits.post()
                .uri("/benefits")
                .body(Mono.just(benefits), Costumer.class)
                .retrieve()
                .onStatus(status -> status.isError(), response -> response.bodyToMono(ErrorResponse.class)
                        .flatMap(errorResponse -> Mono.error(new RuntimeException(errorResponse.getStatus() + ": " + errorResponse.getMessage()))))
                .bodyToMono(Benefits.class)
                .timeout(Duration.ofMillis(10000))
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontró el recurso")));
    }

    @Override
    public Mono<Benefits> update(Integer id, Benefits benefits) {
        return webClientBenefits.put()
                .uri("/benefits/{id}", id)
                .body(Mono.just(benefits), Costumer.class)
                .retrieve()
                .onStatus(status -> status.isError(), response -> response.bodyToMono(ErrorResponse.class)
                        .flatMap(errorResponse -> Mono.error(new RuntimeException(errorResponse.getStatus() + ": " + errorResponse.getMessage()))))
                .bodyToMono(Benefits.class)
                .timeout(Duration.ofMillis(10000))
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontró el recurso")));
    }

    @Override
    public Mono<Boolean> delete(Integer id) {
        return webClientBenefits.delete()
                .uri("/benefits/{id}", id)
                .retrieve()
                .onStatus(status -> status.isError(), response -> response.bodyToMono(ErrorResponse.class)
                        .flatMap(errorResponse -> Mono.error(new RuntimeException(errorResponse.getStatus() + ": " + errorResponse.getMessage()))))
                .bodyToMono(Boolean.class)
                .timeout(Duration.ofMillis(10000))
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontró el recurso")));
    }

}
