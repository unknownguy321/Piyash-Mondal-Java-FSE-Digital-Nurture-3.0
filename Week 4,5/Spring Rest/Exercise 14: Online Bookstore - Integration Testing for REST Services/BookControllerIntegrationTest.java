package com.example.bookstore;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        bookRepository.deleteAll();  // Clean up the database before each test
    }

    @Test
    public void testGetAllBooks_empty() throws Exception {
        mockMvc.perform(get("/api/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(0)));
    }

    @Test
    public void testCreateBook() throws Exception {
        String bookJson = "{\"title\":\"Test Book\", \"author\":\"Author Name\", \"isbn\":\"1234567890\"}";

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("Test Book")))
                .andExpect(jsonPath("$.author", is("Author Name")))
                .andExpect(jsonPath("$.isbn", is("1234567890")));

        // Verify the book was created in the database
        Optional<Book> bookOptional = bookRepository.findAll().stream().findFirst();
        assert (bookOptional.isPresent());
        Book book = bookOptional.get();
        assert (book.getTitle().equals("Test Book"));
        assert (book.getAuthor().equals("Author Name"));
        assert (book.getIsbn().equals("1234567890"));
    }

    @Test
    public void testGetAllBooks_nonEmpty() throws Exception {
        Book book = new Book();
        book.setTitle("Existing Book");
        book.setAuthor("Author Name");
        book.setIsbn("0987654321");
        bookRepository.save(book);

        mockMvc.perform(get("/api/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].title", is("Existing Book")))
                .andExpect(jsonPath("$[0].author", is("Author Name")))
                .andExpect(jsonPath("$[0].isbn", is("0987654321")));
    }
}
