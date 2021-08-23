package com.microservice.structure.microserviceb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private long id;
    private String departmentName;
    private String departmentCode;

}
