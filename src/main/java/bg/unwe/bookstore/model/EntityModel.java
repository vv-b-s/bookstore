package bg.unwe.bookstore.model;

public interface EntityModel<ID> {
    ID getId();

    default boolean isNew() {
        return getId() == null;
    }
}
