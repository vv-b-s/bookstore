package bg.unwe.bookstore.rest;

import bg.unwe.bookstore.dao.BookRepository;
import bg.unwe.bookstore.dao.CategoryRepository;
import bg.unwe.bookstore.dao.UpdateUtil;
import bg.unwe.bookstore.model.Book;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Optional;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/Books")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    private CategoryRepository categoryRepository;

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

    @GET
    @Path("/Category/{categoryId}")
    public Response getAllByCategory(@PathParam("categoryId") int categoryId) {
        return categoryRepository
                .findById(categoryId)
                .map(c -> Response.ok(bookRepository.findByCategory(c).toArray(new Book[0])).build())
                .orElseGet(() -> Response.status(NOT_FOUND).build());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        return Response.ok(bookRepository.persist(book)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response editBook(@PathParam("id") int bookId, Book book) {
        Optional<Book> storedBook = bookRepository.findById(bookId);

        if (storedBook.isPresent()) {
            Book updated = UpdateUtil.updateWhereNotNull(book, storedBook.get());
            return updated == null ? Response.status(INTERNAL_SERVER_ERROR).build() : Response.ok(updated).build();

        } else {
            return Response.status(NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteBook(@PathParam("id") int bookId) {
        bookRepository.remove(bookId);
        return Response.noContent().build();
    }
}
