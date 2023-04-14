package com.sistemaactivos.webclient.ary.service;

import com.sistemaactivos.webclient.ary.model.Benefits;
import com.sistemaactivos.webclient.ary.model.Costumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBenefitsService {
    Flux<Benefits> findAll();
    Mono<Benefits> findByID(Integer id);
    Flux<Benefits> getCostumers(int pageSize, int pageNumber);
    Mono<?> delete(Integer id);
    Mono<Benefits> create(Benefits benefits);
    Mono<Benefits> update(Integer id, Benefits benefits);
}
