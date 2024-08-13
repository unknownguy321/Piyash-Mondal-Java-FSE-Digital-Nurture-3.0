package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Fetch specific fields of employees using a class-based projection (DTO)
    @Query("SELECT new com.example.employeemanagementsystem.dto.EmployeeDTO(e.id, e.name, e.email, d.name) " +
           "FROM Employee e JOIN e.department d")
    List<EmployeeDTO> findAllEmployeeDTOs();
}
