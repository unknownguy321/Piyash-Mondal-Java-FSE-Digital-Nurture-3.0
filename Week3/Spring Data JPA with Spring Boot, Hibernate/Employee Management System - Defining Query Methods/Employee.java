package com.example.employeemanagementsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
@NamedQueries({
    @NamedQuery(
        name = "Employee.findByNameNamedQuery",
        query = "SELECT e FROM Employee e WHERE e.name = :name"
    ),
    @NamedQuery(
        name = "Employee.findByDepartmentNameNamedQuery",
        query = "SELECT e FROM Employee e JOIN e.department d WHERE d.name = :departmentName"
    )
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // Constructors, getters, setters, etc.
}
