package com.microservice.structure.microserviceb.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "department")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    private String departmentName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String departmentCode;

    /* Constructors added by @AllArgsConstructor and NoArgsConstructor */
    /* Getters and setters added by @Data from Lombok*/

}