package com.sistemaactivos.webclient.ary.controller;

import com.sistemaactivos.webclient.ary.model.Benefits;
import com.sistemaactivos.webclient.ary.model.Costumer;
import com.sistemaactivos.webclient.ary.service.BenefitsService;
import com.sistemaactivos.webclient.ary.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BenefitsController {

    @Autowired
    private BenefitsService benefitsService;

    @GetMapping(path = {"/benefits"})
    public Flux<Benefits> findAll() {
        return benefitsService.findAll();
    }

    @GetMapping(path = {"/benefits/{id}"})
    public Mono<Benefits> findByID(@PathVariable("id") Integer id) {
        return benefitsService.findByID(id);
    }

    @GetMapping(path = {"/benefits/page"})
    public Flux<Benefits> getRecordBy(@RequestParam(defaultValue = "10") int pageSize,
                                      @RequestParam(defaultValue = "0") int pageNumber) {
        return benefitsService.getCostumers(pageSize, pageNumber);
    }

    @PostMapping(path = {"/benefits"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Benefits> createRecord(@RequestBody Benefits benefits) {
        return benefitsService.create(benefits);
    }

    @PutMapping(path = {"/benefits/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Benefits> updateRecord(@PathVariable("id") Integer id, @RequestBody Benefits benefits) {
        return benefitsService.update(id, benefits);
    }

    @DeleteMapping(path = {"/benefits/{id}"})
    public Mono<?> deleteRecord(@PathVariable("id") Integer id){ return benefitsService.delete(id); }

}
