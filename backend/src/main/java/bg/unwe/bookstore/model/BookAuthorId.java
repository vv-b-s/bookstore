package bg.unwe.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAuthorId that = (BookAuthorId) o;
        return Objects.equals(book, that.book) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, author);
    }
}
