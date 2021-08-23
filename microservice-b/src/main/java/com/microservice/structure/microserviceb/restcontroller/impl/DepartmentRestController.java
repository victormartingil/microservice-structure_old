package com.microservice.structure.microserviceb.restcontroller.impl;


import com.microservice.structure.microserviceb.model.dto.DepartmentDto;
import com.microservice.structure.microserviceb.model.entity.Department;
import com.microservice.structure.microserviceb.restcontroller.crud.RestCrudController;
import com.microservice.structure.microserviceb.service.crud.CrudService;
import com.microservice.structure.microserviceb.service.impl.DepartmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/microservice-b/department")
public class DepartmentRestController extends RestCrudController<Department, DepartmentDto> {

    @Value("${server.port}")
    String port;
    @Value("${spring.application.name}")
    String applicationName;
    @Value("${configuration.environment}")
    String environment;

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Autowired
    public DepartmentRestController(
            CrudService<Department, DepartmentDto> service) {
        super(service);
    }

    @GetMapping("/showConfiguration")
    public String showConfiguration() {
        log.info(applicationName + " - show configuration");
        return "Application name: " + applicationName +
                ".\nPort: " + port +
                ".\nEnvironment: "+ environment;
    }

    @GetMapping("/getFromApi")
    public String getFromApi() {

        return departmentService.getFromApi();
    }

    // Get JSON from API with Circuit Breaker Example -> Fallback
    @GetMapping("/getJsonFromApiExampleWithFallback")
    public String getJsonFromApiExampleWithFallback() {

        return departmentService.getJsonFromApiExampleWithFallback();
    }


}
