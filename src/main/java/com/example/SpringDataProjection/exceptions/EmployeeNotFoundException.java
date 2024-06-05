package com.example.SpringDataProjection.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
