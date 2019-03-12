package travel.tool.repository;

import org.springframework.data.repository.CrudRepository;
import travel.tool.entity.Landmark;

/**
 * @author ipop
 */
public interface ILandmarkRepository extends CrudRepository<Landmark, Long> {
}
