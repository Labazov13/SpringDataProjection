package com.example.SpringDataProjection.controllers;

import com.example.SpringDataProjection.dto.EmployeeDTO;
import com.example.SpringDataProjection.projections.EmployeeProjection;
import com.example.SpringDataProjection.services.EmployeeService;
import com.example.SpringDataProjection.entities.Employee;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/lastname/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeProjection> getEmployeeByLastname(@PathVariable(name = "lastName")String lastName){
        return ResponseEntity.ok(employeeService.getEmployeeByLastName(lastName));
    }
    @GetMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeProjection> getEmployeeProjectionById(@PathVariable(name = "employeeId")Long employeeId){
        return ResponseEntity.ok(employeeService.getEmployeeProjectionById(employeeId));
    }

    @GetMapping(value = "/department/{name}")
    public ResponseEntity<List<EmployeeProjection>> getEmployeesDepartment(@PathVariable(name = "name") String name){
        return ResponseEntity.ok(employeeService.getEmployeesDepartment(name));
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.createEmployee(employeeDTO));
    }

    @PutMapping(value = "/edit/{employeeId}")
    public ResponseEntity<EmployeeProjection> editEmployee(@PathVariable(name = "employeeId")Long employeeId,
                                                           @Valid @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.editEmployee(employeeId, employeeDTO));
    }
    @DeleteMapping(value = "/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "employeeId")Long employeeId){
        return ResponseEntity.ok(employeeService.deleteEmployee(employeeId));
    }
}
