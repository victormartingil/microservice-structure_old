package com.microservice.structure.microservicea.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private long id;
    private String name;
    private String lastName;
    private long departmentId;

}
