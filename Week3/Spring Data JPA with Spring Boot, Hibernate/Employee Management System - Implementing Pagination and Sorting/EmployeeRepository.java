package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Paginated list of employees
    Page<Employee> findAll(Pageable pageable);

    // Paginated and filtered list of employees by name
    Page<Employee> findByName(String name, Pageable pageable);

    // Find all employees with pagination and sorting
    Page<Employee> findAll(Pageable pageable);

    // Find employees by name with pagination and sorting
    Page<Employee> findByName(String name, Pageable pageable);

    // Find all employees with sorting
    List<Employee> findAll(Sort sort);
}
