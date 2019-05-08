package travel.tool.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import travel.tool.model.Landmark;
import travel.tool.repository.ILandmarkRepository;

/**
 * @author ipop
 */
public interface LandmarkRepository extends JpaRepository<Landmark, Long>, ILandmarkRepository {
}
