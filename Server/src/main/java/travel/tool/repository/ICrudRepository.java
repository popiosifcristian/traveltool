package travel.tool.repository;

import java.util.Collection;

/**
 * @author ipop
 */
public interface ICrudRepository<T> {

    Collection<T> getAll();

    T findById(long id);

    T save(T model);

    void delete(T model);

    default void tearDown() {
        getAll().forEach(this::delete);
    }
}
