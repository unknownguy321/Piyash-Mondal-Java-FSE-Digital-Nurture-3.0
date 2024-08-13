package com.example.repository;

import com.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;

public interface RepositoryContainer {

    @Repository
    @Primary
    interface EmployeeRepository extends JpaRepository<Employee, Long> {
    }
}
