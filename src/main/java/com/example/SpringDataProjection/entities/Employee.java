package com.example.SpringDataProjection.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "employee_table")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String position;

    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(String firstName, String lastName, String position, BigDecimal salary, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.department = department;
    }
}
