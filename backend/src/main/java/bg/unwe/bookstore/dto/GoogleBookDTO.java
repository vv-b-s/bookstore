package bg.unwe.bookstore.dto;

public class GoogleBookDTO {
    private String ISBN;
    private String title;
    private String publisher;
    private String link;

    public GoogleBookDTO() {
    }

    public GoogleBookDTO(String ISBN, String title, String publisher, String link) {
        this.ISBN = ISBN;
        this.title = title;
        this.publisher = publisher;
        this.link = link;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
