package bg.unwe.bookstore.rest;

import bg.unwe.bookstore.dto.GoogleBookDTO;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Path("/Books")
public class GoogleBooksResource {

    private static final String GOOGLE_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=%s&key=%s";

    @Inject
    @ConfigProperty(name = "google.api.key")
    private String googleApiKey;

    private Client client;

    @PostConstruct
    private void init() {
        client = ClientBuilder.newClient();
    }

    @GET
    @Path("/Like")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findSimilarBooks(@QueryParam("bookName") String bookName) {
        if(bookName == null) {
            return Response.status(BAD_REQUEST).build();
        }

        Response response = client.target(String.format(GOOGLE_BOOKS_URL, bookName, googleApiKey))
                .request(MediaType.APPLICATION_JSON)
                .get();

        String jsonString = response.readEntity(String.class);
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject books = reader.readObject();

        List<GoogleBookDTO> googleBooks = new ArrayList<>();
        for (JsonValue book : books.getJsonArray("items")) {
            JsonObject volumeInfo = book.asJsonObject().get("volumeInfo").asJsonObject();

            String title = volumeInfo.getString("title", "");
            String publisher = volumeInfo.getString("publisher", "");

            String link = volumeInfo.getString("previewLink", "");

            JsonArray isbnArray = volumeInfo.getJsonArray("industryIdentifiers");

            String isbn = isbnArray.size() > 0 ? isbnArray.stream()
                    .findFirst()
                    .map(item -> item.asJsonObject()
                    .getString("identifier", ""))
                    .get() : "";

            googleBooks.add(new GoogleBookDTO(isbn, title, publisher, link));
        }

        return Response.ok(googleBooks.toArray(new GoogleBookDTO[0])).build();
    }

}
