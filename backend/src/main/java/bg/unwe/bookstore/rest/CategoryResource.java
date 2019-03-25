package bg.unwe.bookstore.rest;

import bg.unwe.bookstore.dao.CategoryRepository;
import bg.unwe.bookstore.model.Category;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/Categories")
public class CategoryResource {

    @Inject
    private CategoryRepository categoryRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() {
        Category[] categories = categoryRepository
                .findAll()
                .toArray(new Category[0]);

        return Response.ok(categories).build();
    }

}
