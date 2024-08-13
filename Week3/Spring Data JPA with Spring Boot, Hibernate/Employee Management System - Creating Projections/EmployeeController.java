package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.projection.EmployeeProjection;
import com.example.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get all employees using interface-based projection
    @GetMapping("/projections")
    public List<EmployeeProjection> getAllEmployeeProjections() {
        return employeeService.getAllEmployeeProjections();
    }

    // Get all employees using class-based projection (DTO)
    @GetMapping("/dtos")
    public List<EmployeeDTO> getAllEmployeeDTOs() {
        return employeeService.getAllEmployeeDTOs();
    }
}
