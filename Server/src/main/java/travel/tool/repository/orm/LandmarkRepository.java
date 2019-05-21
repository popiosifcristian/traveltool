package travel.tool.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.tool.model.Landmark;
import travel.tool.repository.ILandmarkRepository;

/**
 * @author ipop
 */
@Repository("landmarkRepository")
public interface LandmarkRepository extends JpaRepository<Landmark, Long>, ILandmarkRepository {
}
