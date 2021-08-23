package com.microservice.structure.microserviceb.restcontroller.crud;

import com.microservice.structure.microserviceb.service.crud.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseRestCrudController<Entity, Dto> {

    protected final CrudService<Entity, Dto> service;

    public BaseRestCrudController(CrudService<Entity, Dto> service) {
        this.service = service;
    }

    public ResponseEntity<String> add(@RequestBody Dto dto) throws Exception {
        try {
            service.create(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Dto> show(@PathVariable long id) {
        return ResponseEntity.of(service.findById(id));
    }

    public ResponseEntity<String> delete(@PathVariable long id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
