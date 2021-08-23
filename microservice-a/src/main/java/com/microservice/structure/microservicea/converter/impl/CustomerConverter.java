package com.microservice.structure.microservicea.converter.impl;

import com.microservice.structure.microservicea.converter.crud.MapperConverter;
import com.microservice.structure.microservicea.model.dto.CustomerDto;
import com.microservice.structure.microservicea.model.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter extends MapperConverter<Customer, CustomerDto> {

    @Autowired
    public CustomerConverter() {
        super(Customer.class, CustomerDto.class);
    }


}
