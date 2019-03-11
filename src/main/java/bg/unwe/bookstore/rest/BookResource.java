package bg.unwe.bookstore.rest;

import bg.unwe.bookstore.dao.BookRepository;
import bg.unwe.bookstore.model.Book;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/Books")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    private BookRepository bookRepository;

    @GET
    public Response getAllBooks() {
        Book[] books = bookRepository.findAll().toArray(new Book[0]);

        return Response.ok(books).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id) {
        return bookRepository.findById(id)
                .map(b -> Response.ok(b).build())
                .orElseGet(() -> Response.status(NOT_FOUND).build());
    }
}
