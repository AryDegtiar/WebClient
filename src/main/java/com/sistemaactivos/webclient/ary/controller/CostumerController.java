package com.sistemaactivos.webclient.ary.controller;

import com.sistemaactivos.webclient.ary.model.Costumer;
import com.sistemaactivos.webclient.ary.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CostumerController {

    @Autowired
    private CostumerService costumerService;

    @GetMapping(path = {"/costumers"})
    public Flux<Costumer> findAll() {
        return costumerService.findAll();
    }

    @GetMapping(path = {"/costumer/{id}"})
    public Mono<Costumer> findByID(@PathVariable("id") Integer id) {
        return costumerService.findByID(id);
    }

    @GetMapping(path = {"/costumers/page"})
    public Flux<Costumer> getRecordBy(@RequestParam(defaultValue = "10") int pageSize,
                                      @RequestParam(defaultValue = "0") int pageNumber) {
        return costumerService.getCostumers(pageSize, pageNumber);
    }

}
