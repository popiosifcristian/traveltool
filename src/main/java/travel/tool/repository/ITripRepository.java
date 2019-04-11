package travel.tool.repository;

import travel.tool.entity.Trip;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author ipop
 */
public interface ITripRepository extends ICrudRepository<Trip> {
    List<Trip> searchByNameDateAndTime(String name, LocalDate date, LocalTime startTime);
    void updateAvailablePlaces(long id, int availablePlaces);
    int getAvailablePlaces(long id);
}
