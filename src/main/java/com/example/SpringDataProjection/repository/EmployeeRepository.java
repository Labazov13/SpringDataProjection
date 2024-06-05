package com.example.SpringDataProjection.repository;

import com.example.SpringDataProjection.projections.EmployeeProjection;
import com.example.SpringDataProjection.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<EmployeeProjection> findByLastName(String lastName);
    Optional<List<EmployeeProjection>> findByDepartmentName(String department);

    EmployeeProjection findProjectedById(Long id, Class<EmployeeProjection> employeeProjectionClass);

}
