package com.microservice.structure.microserviceb.converter.impl;


import com.microservice.structure.microserviceb.converter.crud.MapperConverter;
import com.microservice.structure.microserviceb.model.dto.DepartmentDto;
import com.microservice.structure.microserviceb.model.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter extends MapperConverter<Department, DepartmentDto> {

    @Autowired
    public DepartmentConverter() {
        super(Department.class, DepartmentDto.class);
    }


}
