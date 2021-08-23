package com.microservice.structure.microservicea.restcontroller.crud;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestCrudController<Entity, Dto> {

    public List<Dto> list();

    public ResponseEntity<Dto> show(long id);

    public ResponseEntity<String> add(Dto dto) throws Exception;

    public ResponseEntity<String> delete(long id);
}
