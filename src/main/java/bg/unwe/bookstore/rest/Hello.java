package bg.unwe.bookstore.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello")
@RequestScoped
public class Hello {

    @GET
    public Response sayHello() {
        return Response.ok("Hello World!").build();
    }
}
