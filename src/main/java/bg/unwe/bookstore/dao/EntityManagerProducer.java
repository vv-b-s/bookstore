package bg.unwe.bookstore.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {
    @PersistenceContext
    private EntityManager em;

    @Produces
    @RequestScoped
    EntityManager createEntityManager() {
        return em;
    }

}
