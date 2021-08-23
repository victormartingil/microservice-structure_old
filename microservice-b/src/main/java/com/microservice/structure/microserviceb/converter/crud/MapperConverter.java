package com.microservice.structure.microserviceb.converter.crud;


import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MapperConverter<Entity, Dto> implements Converter<Entity, Dto> {

    private static final ModelMapper modelMapper;

    private final Class<Entity> entityClass;

    private final Class<Dto> dtoClass;

    static {
        modelMapper = new ModelMapper();
    }

    public MapperConverter(Class<Entity> entityClass, Class<Dto> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public Entity convertDtoToEntity(Dto dto) {
        return modelMapper.map(dto, entityClass);
    }

    @Override
    public Optional<Dto> convertEntityToDto(Optional<Entity> optional) {
        return optional.map(this::convertEntityToDto);
    }

    @Override
    public Dto convertEntityToDto(Entity entity) {
        if (entity == null) {
            return null;
        }
        return modelMapper.map(entity, dtoClass);
    }

    @Override
    public List<Dto> convertEntityToDto(List<Entity> list) {
        return list.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> convertDtoToEntity(List<Dto> list) {
        return list.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Set<Dto> convertEntityToDto(Set<Entity> list) {
        return list.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Entity> convertDtoToEntity(Set<Dto> list) {
        return list.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public void updateEntity(Dto dto, Entity entity) {
        modelMapper.map(dto, entity);
    }

}
