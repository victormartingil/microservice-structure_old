package com.microservice.structure.microserviceb.service.impl;

import com.microservice.structure.microserviceb.converter.crud.Converter;
import com.microservice.structure.microserviceb.model.dto.DepartmentDto;
import com.microservice.structure.microserviceb.model.entity.Department;
import com.microservice.structure.microserviceb.model.repository.DepartmentRepository;
import com.microservice.structure.microserviceb.service.crud.CrudServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j // enable log
public class DepartmentServiceImpl extends CrudServiceImpl<Department, DepartmentDto, DepartmentRepository> {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final RestTemplate restTemplate;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository,
                                 Converter<Department, DepartmentDto> converter,
                                 CircuitBreakerFactory circuitBreakerFactory,
                                 RestTemplate restTemplate) {
        super(repository, converter);
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.restTemplate = restTemplate;
    }

    public String getFromApi() {
        log.info("getFromApi -> inside");
        String url = "https://jsonplaceholder.typicode.com/albums";
        return restTemplate.getForObject(url, String.class);
    }

    public String getJsonFromApiExampleWithFallback() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        String fakeUrl = "https://fakeUrlToGetError.com";

        return circuitBreaker.run(() ->
                        restTemplate.getForObject(fakeUrl, String.class),
                fallback -> handleErrorCase()); //Redirect this method if there is any error
    }

    private String handleErrorCase() {
        return "Error handled!";
    }


}
