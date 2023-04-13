package com.sistemaactivos.webclient.ary.service;

import com.sistemaactivos.webclient.ary.model.Costumer;
import com.sistemaactivos.webclient.ary.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.naming.ServiceUnavailableException;
import java.time.Duration;

@Service
public class CostumerService implements ICostumerService{

    @Autowired
    WebClient webClient;

    @Override
    public Flux<Costumer> findAll() {
        return webClient.get()
                .uri("/costumers")
                .retrieve()
                .bodyToFlux(Costumer.class)
                .timeout(Duration.ofMillis(10_000));
    }

    @Override
    public Mono<Costumer> findByID(Integer id) {
        return webClient.get()
                .uri("/costumers/{id}", id)
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> response.bodyToMono(ErrorResponse.class)
                                .flatMap(errorResponse -> Mono.error(
                                        new RuntimeException(errorResponse.getStatus() + ": " + errorResponse.getMessage()))))
                .bodyToMono(Costumer.class)
                .timeout(Duration.ofMillis(10_000))
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontró el recurso")));
    }

    @Override
    public Flux<Costumer> getCostumers(int pageSize, int pageNumber) {
        int offset = pageSize * pageNumber;
        return webClient.get()
                .uri("/costumers?page={pageNumber}&size={pageSize}", pageNumber, pageSize)
                .retrieve()
                .bodyToFlux(Costumer.class)
                .skip(offset)
                .take(pageSize);
    }


}
