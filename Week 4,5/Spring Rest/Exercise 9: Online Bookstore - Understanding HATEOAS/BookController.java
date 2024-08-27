import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Book>> getAllBooks() {
        List<EntityModel<Book>> books = bookService.getAllBooks().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(books, linkTo(methodOn(BookController.class).getAllBooks()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(assembler.toModel(book));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Book>> createBook(@Valid @RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.created(linkTo(methodOn(BookController.class).getBookById(createdBook.getId())).toUri())
                .body(assembler.toModel(createdBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(assembler.toModel(updatedBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
