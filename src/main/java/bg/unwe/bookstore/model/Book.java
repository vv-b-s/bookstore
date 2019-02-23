package bg.unwe.bookstore.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "BookID")
    private int id;

    @Column(nullable = false, name = "Title", columnDefinition = "nvarchar(500)")
    private String title;

    @Column(name = "Year")
    private int year;

    @Column(name = "Price")
    private BigDecimal price;

    @Lob
    @Column(name = "Cover", columnDefinition = "varbinary(MAX)")
    private byte[] cover;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "Description", columnDefinition = "nvarchar(MAX)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "CategoryID", nullable = false)
    private Category category;

    public Book() {

    }

    public Book(String title, int year, BigDecimal price, byte[] cover, String isbn, String description, Category category) {
        this.title = title;
        this.year = year;
        this.price = price;
        this.cover = cover;
        this.isbn = isbn;
        this.description = description;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
