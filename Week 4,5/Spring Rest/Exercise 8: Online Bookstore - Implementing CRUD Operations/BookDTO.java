import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDTO {
    @JsonProperty("book_id")
    private Long id;

    @JsonProperty("book_title")
    private String title;

    @JsonProperty("book_author")
    private String author;

    @JsonProperty("book_price")
    private Double price;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
