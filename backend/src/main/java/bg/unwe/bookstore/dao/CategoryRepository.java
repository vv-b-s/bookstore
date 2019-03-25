package bg.unwe.bookstore.dao;

import bg.unwe.bookstore.model.Category;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryRepository extends AbstractRepository<Integer, Category> {
    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }
}
