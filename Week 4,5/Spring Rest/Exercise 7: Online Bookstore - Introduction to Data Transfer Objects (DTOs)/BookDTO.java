import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class BookDTO {
    @JsonProperty("book_id")
    private Long id;

    @JsonProperty("book_price")
    @JsonDeserialize(using = CustomPriceDeserializer.class)
    private Double price;

    @JsonProperty("book_title")
    private String title;

    @JsonProperty("book_author")
    private String author;

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
