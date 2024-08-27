package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book1 = new Book(1L, "Book One", "Author One", "1234567890");
        Book book2 = new Book(2L, "Book Two", "Author Two", "0987654321");

        when(bookService.findAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Book One")))
                .andExpect(jsonPath("$[1].title", is("Book Two")));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book(1L, "Book One", "Author One", "1234567890");

        when(bookService.findBookById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Book One")));
    }

    @Test
    public void testGetBookById_NotFound() throws Exception {
        when(bookService.findBookById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/999")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateBook() throws Exception {
        Book book = new Book(1L, "New Book", "New Author", "1234567890");

        when(bookService.saveBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"New Book\", \"author\":\"New Author\", \"isbn\":\"1234567890\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("New Book")))
                .andExpect(jsonPath("$.author", is("New Author")))
                .andExpect(jsonPath("$.isbn", is("1234567890")));
    }

    @Test
    public void testDeleteBook() throws Exception {
        when(bookService.deleteBook(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteBook_NotFound() throws Exception {
        when(bookService.deleteBook(anyLong())).thenReturn(false);

        mockMvc.perform(delete("/api/books/999"))
                .andExpect(status().isNotFound());
    }

    @Test
public void testCreateBook_InvalidData() throws Exception {
    mockMvc.perform(post("/api/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"\", \"author\":\"New Author\"}"))
            .andExpect(status().isBadRequest());
}


    @Test
public void testCreateBook_VerifyHeaders() throws Exception {
    Book book = new Book(1L, "New Book", "New Author", "1234567890");

    when(bookService.saveBook(any(Book.class))).thenReturn(book);

    mockMvc.perform(post("/api/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"New Book\", \"author\":\"New Author\", \"isbn\":\"1234567890\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title", is("New Book")))
            .andExpect(jsonPath("$.author", is("New Author")))
            .andExpect(jsonPath("$.isbn", is("1234567890")))
            .andExpect(header().string("Content-Type", "application/json"));
}

}
