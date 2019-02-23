package bg.unwe.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookAuthorId implements Serializable {

    @Column(name = "BookID", nullable = false)
    private int book;

    @Column(name = "AuthorID", nullable = false)
    private int author;

    public BookAuthorId() {

    }

    public BookAuthorId(int book, int author) {
        this.book = book;
        this.author = author;
    }

    public int getBook() {
        return book;
    }

    public int getAuthor() {
        return author;
    }
}
