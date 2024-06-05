package com.example.SpringDataProjection.exceptions;

public class DepartmentNotFoundException extends  RuntimeException{
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
