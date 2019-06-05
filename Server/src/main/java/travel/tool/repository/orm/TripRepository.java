package travel.tool.repository.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import travel.tool.model.Trip;
import travel.tool.repository.ITripRepository;

/**
 * @author ipop
 */
@Repository("tripRepository")
public interface TripRepository extends JpaRepository<Trip, Long>, ITripRepository {
//    @Override
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE trip t SET t.available_places= :availablePlaces WHERE t.id= :id")
//    int updateAvailablePlaces(@Param("id") long id, @Param("availablePlaces") int availablePlaces);
//

    @Override
    default int getAvailablePlacesById(long id) {
        return 0;
    }

    @Override
    default int updateAvailablePlaces(long id, int availablePlaces) {
        return 0;
    }
}
