package travel.tool.repository;

import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author ipop
 */
@Repository
public interface ICrudRepository<T> {

    Collection<T> getAll();

    T findById(long id);

    T update(T model);

    boolean delete(T model);

    default void tearDown() {
        getAll().forEach(this::delete);
    }
}
