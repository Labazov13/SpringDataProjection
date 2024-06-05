package com.example.SpringDataProjection.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record EmployeeDTO(@NotBlank(message = "Firstname cannot be empty")String firstName,
                          @NotBlank(message = "Lastname cannot be empty")String lastName,
                          @NotBlank(message = "Position cannot be empty")String position,
                          @Min(100000) BigDecimal salary,
                          @NotBlank(message = "Department cannot be empty")String department) {
}
