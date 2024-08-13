package com.example.employeemanagementsystem.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {

    Long getId();
    String getName();
    String getEmail();
    String getDepartmentName();

    // Use @Value to concatenate name and email
    @Value("#{target.name + ' (' + target.email + ')'}")
    String getNameWithEmail();
}
