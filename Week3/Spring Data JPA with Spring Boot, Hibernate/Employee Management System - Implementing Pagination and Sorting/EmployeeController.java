package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get paginated and sorted list of all employees
    @GetMapping
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeService.getAllEmployees(pageable);
    }

    // Get paginated and sorted list of employees by name
    @GetMapping("/search")
    public Page<Employee> getEmployeesByName(
            @RequestParam("name") String name,
            Pageable pageable) {
        return employeeService.getEmployeesByName(name, pageable);
    }

    // Get sorted list of all employees
    @GetMapping("/sorted")
    public List<Employee> getAllEmployees(@RequestParam("sort") String sort) {
        return employeeService.getAllEmployees(Sort.by(sort));
    }
}
