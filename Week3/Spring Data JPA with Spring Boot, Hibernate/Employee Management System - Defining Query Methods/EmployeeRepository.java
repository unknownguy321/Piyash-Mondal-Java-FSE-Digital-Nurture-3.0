package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employees by name
    List<Employee> findByName(String name);

    // Find employees by department name
    List<Employee> findByDepartmentName(String departmentName);

    // Find employees with an email containing a specific string
    List<Employee> findByEmailContaining(String keyword);

    // Find employees with a name starting with a specific prefix
    List<Employee> findByNameStartingWith(String prefix);

    // Find employees by department id
    List<Employee> findByDepartmentId(Long departmentId);

    // Custom query to find employees by partial name match
    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
    List<Employee> searchByName(@Param("name") String name);

    // Custom query to find employees by department name using join
    @Query("SELECT e FROM Employee e JOIN e.department d WHERE d.name = :departmentName")
    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);

    // Native query to find employees by email domain
    @Query(value = "SELECT * FROM employees e WHERE e.email LIKE %:domain%", nativeQuery = true)
    List<Employee> findByEmailDomain(@Param("domain") String domain);

    @Query(name = "Employee.findByNameNamedQuery")
    List<Employee> findByNameNamedQuery(@Param("name") String name);

    @Query(name = "Employee.findByDepartmentNameNamedQuery")
    List<Employee> findByDepartmentNameNamedQuery(@Param("departmentName") String departmentName);
}
