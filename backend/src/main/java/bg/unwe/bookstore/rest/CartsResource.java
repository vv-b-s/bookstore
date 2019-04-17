package bg.unwe.bookstore.rest;

import bg.unwe.bookstore.dao.BookRepository;
import bg.unwe.bookstore.dao.CartBookRepository;
import bg.unwe.bookstore.dao.CartRepository;
import bg.unwe.bookstore.dao.UpdateUtil;
import bg.unwe.bookstore.dto.BookDTO;
import bg.unwe.bookstore.dto.UserDTO;
import bg.unwe.bookstore.model.Book;
import bg.unwe.bookstore.model.Cart;
import bg.unwe.bookstore.model.CartBook;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@RequestScoped
@Path("/Carts")
public class CartsResource {

    @Inject
    private BookRepository bookRepository;

    @Inject
    private CartRepository cartRepository;

    @Inject
    private CartBookRepository cartBookRepository;

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getCart(@PathParam("id") int cartId) {
        return cartRepository.findById(cartId)
                .map(c -> Response.ok(c).build())
                .orElseGet(() -> Response.status(NOT_FOUND).build());
    }

    @GET
    @Path("/{id}/Items")
    @Produces(APPLICATION_JSON)
    public Response getCartItems(@PathParam("id") int cartId) {
        return cartRepository.findById(cartId)
                .map(c -> Response.ok(cartBookRepository.findAllByCart(c).toArray(new CartBook[0])).build())
                .orElseGet(() -> Response.status(NOT_FOUND).build());
    }

    @POST
    @Transactional
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response createCart(UserDTO userDTO) {
        Cart storedCart = cartRepository.persist(new Cart(userDTO.getUserId(), false, null, null));
        return Response.ok(storedCart).build();
    }

    @POST
    @Transactional
    @Path("/{id}/Items")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addItemToCart(@PathParam("id") int cartId, BookDTO bookDTO) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        Optional<Book> book = bookRepository.findById(bookDTO.getBookId());

        if (cart.isPresent() && book.isPresent()) {
            CartBook cartBook = cartBookRepository.persist(new CartBook(cart.get(), book.get(), LocalDateTime.now(), 1));
            return Response.ok(cartBook).build();

        } else return Response.status(NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response editCart(@PathParam("id") int cartId, Cart cart) {
        return cartRepository.findById(cartId)
                .map(c -> Response.ok(cartRepository.persist(UpdateUtil.updateWhereNotNull(cart, c))).build())
                .orElseGet(() -> Response.status(NOT_FOUND).build());
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    @Produces(APPLICATION_JSON)
    public Response finalizeOrder(@PathParam("id") int cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);

        if(cart.isPresent()) {
            cart.get().setPaid(true);
            return Response.ok(cartRepository.persist(cart.get())).build();

        } else return Response.status(NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}/Items")
    @Transactional
    @Consumes(APPLICATION_JSON)
    public Response removeItemFromCart(@PathParam("id") int cartId, BookDTO bookDTO) {
        cartBookRepository.removeByCartAndBookId(cartId, bookDTO.getBookId());
        return Response.noContent().build();
    }
}

