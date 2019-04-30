package bg.unwe.bookstore.dao;

import bg.unwe.bookstore.model.EntityModel;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<ID, U extends EntityModel<ID>> {

    private static final String FIND_ALL_QUERY = "SELECT e FROM %s e";
    private static final String REMOVE_BY_ID = "DELETE FROM %s e WHERE e.id = :id";

    public U persist(U entity) {
        if(entity.isNew()) {
            getEm().persist(entity);
            return entity;

        } else {
            return getEm().merge(entity);
        }
    }

    public void remove(U entity) {
        entity = getEm().merge(entity);
        getEm().remove(entity);
    }

    public void remove(ID entityId) {
        Query query = getEm().createQuery(String.format(REMOVE_BY_ID, getClass().getSimpleName()));
        query.setParameter("id", entityId);
        query.executeUpdate();
    }

    public Optional<U> findById(ID id) {
        return Optional.ofNullable(getEm().find(getEntityClass(), id));
    }

    public List<U> findAll() {
        Query q = getEm().createQuery(String.format(FIND_ALL_QUERY, getEntityClass().getSimpleName()));

        return q.getResultList();
    }

    protected abstract Class<U> getEntityClass();

    protected abstract EntityManager getEm();

    /**
     * @param params Provide an even number of parameters.
     *               The first must be <span style="color: red">String</span>;
     *               The second must be the object that will be passed as a parameter.
     */
    protected Query createNamedQuery(String queryName, Object... params) {
        if (params.length % 2 != 0) {
            throw new IllegalStateException("Uneven number of parameters");
        }

        Query query = getEm().createNamedQuery(queryName);

        for (int i = 0; i < params.length; i += 2) {
            String paramName = (String) params[i];
            query.setParameter(paramName, params[i + 1]);
        }

        return query;
    }
}
