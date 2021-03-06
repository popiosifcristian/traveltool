package travel.tool.repository;

import travel.tool.model.Trip;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author ipop
 */
public interface ITripRepository extends ICrudRepository<Trip> {
    List<Trip> findByLandmarkNameAndDateAndAndStartTime(String landmarkName, LocalDate date, LocalTime startTime);
    int updateAvailablePlaces(long id, int availablePlaces);
    int getAvailablePlacesById(long id);
}
