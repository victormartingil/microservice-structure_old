package com.microservice.structure.microservicea.model.repository;

import com.microservice.structure.microservicea.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public List<Customer> findByName(String name);

    public List<Customer> findByLastName(String lastName);

    public List<Customer> findByNameAndLastName(String name, String lastName);

}