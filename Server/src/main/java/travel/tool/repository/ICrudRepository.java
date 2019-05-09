package travel.tool.repository;

import java.util.Collection;

/**
 * @author ipop
 */
public interface ICrudRepository<T> {

    Collection<T> findAll();

    T getOne(long id);

    T save(T model);

    void delete(T model);

    default void tearDown() {
        findAll().forEach(this::delete);
    }
}
