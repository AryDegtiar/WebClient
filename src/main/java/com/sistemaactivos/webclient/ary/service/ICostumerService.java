package com.sistemaactivos.webclient.ary.service;

import com.sistemaactivos.webclient.ary.model.Costumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICostumerService {
    Flux<Costumer> findAll();
    Mono<Costumer> findByID(Integer id);
    Flux<Costumer> getCostumers(int pageSize, int pageNumber);
    Mono<?> delete(Integer id);
    Mono<Costumer> create(Costumer costumer);
    Mono<Costumer> update(Integer id, Costumer costumer);
}
