package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.projection.EmployeeProjection;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees using interface-based projection
    public List<EmployeeProjection> getAllEmployeeProjections() {
        return employeeRepository.findAllEmployeeDetails();
    }

    // Get all employees using class-based projection (DTO)
    public List<EmployeeDTO> getAllEmployeeDTOs() {
        return employeeRepository.findAllEmployeeDTOs();
    }
}
