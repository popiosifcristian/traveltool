package travel.tool.repository.crud;

import org.springframework.data.repository.CrudRepository;
import travel.tool.entity.Landmark;

/**
 * @author ipop
 */
public interface ILandmarkCrudRepository extends CrudRepository<Landmark, Long> {
}
