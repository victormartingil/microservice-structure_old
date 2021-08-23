package com.microservice.structure.microserviceb.service.crud;

import java.util.List;
import java.util.Optional;

public interface CrudService <Entity, Dto>{

    public List<Dto> findAll();

    public Optional<Dto> findById(long id);

    public void create(Dto dto);

    public void delete(long id);

}
