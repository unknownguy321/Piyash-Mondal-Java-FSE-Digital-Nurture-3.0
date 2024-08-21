package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.exception.ResourceNotFoundException;
import com.example.bookstoreapi.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1, "Effective Java", "Joshua Bloch", 45.0, "978-0134685991"));
        books.add(new Book(2, "Clean Code", "Robert C. Martin", 40.0, "978-0132350884"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Optional<Book> book = books.stream().filter(b -> b.getId() == id).findFirst();

        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            throw new ResourceNotFoundException("Book with ID " + id + " not found");
        }
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
        if (newBook.getTitle() == null || newBook.getAuthor() == null) {
            throw new BadRequestException("Title and Author are required fields");
        }

        books.add(newBook);
        return ResponseEntity.status(201).body(newBook);
    }
}
