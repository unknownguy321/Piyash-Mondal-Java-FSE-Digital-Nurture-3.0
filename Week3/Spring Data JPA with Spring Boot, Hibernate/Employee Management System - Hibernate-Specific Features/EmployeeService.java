package com.example.service;

import com.example.entity.Employee;
import com.example.repository.RepositoryContainer;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RepositoryContainer.EmployeeRepository employeeRepository;

    @Transactional
    public void saveAllEmployees(List<Employee> employees) {
        Session session = entityManager.unwrap(Session.class);
        int batchSize = 20;

        for (int i = 0; i < employees.size(); i++) {
            session.save(employees.get(i));

            if (i > 0 && i % batchSize == 0) {
                // Flush and clear the session to avoid memory issues
                session.flush();
                session.clear();
            }
        }
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
