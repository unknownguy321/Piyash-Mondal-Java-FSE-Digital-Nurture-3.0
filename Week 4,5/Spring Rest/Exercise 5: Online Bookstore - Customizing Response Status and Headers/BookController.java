package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Book;
import org.springframework.http.HttpStatus;
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
            return ResponseEntity.ok()
                    .header("Custom-Header", "Book-Fetch-Success")
                    .body(book.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Custom-Header", "Book-Not-Found")
                    .build();
        }
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
        books.add(newBook);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Custom-Header", "Book-Creation-Success")
                .body(newBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        boolean removed = books.removeIf(book -> book.getId() == id);

        if (removed) {
            return ResponseEntity.noContent()
                    .header("Custom-Header", "Book-Deletion-Success")
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Custom-Header", "Book-Not-Found")
                    .build();
        }
    }
}
