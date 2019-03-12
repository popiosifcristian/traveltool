package travel.tool.repository;

import java.util.Collection;

/**
 * @author ipop
 */
public interface IBaseRepository <T>{

    Collection<T> getAll();

    T findById(Long id);

    T update(T model);

    boolean delete(T model);
}
