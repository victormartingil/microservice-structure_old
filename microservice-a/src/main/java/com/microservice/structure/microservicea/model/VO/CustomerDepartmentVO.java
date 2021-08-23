package com.microservice.structure.microservicea.model.VO;

import com.microservice.structure.microservicea.model.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDepartmentVO {

    private Customer customer;
    private Department department;
}
