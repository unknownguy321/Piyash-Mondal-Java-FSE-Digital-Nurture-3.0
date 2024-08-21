package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
        customers.add(newCustomer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    // POST: Process form data for customer registration
    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        Customer customer = new Customer(customers.size() + 1, firstName, lastName, email, password);
        customers.add(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
