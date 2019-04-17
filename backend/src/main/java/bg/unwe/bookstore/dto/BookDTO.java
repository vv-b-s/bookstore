package bg.unwe.bookstore.dto;

public class BookDTO {
    private int bookId;

    public BookDTO() {

    }

    public BookDTO(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
