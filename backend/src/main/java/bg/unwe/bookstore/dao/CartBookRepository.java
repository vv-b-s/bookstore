package bg.unwe.bookstore.dao;

import bg.unwe.bookstore.model.Cart;
import bg.unwe.bookstore.model.CartBook;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CartBookRepository extends AbstractRepository<Integer, CartBook> {

    public List<CartBook> findAllByCart(Cart cart) {
        return createNamedQuery(CartBook.FIND_ALL_BY_CART, "cart", cart)
                .getResultList();
    }

    public void removeByCartAndBookId(int cartId, int bookId) {
        createNamedQuery(CartBook.REMOVE_BY_CART_AND_BOOK_ID, "cartId", cartId, "bookId", bookId)
                .executeUpdate();
    }

    @Override
    protected Class<CartBook> getEntityClass() {
        return CartBook.class;
    }
}
