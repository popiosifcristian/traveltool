package travel.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import travel.tool.model.Trip;
import travel.tool.repository.ITripRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ipop
 */
@Service("tripService_")
public class TripService_ {
    @Autowired
    @Qualifier("tripRepository")
    private ITripRepository tripRepository;

    public List<Trip> findAll() {
        return new ArrayList<>(tripRepository.findAll());
    }

    public Trip getOne(long id) {
        return tripRepository.getOne(id);
    }

    public Trip save(Trip trip) {
        return tripRepository.save(trip);
    }

    public void delete(Trip trip) {
        tripRepository.delete(trip);
    }

    public List<Trip> searchByNameDateAndTime(String name, LocalDate date, LocalTime startTime) {
        return tripRepository.findByLandmarkNameAndDateAndAndStartTime(name, date, startTime);
    }

    public int updateAvailablePlaces(long id, int availablePlaces) {
        return tripRepository.updateAvailablePlaces(id, availablePlaces);
    }

    public int getAvailablePlaces(long id) {
        return tripRepository.getAvailablePlacesById(id);
    }
}
