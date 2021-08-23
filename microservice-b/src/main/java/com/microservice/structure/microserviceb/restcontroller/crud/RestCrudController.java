package com.microservice.structure.microserviceb.restcontroller.crud;

import com.microservice.structure.microserviceb.service.crud.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class RestCrudController<Entity, Dto> extends BaseRestCrudController<Entity, Dto> {

    public RestCrudController(CrudService<Entity, Dto> service) {
        super(service);
    }

    @GetMapping("")
    public List<Dto> list() {
        return service.findAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<Dto> show(@PathVariable long id) {
        return super.show(id);
    }

    @Override
    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody Dto dto) throws Exception {
        return super.add(dto);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return super.delete(id);
    }

}
