package com.microservice.structure.microserviceb.service.crud;

import com.microservice.structure.microserviceb.converter.crud.Converter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class CrudServiceImpl<Entity, Dto, Repository extends CrudRepository<Entity, Long>> implements CrudService<Entity, Dto> {

    protected Repository repository;

    protected Converter<Entity, Dto> converter;

    public CrudServiceImpl(Repository repository, Converter<Entity, Dto> converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<Dto> findAll() {
        List<Dto> list = new ArrayList<>();
        repository.findAll().forEach(e -> {
            list.add(converter.convertEntityToDto(e));
        });
        return list;
    }

    @Override
    public Optional<Dto> findById(long id) {
        return converter.convertEntityToDto(repository.findById(id));
    }

    @Override
    @Transactional
    public void create(Dto dto) {

        try {
            if (dto == null) {
                throw new NoSuchElementException();
            }
            Entity entity = converter.convertDtoToEntity(dto);
            repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void createEntity(Entity entity) {

        try {
            if (entity == null) {
                throw new NoSuchElementException();
            }
            repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

}
