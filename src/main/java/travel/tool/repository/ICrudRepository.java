package travel.tool.repository;

import java.util.Collection;

/**
 * @author ipop
 */
public interface ICrudRepository<T> {

    Collection<T> getAll();

    T findById(long id);

    T update(T model);

    boolean delete(T model);
}
