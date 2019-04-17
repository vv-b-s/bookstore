package bg.unwe.bookstore.dao.intercept;

import bg.unwe.bookstore.model.Cart;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.time.LocalDateTime;

public class CartStorageInterceptor {

    @AroundInvoke
    public Object onCartStorage(InvocationContext ctx) throws Exception {
        Cart cart = (Cart) ctx.getParameters()[0];


        if(cart.getCreatedOn() == null) {
            cart.setCreatedOn(LocalDateTime.now());
        }

        cart.setLastModification(LocalDateTime.now());

        return ctx.proceed();
    }
}
