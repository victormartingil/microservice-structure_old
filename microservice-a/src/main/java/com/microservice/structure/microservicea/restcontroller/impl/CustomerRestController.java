package com.microservice.structure.microservicea.restcontroller.impl;


import com.microservice.structure.microservicea.model.VO.CustomerDepartmentVO;
import com.microservice.structure.microservicea.model.dto.CustomerDto;
import com.microservice.structure.microservicea.model.entity.Customer;
import com.microservice.structure.microservicea.restcontroller.crud.RestCrudControllerImpl;
import com.microservice.structure.microservicea.service.crud.CrudService;
import com.microservice.structure.microservicea.service.impl.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/microservice-a/customer")
//@RefreshScope
public class CustomerRestController extends RestCrudControllerImpl<Customer, CustomerDto> {

    @Value("${server.port}")
    String port;
    @Value("${spring.application.name}")
    String applicationName;
    @Value("${configuration.environment}")
    String environment;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    public CustomerRestController(
            CrudService<Customer, CustomerDto> service) {
        super(service);
    }

    @GetMapping("/showConfiguration")
    public String showConfiguration() {
        log.info(applicationName + " - show configuration");
        return "Application name: " + applicationName +
                ".\nPort: " + port +
                ".\nEnvironment: " + environment;
    }

    @GetMapping("/getFromApi")
    public ResponseEntity<String> getFromApi() {

        if (customerService.getFromApi() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("This url can not be reached");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.getFromApi());
    }

    // Get JSON from API with Circuit Breaker Example -> Fallback
    @GetMapping("/getJsonFromApiExampleWithFallback")
    public ResponseEntity getJsonFromApiExampleWithFallback() {

        if (customerService.getJsonFromApiExampleWithFallback() == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("This url can not be reached");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.getJsonFromApiExampleWithFallback());
    }

    @GetMapping("/customerWithDepartment/{id}")
    public ResponseEntity getCustomerWithDepartment(@PathVariable("id") Long customerId) {
        log.info("Inside getCustomerWithDepartment of CustomerRestController");
        CustomerDepartmentVO vo = customerService.getCustomerWithDepartment(customerId);

        if (vo == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Customer can not be found");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.getCustomerWithDepartment(customerId));
    }

//    @PostMapping("/refresh")
//    public ResponseEntity refresh (HttpServletResponse response) throws IOException {
//        log.info("Inside Refresh Controller");
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(customerService.refresh());
//    }

}
