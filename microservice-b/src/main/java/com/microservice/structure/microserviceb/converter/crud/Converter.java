package com.microservice.structure.microserviceb.converter.crud;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Converter<Entity, Dto> {

    public Entity convertDtoToEntity(Dto dto) throws Exception;

    public Optional<Dto> convertEntityToDto(Optional<Entity> optional);

    public Dto convertEntityToDto(Entity entity);

    public List<Dto> convertEntityToDto(List<Entity> list);

    public List<Entity> convertDtoToEntity(List<Dto> list) throws Exception;

    public Set<Dto> convertEntityToDto(Set<Entity> list);

    public Set<Entity> convertDtoToEntity(Set<Dto> list) throws Exception;

    public void updateEntity(Dto dto, Entity entity) throws Exception;


}
