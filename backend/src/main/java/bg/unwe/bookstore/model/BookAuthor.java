package bg.unwe.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "BookAuthors")
public class BookAuthor implements EntityModel<BookAuthorId> {

    @EmbeddedId
    private BookAuthorId id;

    @OneToOne
    @JoinColumn(referencedColumnName = "BookID")
    private Book book;

    @OneToOne
    @JoinColumn(referencedColumnName = "AuthorID")
    private Author author;

    @Column(nullable = false, name = "Position", columnDefinition = "int default 0")
    private Integer position;

    public BookAuthor() {

    }

    public BookAuthor(Book book, Author author, Integer position) {
        id = new BookAuthorId(book.getId(), author.getId());
        this.position = position;
    }

    public Book getBook() {
        return book;
    }

    public Author getAuthor() {
        return author;
    }

    public BookAuthorId getId() {
        return id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
