package com.microservice.structure.microservicea.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private Long id;
    private String departmentName;
    private String departmentCode;
}