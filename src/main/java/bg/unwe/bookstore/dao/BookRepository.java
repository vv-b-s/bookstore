package bg.unwe.bookstore.dao;

import bg.unwe.bookstore.model.Book;
import bg.unwe.bookstore.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository extends AbstractRepository<Integer, Book> {

    public Optional<Book> findByISBN(String isbn) {
        Query query = createNamedQuery(Book.FIND_BY_ISBN, "isbn", isbn);
        return query.getResultList().stream().findFirst();
    }

    public List<Book> findByCategory(Category category) {
        Query query = createNamedQuery(Book.FIND_BY_CATEGORY, "category", category);
        return query.getResultList();
    }

    public List<Book> findByCategory(int categoryId) {
        Query query = createNamedQuery(Book.FIND_BY_CATEGORY_ID, "categoryId", categoryId);
        return query.getResultList();
    }

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}
