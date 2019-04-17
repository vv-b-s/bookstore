package bg.unwe.bookstore.dao;

import bg.unwe.bookstore.dao.intercept.CartStorageInterceptor;
import bg.unwe.bookstore.model.Cart;

import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.Interceptors;

@ApplicationScoped
public class CartRepository extends AbstractRepository<Integer, Cart> {

    @Override
    @Interceptors({CartStorageInterceptor.class})
    public Cart persist(Cart entity) {
        return super.persist(entity);
    }

    @Override
    protected Class<Cart> getEntityClass() {
        return Cart.class;
    }
}
