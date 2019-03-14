package travel.tool.repository.crud;

import org.springframework.data.repository.CrudRepository;
import travel.tool.entity.Trip;

/**
 * @author ipop
 */
public interface ITripCrudRepository extends CrudRepository<Trip, Long> {
}
