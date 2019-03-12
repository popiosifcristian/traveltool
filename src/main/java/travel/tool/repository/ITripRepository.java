package travel.tool.repository;

import org.springframework.data.repository.CrudRepository;
import travel.tool.entity.Trip;

/**
 * @author ipop
 */
public interface ITripRepository extends CrudRepository<Trip, Long> {
}
