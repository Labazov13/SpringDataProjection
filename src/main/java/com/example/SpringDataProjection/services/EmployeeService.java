package com.example.SpringDataProjection.services;

import com.example.SpringDataProjection.dto.EmployeeDTO;
import com.example.SpringDataProjection.exceptions.DepartmentNotFoundException;
import com.example.SpringDataProjection.exceptions.EmployeeNotFoundException;
import com.example.SpringDataProjection.projections.EmployeeProjection;
import com.example.SpringDataProjection.repository.DepartmentRepository;
import com.example.SpringDataProjection.repository.EmployeeRepository;
import com.example.SpringDataProjection.entities.Department;
import com.example.SpringDataProjection.entities.Employee;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public EmployeeProjection getEmployeeByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    public EmployeeProjection getEmployeeProjectionById(Long id) {
        return employeeRepository.findProjectedById(id, EmployeeProjection.class);
    }

    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Department department = departmentRepository.findByName(employeeDTO.department())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        Employee employee = new Employee(employeeDTO.firstName(),
                employeeDTO.lastName(), employeeDTO.position(), employeeDTO.salary(), department);
        return employeeRepository.save(employee);
    }

    public List<EmployeeProjection> getEmployeesDepartment(String name) {
        return employeeRepository.findByDepartmentName(name)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
    }
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public EmployeeProjection editEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        Department department = departmentRepository.findByName(employeeDTO.department())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        employee.setFirstName(employeeDTO.firstName());
        employee.setLastName(employeeDTO.lastName());
        employee.setPosition(employeeDTO.position());
        employee.setSalary(employeeDTO.salary());
        employee.setDepartment(department);
        employeeRepository.save(employee);
        return getEmployeeProjectionById(employeeId);
    }

    public String deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
        return "Success!";
    }
}
