package bg.unwe.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookAuthorId implements Serializable {

    @Column(name = "BookID", nullable = false)
    private Integer book;

    @Column(name = "AuthorID", nullable = false)
    private Integer author;

    public BookAuthorId() {

    }

    public BookAuthorId(Integer book, Integer author) {
        this.book = book;
        this.author = author;
    }

    public Integer getBook() {
        return book;
    }

    public Integer getAuthor() {
        return author;
    }
}
