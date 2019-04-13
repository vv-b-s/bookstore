package bg.unwe.bookstore.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CartBooks")
public class CartBook implements EntityModel<Integer> {

    @Id
    @Column(name = "CartBookID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "CartID", nullable = false)
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "BookID", nullable = false)
    private Book book;

    @Column(name = "AddedOn", nullable = false, columnDefinition = "datetime DEFAULT GETDATE()")
    private LocalDateTime addedOn;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    public CartBook() {

    }

    @Override
    public Integer getId() {
        return id;
    }

    public CartBook(Cart cart, Book book, LocalDateTime addedOn, Integer quantity) {
        this.cart = cart;
        this.book = book;
        this.addedOn = addedOn;
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
