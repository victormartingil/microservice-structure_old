package com.microservice.structure.microservicea.service.impl;

import com.microservice.structure.microservicea.model.VO.CustomerDepartmentVO;
import com.microservice.structure.microservicea.model.VO.Department;
import com.microservice.structure.microservicea.converter.crud.Converter;
import com.microservice.structure.microservicea.model.dto.CustomerDto;
import com.microservice.structure.microservicea.model.entity.Customer;
import com.microservice.structure.microservicea.model.repository.CustomerRepository;
import com.microservice.structure.microservicea.service.crud.CrudServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j //enable log
public class CustomerServiceImpl extends CrudServiceImpl<Customer, CustomerDto, CustomerRepository> {

    private final CircuitBreakerFactory circuitBreakerFactory;
    private final RestTemplate restTemplate;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository,
                               Converter<Customer, CustomerDto> converter,
                               CircuitBreakerFactory circuitBreakerFactory,
                               RestTemplate restTemplate) {
        super(repository, converter);
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.restTemplate = restTemplate;
    }

    public String getFromApi() {
        RestTemplate notBalancedRestTemplate = new RestTemplate();
        log.info("getFromApi -> inside");
        String url = "https://jsonplaceholder.typicode.com/albums";
        return notBalancedRestTemplate.getForObject(url, String.class);
    }

    public String getJsonFromApiExampleWithFallback() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        String fakeUrl = "https://fakeUrlToGetError.com";

        return circuitBreaker.run(() ->
                        restTemplate.getForObject(fakeUrl, String.class),
                fallback -> handleErrorCase());
    }

    private String handleErrorCase() {
        return null;
    }


    public CustomerDepartmentVO getCustomerWithDepartment(Long customerId) {
        CustomerDepartmentVO vo = new CustomerDepartmentVO();
        Customer customer = repository.findById(customerId).orElse(null);
        if (customer == null) {
            log.error("Customer has not been found!");
            return null;
        }

        //We will call here microservice-a
        String departmentUrl = "http://MICROSERVICE-B/microservice-b/department/" + customer.getDepartmentId();
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        Department department;
        department = circuitBreaker.run(() ->
                        restTemplate.getForObject(
                                departmentUrl, Department.class),
                fallback -> {
                    log.error("Department has not been found!");
                    return null;
                });

        vo.setCustomer(customer);
        vo.setDepartment(department);
        return vo;

    }

//    public String refresh() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<>(null, headers);
//        return restTemplate.postForEntity("http://MICROSERVICE-A:9001/actuator/refresh", entity, String.class).getBody();
//    }
}
