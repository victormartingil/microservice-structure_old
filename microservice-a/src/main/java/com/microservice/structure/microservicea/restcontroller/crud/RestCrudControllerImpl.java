package com.microservice.structure.microservicea.restcontroller.crud;

import com.microservice.structure.microservicea.service.crud.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class RestCrudControllerImpl<Entity, Dto> implements RestCrudController<Entity, Dto> {

    protected final CrudService<Entity, Dto> service;

    public RestCrudControllerImpl(CrudService<Entity, Dto> service) {
        this.service = service;
    }

    @Override
    @GetMapping("")
    public List<Dto> list() {
        return service.findAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<Dto> show(@PathVariable long id) {

        return ResponseEntity.of(service.findById(id));
    }

    @Override
    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody Dto dto) throws Exception {
        try {
            service.create(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
