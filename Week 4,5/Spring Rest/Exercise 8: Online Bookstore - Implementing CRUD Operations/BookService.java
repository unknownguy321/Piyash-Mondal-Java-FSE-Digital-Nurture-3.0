import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookDTO)
                .toList();
    }

    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toBookDTO)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = bookMapper.toBook(bookDTO);
        return bookMapper.toBookDTO(bookRepository.save(book));
    }

    @Transactional
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        return bookMapper.toBookDTO(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
