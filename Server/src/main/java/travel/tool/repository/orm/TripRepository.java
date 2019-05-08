package travel.tool.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import travel.tool.model.Trip;
import travel.tool.repository.ITripRepository;

/**
 * @author ipop
 */
public interface TripRepository extends JpaRepository<Trip, Long>, ITripRepository {
}
