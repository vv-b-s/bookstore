package bg.unwe.bookstore.dao;

import bg.unwe.bookstore.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CategoryRepository extends AbstractRepository<Integer, Category> {

    @Inject
    private EntityManager em;

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
}
