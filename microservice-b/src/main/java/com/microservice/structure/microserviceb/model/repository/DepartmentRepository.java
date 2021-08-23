package com.microservice.structure.microserviceb.model.repository;

import com.microservice.structure.microserviceb.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public List<Department> findByDepartmentName(String departmentName);

    public List<Department> findByDepartmentCode(String departmentCode);

    public List<Department> findByDepartmentNameAndDepartmentCode(String departmentName, String departmentCode);

}